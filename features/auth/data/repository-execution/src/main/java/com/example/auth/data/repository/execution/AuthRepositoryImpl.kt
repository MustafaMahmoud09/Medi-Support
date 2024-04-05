package com.example.auth.data.repository.execution

import android.util.Log
import com.example.auth.data.source.remote.data.requests.AuthRequest
import com.example.auth.domain.repository.declarations.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(
    private val authRequest: AuthRequest
) : IAuthRepository {

    override suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Int> {

        return flow {

            emit(0)

            Log.d("TAG", "LOADING")

            val response = authRequest.register(
                name = name,
                lastName = lastName,
                email = email,
                password = password,
                passwordConfirmation = confirmPassword
            )

            //if the response is successful
            if (response.isSuccessful) {
                emit(response.code())
                Log.d("TAG", "SUCCESS")
            }//end if

            //if the response is failed
            else {
                emit(502)
                Log.d("TAG", "ERROR")
            }//end else

        }//end flow

    }//end register

}//end AuthRepository