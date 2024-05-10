package com.example.account.setting.data.repository.execution

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.ICacheAccountRepositoryHelper
import com.example.account.setting.data.repository.execution.cacheHelperDeclarations.IServerAccountRepositoryHelper
import com.example.account.setting.data.source.remote.data.requests.AccountSettingRequest
import com.example.account.setting.domain.repository.declarations.IAccountSettingRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.local.data.entity.declarations.IUserEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class AccountSettingRepositoryImpl(
    private val wrapper: ResponseWrapper,
    private val accountSettingRequest: AccountSettingRequest,
    private val serverAccountRepositoryHelper: IServerAccountRepositoryHelper,
    private val localDatabase: MediSupportDatabase,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject,
    private val cacheAccountRepositoryHelper: ICacheAccountRepositoryHelper,
    private val context: Context
) : IAccountSettingRepository {

    //function for make request on server for update profile data
    override suspend fun updateProfileData(
        firstName: String?,
        lastName: String?,
        password: String?,
        imageUri: Any?
    ): Flow<Status<EffectResponse<Any>>> {

        //get user auth token
        val accessToken = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()

        val params: HashMap<String, RequestBody> = HashMap()

        var avatar: MultipartBody.Part? = null

        if (firstName != null) {
            params["name"] = firstName.toRequestBody("text/plain".toMediaTypeOrNull())
        }//end if
        if (lastName != null) {
            params["last_name"] = lastName.toRequestBody("text/plain".toMediaTypeOrNull())
        }//end if
        if (password != null) {
            params["password"] = password.toRequestBody("text/plain".toMediaTypeOrNull())
            params["password_confirmation"] =
                password.toRequestBody("text/plain".toMediaTypeOrNull())
        }//end if
        if (imageUri != null) {
            avatar = convertUriToMultiPart(
                imageUri = imageUri
            )
        }//end if

        return channelFlow {

            wrapper.wrapper<Any, Any> {
                accountSettingRequest.updateProfile(
                    params = params,
                    avatar = avatar
                )
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {
                        if (status.toData()?.statusCode == 200) {
                            cacheAccountRepositoryHelper.cacheUpdateAccountInfo(
                                lastName = lastName,
                                firstName = firstName,
                                accessToken = accessToken
                            )
                        }//end if
                        trySend(status)
                    }//end success case

                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                }//end when

            }//end wrapper

        }.flowOn(Dispatchers.IO)

    }//end updateProfileData

    private fun convertUriToMultiPart(imageUri: Any?): MultipartBody.Part? {

        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor =
            context.contentResolver.query((imageUri as Uri), filePathColumn, null, null, null)

        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = cursor?.getString(columnIndex!!)
        cursor?.close()

        val file = filePath?.let { File(it) }

        return file?.name?.let {
            MultipartBody.Part.createFormData(
                "avatar",
                it,
                file.asRequestBody()
            )
        }

    }//end convertUriToMultiPart


    //function for make request on server user info
    //after that cache data
    //after that return data from local database
    override suspend fun getAccountInfo(): Flow<List<IUserEntity>> {

        //get user auth token
        val accessToken = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()

        //make request on server for get user info
        //cache user info in local database
        serverAccountRepositoryHelper.getAccountInfoServer(
            accessToken = accessToken
        )

        //return user info from local database
        return localDatabase.userDao().getUserByAccessToken(
            accessToken = accessToken
        )

    }//end getAccountInfo


    //function for make request on server for send contact us message to admin
    override suspend fun sendContactUsMessage(
        userName: String,
        email: String,
        message: String
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.wrapper<Any, Any> {
            accountSettingRequest.sendContactUsMessage(
                username = userName,
                email = email,
                message = message
            )
        }//end wrapper

    }//end sendContactUsMessage


    //function for make request on server for delete user auth account

    override suspend fun deleteUserAccount(): Flow<Status<EffectResponse<Any>>> {

        //get user auth token
        val accessToken = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()

        //return flow
        return channelFlow {

            wrapper.wrapper<Any, Any> {
                accountSettingRequest.deleteUserAccount()
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //delete user account from local database
                            localDatabase.userDao().deleteUserAccountByToken(
                                token = accessToken
                            )

                            //remove access token from shared preferences
                            sharedPreferencesAccessObject.accessTokenManager().setAccessToken(
                                value = ""
                            )

                        }//end if

                        trySend(status)
                    }//end success case

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                }//end when

            }//end collectLatest

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end deleteUserAccount


    //function for make request on server for make logout
    override suspend fun logout(): Flow<Status<EffectResponse<Any>>> {

        //get user auth token
        val accessToken = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()

        //return flow
        return channelFlow {

            wrapper.wrapper<Any, Any> {
                accountSettingRequest.logout()
            }.collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //delete user account from local database
                            localDatabase.userDao().deleteUserAccountByToken(
                                token = accessToken
                            )

                            //remove access token from shared preferences
                            sharedPreferencesAccessObject.accessTokenManager().setAccessToken(
                                value = ""
                            )

                        }//end if

                        trySend(status)
                    }//end success case

                    is Status.Loading -> {
                        trySend(status)
                    }//end loading case

                    is Status.Error -> {
                        trySend(status)
                    }//end error case

                }//end when

            }//end collectLatest

        }.flowOn(Dispatchers.IO)//end channelFlow

    }//end logout

}//end AccountSettingRepositoryImpl