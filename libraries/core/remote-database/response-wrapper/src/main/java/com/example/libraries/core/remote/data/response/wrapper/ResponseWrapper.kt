package com.example.libraries.core.remote.data.response.wrapper

import com.example.libraries.core.remote.data.response.status.Response
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import java.io.IOException


class ResponseWrapper {

    inline fun <reified PT, CT> wrapper(
        crossinline request: suspend () -> retrofit2.Response<CT>
    ): Flow<Status<Response<PT>>> {

        return channelFlow {

            //emit loading status
            send(
                element = Status.Loading
            )

            //make request
            val response = request()

            //if the response is successful
            try {

                //emit success status
                send(
                    element = Status.Success(
                        data = Response(
                            statusCode = response.code(),
                            body = response.body()
                        )
                    ) as Status<Response<PT>>
                )

            }//end try
            //if exist exception in internet
            catch (ex: IOException) {

                //emit error status
                send(
                    element = Status.Error(
                        status = 400
                    )
                )

            }//end catch
            //if exist exception in server
            catch (ex: Exception){

                //emit error status
                send(
                    element = Status.Error(
                        status = 500
                    )
                )

            }//end catch

        }//end flow

    }//end wrapper


}//end ResponseWrapper