package com.example.libraries.core.remote.data.response.wrapper

import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flowOn
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


}//end ResponseWrapper