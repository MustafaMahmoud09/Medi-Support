package com.example.auth.data.repository.execution

import android.util.Log
import com.example.auth.data.source.remote.data.requests.AuthRequest
import com.example.auth.data.source.remote.data.requests.ResetPasswordRequest
import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class AuthRepositoryImpl(
    private val authRequest: AuthRequest,
    private val resetPasswordRequest: ResetPasswordRequest
) : IAuthRepository {

    //function for make register request
    override suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Status<Int>> {

        return flow {

            //emit loading status
            emit(
                value = Status.Loading
            )

            Log.d("STATUS","LOADING")

            //make request
            val response = authRequest.register(
                name = name,
                lastName = lastName,
                email = email,
                password = password,
                passwordConfirmation = confirmPassword
            )

            //if the response is successful
            if (response.isSuccessful) {

                //emit success status
                emit(
                    value = Status.Success(
                        data = response.code()
                    )
                )

                Log.d("STATUS","SUCCESS")
            }//end if

            //if the response is failed
            else {

                //emit error status
                emit(
                    value = Status.Error(
                        message = "server not exist"
                    )
                )

                Log.d("STATUS","ERROR")
            }//end else

        }//end flow

    }//end register


    //function for send email for reset user password
    override suspend fun sendEmail(
        email: String,
    ): Flow<Status<Int>> {

        return flow {

            //emit loading status
            emit(
                value = Status.Loading
            )

            //make request
            val response = resetPasswordRequest.sendUserEmail(
                email = email
            )

            //if the response is successful
            if (response.isSuccessful) {

                //emit success status
                emit(
                    value = Status.Success(
                        data = response.code()
                    )
                )

            }//end if

            //if the response is failed
            else {

                //emit error status
                emit(
                    value = Status.Error(
                        message = "server not exist"
                    )
                )

            }//end else

        }//end flow

    }//end register

    //function for verify code request on server
    override suspend fun verifyCode(
        email: String,
        code: String
    ): Flow<Status<Int>> {

        return flow {

            //emit loading status
            emit(
                value = Status.Loading
            )

            //make request
            val response = resetPasswordRequest.verifyCode(
                email = email,
                code = code
            )

            //if the response is successful
            if (response.isSuccessful) {

                //emit success status
                emit(
                    value = Status.Success(
                        data = response.code()
                    )
                )

            }//end if

            //if the response is failed
            else {

                //emit error status
                emit(
                    value = Status.Error(
                        message = "server not exist"
                    )
                )

            }//end else

        }//end flow

    }//end register

    //function for make reset password request on server
    override suspend fun resetPassword(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Status<Int>> {

        return flow {

            //emit loading status
            emit(
                value = Status.Loading
            )

            //make request
            val response = resetPasswordRequest.resetPassword(
                email = email,
                password = password,
                passwordConfirmation = passwordConfirmation
            )

            //if the response is successful
            if (response.isSuccessful) {

                //emit success status
                emit(
                    value = Status.Success(
                        data = response.code()
                    )
                )

            }//end if

            //if the response is failed
            else {

                //emit error status
                emit(
                    value = Status.Error(
                        message = "server not exist"
                    )
                )

            }//end else

        }//end flow

    }//end register

}//end AuthRepository