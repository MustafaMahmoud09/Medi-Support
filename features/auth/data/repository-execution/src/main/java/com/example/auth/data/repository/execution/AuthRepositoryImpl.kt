package com.example.auth.data.repository.execution

import com.example.auth.data.source.remote.data.requests.AuthRequest
import com.example.auth.data.source.remote.data.requests.ResetPasswordRequest
import com.example.auth.domain.dto.declarations.emailUser.IEmailUserDto
import com.example.auth.domain.dto.declarations.socialUser.ISocialUserDto
import com.example.auth.domain.repository.declarations.IAuthRepository
import com.example.data.source.remote.data.dto.execution.response.emailUser.EmailUserDto
import com.example.data.source.remote.data.dto.execution.response.socialUser.SocialUserDto
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.local.data.entity.declarations.IUserEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.libraries.local.data.shared.entities.entity.execution.user.UserEntity
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authRequest: AuthRequest,
    private val resetPasswordRequest: ResetPasswordRequest,
    private val responseWrapper: ResponseWrapper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject
) : IAuthRepository {

    //function for make register request
    override suspend fun register(
        name: String,
        lastName: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Flow<Status<EffectResponse<Any>>> {

        //execute register request here
        return responseWrapper.wrapper<Any, Any> {
            authRequest.register(
                name = name,
                lastName = lastName,
                email = email,
                password = password,
                passwordConfirmation = confirmPassword
            )
        }

    }//end register


    //function for execute login with email
    override suspend fun loginWithEmail(
        email: String,
        password: String
    ): Flow<Status<EffectResponse<IEmailUserDto>>> {

        //execute login with email request here
        return responseWrapper.wrapper<IEmailUserDto, EmailUserDto> {
            authRequest.loginWithEmail(
                email = email,
                password = password
            )
        }

    }//end loginWithEmail


    //function for execute login with social
    override suspend fun loginWithSocial(
        accessToken: String,
        provider: String
    ): Flow<Status<EffectResponse<ISocialUserDto>>> {

        //execute login with social request here
        return responseWrapper.wrapper<ISocialUserDto, SocialUserDto> {
            authRequest.loginWithSocial(
                token = accessToken,
                provider = provider
            )
        }//end wrapper

    }//end loginWithSocial

    //function for caching user data in local data base
    override suspend fun cachingUserData(
        id: Long,
        firstName: String,
        lastName: String,
        email: String,
        token: String,
        path: String,
        remember: Boolean
    ) {

        //check user exist or no
        val user = localDatabase.userDao().getUserByEmail(
            email = email
        ).isNotEmpty()

        //set access token in shared preferences here
        sharedPreferencesAccessObject.accessTokenManager().setAccessToken(
            value = token
        )

        //if user exist update user data to default value
        if (user) {

            //update user data here
            localDatabase.userDao().updateUserData(
                id = id,
                email = email,
                lastName = lastName,
                firstName = firstName,
                path = path,
                token = token,
                remember = remember,
                auth = true,
                count = 0
            )

        }//end if

        //if user not exist in local
        //insert as new user
        else {

            //insert new user here
            localDatabase.userDao().insert(
                user = UserEntity(
                    id = id,
                    email = email,
                    lastName = lastName,
                    firstName = firstName,
                    remember = remember,
                    auth = true,
                    token = token,
                    count = 0,
                    path = path
                )
            )

        }//end else

    }//end cachingUserData


    //function for send email for reset user password
    override suspend fun sendEmail(
        email: String,
    ): Flow<Status<EffectResponse<Any>>> {

        //execute send email request here
        return responseWrapper.wrapper<Any, Any> {
            resetPasswordRequest.sendUserEmail(
                email = email
            )
        }//end wrapper

    }//end register

    //function for verify code request on server
    override suspend fun verifyCode(
        email: String,
        code: String
    ): Flow<Status<EffectResponse<Any>>> {

        //execute verify code request here
        return responseWrapper.wrapper<Any, Any> {
            resetPasswordRequest.verifyCode(
                email = email,
                code = code
            )
        }//end wrapper

    }//end register

    //function for make reset password request on server
    override suspend fun resetPassword(
        email: String,
        password: String,
        passwordConfirmation: String
    ): Flow<Status<EffectResponse<Any>>> {

        //execute reset password request here
        return responseWrapper.wrapper<Any, Any> {
            resetPasswordRequest.resetPassword(
                email = email,
                password = password,
                passwordConfirmation = passwordConfirmation
            )
        }//end wrapper

    }//end register


    //function for get auth user from local database
    override suspend fun getAuthUser(
        rememberMe: Boolean
    ): List<IUserEntity> {

        return localDatabase.userDao().getAuthUser(
            rememberMe = rememberMe
        )

    }//end getAuthUser

    //function for update user auth count in local
    override suspend fun updateUsersAuthCount() {

        localDatabase.userDao().updateUsersAuthCount()

    }//end updateUsersAuthCount

}//end AuthRepository