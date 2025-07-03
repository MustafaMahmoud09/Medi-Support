package com.example.chat.mapper.execution

import com.example.chat.domain.dto.declarations.chat.IContactDto
import com.example.chat.domain.mapper.declarations.child.IChatDtoToChatModelMapper
import com.example.chat.domain.model.ChatModel
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class ChatDtoToChatModelMapper(
    private val baseUrl: String
) : IChatDtoToChatModelMapper {

    override fun listConvertor(
        list: List<IContactDto>
    ): List<ChatModel> {

        return list.map {
            objectConvertor(
                obj = it
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IContactDto
    ): ChatModel {

        return ChatModel(
            doctorName = (obj.user?.firstName ?: "") + " " + (obj.user?.lastName ?: ""),
            doctorId = obj.user?.id ?: 0,
            doctorImageUrl = baseUrl + (obj.user?.avatar ?: ""),
            lastMessage = obj.lastMessage?.lastMessage ?: "",
            activeStatus = obj.user?.activeStatus == 1,
            unseenCount = if ((obj.unseenCounter ?: 0) > 9) "+9" else "${obj.unseenCounter ?: 0}",
            lastMessageTime = formatMessageTime(obj.lastMessage?.createdAt ?: ""),
            doctorJobType = obj.user?.specialization ?: ""
        )

    }//end objectConvertor


    private fun formatMessageTime(time: String): String {

        // تنسيق الوقت المحدد
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX")
        val parsedTime = ZonedDateTime.parse(time, formatter)

        val localTime = parsedTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()

        // الوقت الحالي
        val currentTime = LocalDateTime.now()

        // مقارنة الوقتين
        return if (localTime.toLocalDate() == currentTime.toLocalDate()) {

            val finalFormatter = DateTimeFormatter.ofPattern("HH:mm")
            localTime.format(finalFormatter)

        }//end if
        else {

            val finalFormatter = DateTimeFormatter.ofPattern("d-M-yyyy")
            localTime.format(finalFormatter)

        }//end else

    }//end formatMessageTime

}//end ChatDtoToChatModelMapper