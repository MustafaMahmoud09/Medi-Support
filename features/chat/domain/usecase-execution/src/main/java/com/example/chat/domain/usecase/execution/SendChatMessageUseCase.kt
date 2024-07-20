package com.example.chat.domain.usecase.execution

import com.example.chat.domain.model.AttachmentModel
import com.example.chat.domain.model.MessageModel
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.ISendChatMessageUseCase
import com.example.libraries.core.remote.data.response.status.EffectResponse
import com.example.libraries.core.remote.data.response.status.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalCoroutinesApi::class)
class SendChatMessageUseCase(
    private val chatRepository: IChatRepository,
    private val baseUrl: String
) : ISendChatMessageUseCase {

    override suspend fun invoke(
        doctorId: Int,
        message: String?,
        temporaryMsgId: String,
        uri: Any?
    ): Flow<Status<EffectResponse<MessageModel>>> {

        return channelFlow<Status<EffectResponse<MessageModel>>> {

            chatRepository.sendMessage(
                doctorId = doctorId,
                message = message,
                temporaryMsgId = temporaryMsgId,
                file = uri
            ).collectLatest { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            trySend(
                                element = Status.Success(
                                    EffectResponse(
                                        statusCode = status.toData()?.statusCode ?: 0,
                                        body = MessageModel(
                                            messageId = status.toData()?.body?.message?.id ?:"",
                                            fromId = status.toData()?.body?.message?.fromId ?: 0,
                                            toId = status.toData()?.body?.message?.toId ?: 0,
                                            body = status.toData()?.body?.message?.message ?:"",
                                            attachment = AttachmentModel(
                                                newName = baseUrl + "attachments/"  + (status.toData()?.body?.message?.attachment?.file ?:""),
                                                ex = (status.toData()?.body?.message?.attachment?.title ?:"").subStringFromEndBefore('.'),
                                                oldName = (status.toData()?.body?.message?.attachment?.title ?:"").substringBefore('.')
                                            ),
                                            seen = true,
                                            time = formatMessageTime(status.toData()?.body?.message?.createdAt ?:"")
                                        )
                                    )
                                )
                            )

                        }//end if
                        else {

                            trySend(
                                element = Status.Success(
                                    EffectResponse(
                                        statusCode = status.toData()?.statusCode ?: 0,
                                        body = null
                                    )
                                )
                            )

                        }//end else

                    }

                    is Status.Loading -> {
                        trySend(status)
                    }

                    is Status.Error -> {
                        trySend(status)
                    }

                }//end when

            }//end collectLatest

        }.flowOn(Dispatchers.IO)

    }//end invoke

    private fun String.subStringFromEndBefore(char: Char): String{

        var result = ""

        for (count in this.indices){
            if(this[this.length - count - 1] == char){
                break
            }
            result += this[this.length - count - 1]
        }//end for

        var finalResult = ""
        for (count in result.indices){
            finalResult += result[result.length - count - 1]
        }//end for

        return finalResult

    }//end subStringFromEnd


    private fun formatMessageTime(time: String): String {

        // تنسيق الوقت المحدد
        val serverTime = Instant.parse(time)

        val localTime = serverTime.atZone(ZoneId.systemDefault()).toLocalDateTime()

        // الوقت الحالي
        val currentTime = LocalDateTime.now()

        // مقارنة الوقتين
        return if (localTime.toLocalDate() == currentTime.toLocalDate()) {

            val finalFormatter = DateTimeFormatter.ofPattern("HH:mm")
            localTime.format(finalFormatter)

        }//end if
        else {

            val finalFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HH:mm")
            localTime.format(finalFormatter)

        }//end else

    }//end formatMessageTime

}//end SendChatMessageUseCase