package com.example.remotedata

import okhttp3.Interceptor
import okhttp3.Response

class JsonFormatInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //get request
        //add json format to request
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Content-Type",
                "application/json"
            )
            .addHeader(
                "Accept",
                "application/json"
            ).build()

        //return server response
        return chain.proceed(request)

    }//end intercept

}//end JsonFormatInterceptor