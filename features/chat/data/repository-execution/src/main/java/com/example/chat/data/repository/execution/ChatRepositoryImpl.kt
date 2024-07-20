package com.example.chat.data.repository.execution

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import com.example.chat.data.source.remote.data.dto.execution.chat.ChatResponseDto
import com.example.chat.data.source.remote.data.dto.execution.chatAuth.ChatAuthResponseDto
import com.example.chat.data.source.remote.data.dto.execution.doctorInfo.DoctorInfoResponseDto
import com.example.chat.data.source.remote.data.dto.execution.doctorInfo.IDoctorInfoResponseDto
import com.example.chat.data.source.remote.data.dto.execution.message.MessageResponseDto
import com.example.chat.data.source.remote.data.dto.execution.sendMessage.SendMessageResponseDto
import com.example.chat.data.source.remote.data.requests.ChatRequest
import com.example.chat.domain.dto.declarations.chat.IChatResponseDto
import com.example.chat.domain.dto.declarations.chatAuth.IChatAuthResponseDto
import com.example.chat.domain.dto.declarations.message.IMessageResponseDto
import com.example.chat.domain.dto.declarations.sendMessage.ISendMessageResponseDto
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.database_creator.MediSupportDatabase
import com.example.libraries.core.local.data.entity.declarations.IUserEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.wrapper.ResponseWrapper
import com.example.shared.preferences.access.`object`.SharedPreferencesAccessObject
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.http.Field
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ChatRepositoryImpl(
    private val wrapper: ResponseWrapper,
    private val chatRequest: ChatRequest,
    private val localDatabase: MediSupportDatabase,
    private val context: Context,
    private val sharedPreferencesAccessObject: SharedPreferencesAccessObject,
) : IChatRepository {


    //function for make request on server for get page contain on chats
    override suspend fun getPageChat(
        page: Int,
        perPage: Int
    ): Flow<Status<EffectResponse<IChatResponseDto>>> {

        return wrapper.infiniteWrapper<IChatResponseDto, ChatResponseDto> {
            chatRequest.getPageChats(
                page = page,
                perPage = perPage
            )
        }//end infiniteWrapper

    }//end getPageChat


    //function for make request on server for get page contain on chats
    override suspend fun getPageChatMessage(
        page: Int,
        perPage: Int,
        doctorId: Int
    ): Flow<Status<EffectResponse<IMessageResponseDto>>> {

        return wrapper.infiniteWrapper<IMessageResponseDto, MessageResponseDto> {
            chatRequest.getChatMessages(
                page = page,
                perPage = perPage,
                doctorId = doctorId
            )
        }//end infiniteWrapper

    }//end getPageChat


    //function for make request on server for get page contain on chats
    override suspend fun getDoctorById(
        doctorId: Int
    ): Flow<Status<EffectResponse<IDoctorInfoResponseDto>>> {

        return wrapper.infiniteWrapper<IDoctorInfoResponseDto, DoctorInfoResponseDto> {
            chatRequest.getDoctorInfoById(
                doctorId = doctorId
            )
        }//end infiniteWrapper

    }//end getPageChat


    //function for make request on server for get page contain on chats
    override suspend fun getChatAuthToken(
        socketId: String,
        channelName: String
    ): Flow<Status<EffectResponse<IChatAuthResponseDto>>> {

        return wrapper.infiniteWrapper<IChatAuthResponseDto, ChatAuthResponseDto> {
            chatRequest.authInChatChannel(
                socketId = socketId,
                channelName = channelName
            )
        }//end infiniteWrapper

    }//end getPageChat


    //function for provide auth user
    //function for make request on server user info
    //after that cache data
    //after that return data from local database
    override suspend fun getAccountInfo(): Flow<List<IUserEntity>> {

        //get user auth token
        val accessToken = sharedPreferencesAccessObject.accessTokenManager().getAccessToken()

        //return user info from local database
        return localDatabase.userDao().getUserByAccessToken(
            accessToken = accessToken
        )

    }//end getAccountInfo


    //function for provide auth user
    //function for make request on server user info
    //after that cache data
    //after that return data from local database
    override suspend fun seenChatMessages(
        doctorId: Int
    ): Flow<Status<EffectResponse<Any>>> {

        return wrapper.infiniteWrapper<Any, Any> {
            chatRequest.seenChatMessages(
                doctorId = doctorId
            )
        }//end infiniteWrapper

    }//end getAccountInfo


    //function for provide auth user
    //function for make request on server user info
    //after that cache data
    //after that return data from local database
    override suspend fun sendMessage(
        doctorId: Int,
        message: String?,
        temporaryMsgId: String,
        file: Any?
    ): Flow<Status<EffectResponse<ISendMessageResponseDto>>> {

        val params: HashMap<String, RequestBody> = HashMap()

        params["id"] = doctorId.toString().toRequestBody("text/plain".toMediaTypeOrNull())
        params["temporaryMsgId"] = temporaryMsgId.toRequestBody("text/plain".toMediaTypeOrNull())
        if (message != null) {
            params["message"] = message.toRequestBody("text/plain".toMediaTypeOrNull())
        }//end if

        var resultFile: MultipartBody.Part? = null

        if(file != null){
            resultFile = convertUriToMultiPart(
                imageUri = file
            )
            Log.d("TAG_REPO", resultFile?.body.toString())
        }//end if

        return wrapper.infiniteWrapper<ISendMessageResponseDto, SendMessageResponseDto> {
            chatRequest.sendMessage(
                params = params,
                file = resultFile
            )
        }//end infiniteWrapper

    }//end getAccountInfo

    @SuppressLint("Recycle")
    private fun convertUriToMultiPart(imageUri: Any?): MultipartBody.Part? {

        val fileDescriptor = context.contentResolver.openFileDescriptor(imageUri as Uri, "r")

        val inputStream = FileInputStream(fileDescriptor?.fileDescriptor)
        val file = File(context.cacheDir, "tempFile")
        val outputStream = FileOutputStream(file)

        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }

        return MultipartBody.Part.createFormData(
            "file",
            file.name,
            file.asRequestBody()
        )

    }//end convertUriToMultiPart

}//end ChatRepositoryImpl