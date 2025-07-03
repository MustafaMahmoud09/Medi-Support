package com.example.chat.mapper.execution

import com.example.chat.domain.dto.declarations.message.IMessageDto
import com.example.chat.domain.mapper.declarations.child.IMessageDtoToMessageModelMapper
import com.example.chat.domain.model.AttachmentModel
import com.example.chat.domain.model.MessageModel
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MessageDtoToMessageModelMapper(
    private val baseUrl: String
) : IMessageDtoToMessageModelMapper {

    override fun listConvertor(
        list: List<IMessageDto>
    ): List<MessageModel> {

        return list.map {
            objectConvertor(
                obj = it
            )
        }//end map

    }//end listConvertor

    override fun objectConvertor(
        obj: IMessageDto
    ): MessageModel {

        val attachment = (obj.attachment ?: "").filter {
            it != '\\' && it != '{' && it != '}' && it != '"'
        } +","

        return MessageModel(
            messageId = obj.id ?: "",
            fromId = obj.fromId ?: 0,
            toId = obj.toId ?: 0,
            body = obj.body ?: "",
            attachment = AttachmentModel(
                newName = baseUrl + "attachments/" + attachment.subString(startString = "new_name:", ','),
                oldName = attachment.subString(startString = "old_name:", end = ',').substringBefore('.'),
                ex = attachment.subString(startString = "new_name:", ',').subStringFromEndBefore('.')
            ),
            seen = obj.seen == 1,
            time = formatMessageTime(obj.createdAt ?: "")
        )

    }//end objectConvertor


    private fun String.subString(startString: String, end: Char): String {

        var result = ""
        var tempStart = ""

        for (it in this) {

            if (startString in tempStart) {

                if (it == end) {
                    break
                }//end if

                result += it
                continue
            }//end if

            tempStart += it

        }//end for Each

        return result

    }//end subString


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

}//end MessageDtoToMessageModelMapper