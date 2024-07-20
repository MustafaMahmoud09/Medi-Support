package com.example.chat.domain.repository.declarations

import com.example.chat.data.source.remote.data.dto.execution.doctorInfo.IDoctorInfoResponseDto
import com.example.chat.domain.dto.declarations.chat.IChatResponseDto
import com.example.chat.domain.dto.declarations.chatAuth.IChatAuthResponseDto
import com.example.chat.domain.dto.declarations.message.IMessageResponseDto
import com.example.chat.domain.dto.declarations.sendMessage.ISendMessageResponseDto
import com.example.libraries.core.local.data.entity.declarations.IUserEntity
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.flow.Flow

interface IChatRepository {

    suspend fun getPageChat(
        page: Int,
        perPage: Int
    ): Flow<Status<EffectResponse<IChatResponseDto>>>


    suspend fun getPageChatMessage(
        page: Int,
        perPage: Int,
        doctorId: Int
    ): Flow<Status<EffectResponse<IMessageResponseDto>>>


    suspend fun getDoctorById(
        doctorId: Int
    ): Flow<Status<EffectResponse<IDoctorInfoResponseDto>>>


    suspend fun getChatAuthToken(
        socketId: String,
        channelName: String
    ): Flow<Status<EffectResponse<IChatAuthResponseDto>>>


    suspend fun getAccountInfo(): Flow<List<IUserEntity>>


    suspend fun seenChatMessages(
        doctorId: Int
    ): Flow<Status<EffectResponse<Any>>>


    suspend fun sendMessage(
        doctorId: Int,
        message: String?,
        temporaryMsgId: String,
        file: Any?,
    ): Flow<Status<EffectResponse<ISendMessageResponseDto>>>

}//end IChatRepository