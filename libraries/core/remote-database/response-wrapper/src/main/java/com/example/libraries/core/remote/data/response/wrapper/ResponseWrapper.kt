package com.example.libraries.core.remote.data.response.wrapper

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.IOException


class ResponseWrapper {

    inline fun <reified PT, CT> wrapper(
        crossinline request: suspend () -> retrofit2.Response<CT>
    ): Flow<Status<EffectResponse<PT>>> {

        return channelFlow {

            //emit loading status
            trySend(
                element = Status.Loading
            )

            //if the response is successful
            try {

                //make request
                val response = request()

                //emit success status
                send(
                    element = Status.Success(
                        data = EffectResponse(
                            statusCode = response.code(),
                            body = response.body()
                        )
                    ) as Status<EffectResponse<PT>>
                )

            }//end try
            //if exist exception in internet
            catch (ex: IOException) {

                //emit error status
                trySend(
                    element = Status.Error(
                        status = 400
                    )
                )

            }//end catch
            //if exist exception in server
            catch (ex: Exception){

                //emit error status
                trySend(
                    element = Status.Error(
                        status = 500
                    )
                )

            }//end catch

        }.flowOn(Dispatchers.IO)//end flow

    }//end wrapper


    inline fun <reified PT, CT> infiniteWrapper(
        crossinline request: suspend () -> retrofit2.Response<CT>
    ): Flow<Status<EffectResponse<PT>>>{

        //return flow with status function result contain on diastolic measurements
        return channelFlow {

            var dataEmitting: Status<EffectResponse<PT>> =
                Status.Loading

            trySend(dataEmitting)

            val serverRequestsJop = CoroutineScope(Dispatchers.IO).launch {

                while (true) {

                    var breakCondition = false

                    try {

                        //make request on server here for get diastolic measurements
                        wrapper<PT, CT> {
                            request()
                        }.collectLatest { status ->

                            when (status) {

                                is Status.Success -> {
                                    breakCondition = true
                                    dataEmitting = status
                                    return@collectLatest
                                }//end Success case

                                is Status.Loading -> {
                                    //emit current status to flow
                                    dataEmitting = status
                                }//end Loading case

                                is Status.Error -> {
                                    dataEmitting = status
                                    return@collectLatest
                                }//end Error case

                            }//end when

                        }//end collectLatest

                    }//end try
                    catch (ex: IOException) {
                        dataEmitting = Status.Error(status = 400)
                    }//end catch

                    if (breakCondition) {
                        return@launch
                    }//end if

                    //delay 1/4 second between request and second request
                    delay(250)
                }//end while

            }//end coroutine builder scope

            serverRequestsJop.join()
            trySend(dataEmitting)

        }.flowOn(Dispatchers.IO)//end flow

    }//end infiniteWrapper


}//end ResponseWrapper