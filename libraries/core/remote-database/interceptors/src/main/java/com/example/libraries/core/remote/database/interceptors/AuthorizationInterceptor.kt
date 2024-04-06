package com.example.libraries.core.remote.database.interceptors

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //get request
        //add token to request
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                ""
            ).build()

        //return server response
        return chain.proceed(request)

    }//end intercept

}//end AuthorizationInterceptor