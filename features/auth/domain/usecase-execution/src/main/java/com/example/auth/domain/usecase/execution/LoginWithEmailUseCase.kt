@file:OptIn(ExperimentalCoroutinesApi::class)

package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICachingUserDataUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithEmailUseCase
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest

class LoginWithEmailUseCase(
    private val authRepository: IAuthRepository,
    private val cachingUserDataUseCase: ICachingUserDataUseCase
) : ILoginWithEmailUseCase {

    //function for execute login with email
    //return flow of status
    override suspend fun invoke(
        email: String,
        password: String,
        remember: Boolean
    ): Flow<Status<Int>> {

        return channelFlow {

            authRepository.loginWithEmail(
                email = email,
                password = password
            ).collectLatest { status ->

                //if status is success
                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            cachingUserDataUseCase.cachingEmailUser(
                                user = status.toData()!!.body!!,
                                remember = remember
                            )

                        }//end if

                        //emit status code with success
                        send(Status.Success(status.toData()!!.statusCode))

                    }//end if

                    //if status is loading
                    is Status.Loading -> {
                        send(Status.Loading)
                    }//end else if

                    //if status is error
                    is Status.Error -> {
                        send(Status.Error(status.status))
                    }
                }//end else if

            }//end collectLatest

        }//end flow

    }//end invoke

}//end LoginWithEmailUseCase