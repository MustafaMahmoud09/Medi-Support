package com.example.auth.domain.usecase.execution

import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.auth.domain.usecase.declarations.ICachingUserDataUseCase
import com.example.auth.domain.usecase.declarations.ILoginWithFacebookUseCase
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class LoginWithFacebookUseCase(
    private val authRepository: IAuthRepository,
    private val cachingUserDataUseCase: ICachingUserDataUseCase
) : ILoginWithFacebookUseCase {

    //function for execute login with facebook
    //return flow of status
    override suspend fun invoke(
        accessToken: String
    ): Flow<Status<Int>> {

        return flow {

            authRepository.loginWithSocial(
                accessToken = accessToken,
                provider = "facebook"
            ).collectLatest { status ->

                //if status is success
                when (status) {
                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            cachingUserDataUseCase.cachingSocialUser(
                                user = status.toData()!!.body!!,
                            )

                        }//end if

                        //emit status code with success
                        emit(Status.Success(status.toData()!!.statusCode))

                    }//end if

                    //if status is loading
                    is Status.Loading -> {
                        emit(Status.Loading)
                    }//end else if

                    //if status is error
                    is Status.Error -> {
                        emit(Status.Error(status.message))
                    }
                }//end else if

            }//end collectLatest

        }//end flow

    }//end invoke

}//end LoginWithFacebookUseCase