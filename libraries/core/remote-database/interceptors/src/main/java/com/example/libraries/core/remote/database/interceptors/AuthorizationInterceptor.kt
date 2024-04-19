package com.example.libraries.core.remote.database.interceptors

import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        //get request
        //add token to request
        val request = chain.request()
            .newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${sharedPreferencesAccessObject.accessTokenManager().getAccessToken()}"
            ).build()

        //return server response
        return chain.proceed(request)

    }//end intercept

}//end AuthorizationInterceptor