package com.example.chat.domain.usecase.execution

import com.example.chat.domain.dto.declarations.message.IMessageDto
import com.example.chat.domain.mapper.declarations.child.IMessageDtoToMessageModelMapper
import com.example.chat.domain.model.MessageModel
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetPageMessageUseCase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.collect
import java.util.LinkedList

class GetPageMessageUseCase(
    private val chatRepository: IChatRepository,
    private val messageDtoToMessageModelMapper: IMessageDtoToMessageModelMapper
): IGetPageMessageUseCase {

    override suspend fun invoke(
        page: Int,
        perPage: Int,
        doctorId: Int
    ): UnEffectResponse<List<MessageModel>> {

        val messageResult = LinkedList<MessageModel>()
        var lastPage = 0

        while (true) {

            var breakCondition = false

            //make request on repository for get data
            //collect result flow
            //in success status map data to model and kill collect
            chatRepository.getPageChatMessage(
                page = page,
                perPage = perPage,
                doctorId = doctorId
            ).collect { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get offline doctors
                            val messageDto = status.toData()?.body?.messages

                            //map offline doctors form dto to model
                            val messageModels =
                                messageDtoToMessageModelMapper.listConvertor(
                                    list = messageDto as List<IMessageDto>
                                )

                            //add offline doctors model list to result list
                            messageResult.addAll(messageModels)
                            lastPage = status.toData()?.body?.lastPage ?: 0
                            breakCondition = true
                        }//end if

                        return@collect

                    }//end success case

                    is Status.Loading -> {

                    }//end loading case

                    is Status.Error -> {

                    }//end error case

                }//end when

            }//end collect

            if (breakCondition) {
                break
            }//end if

        }//end while

        //return result here
        return UnEffectResponse(
            lastPageNumber = lastPage,
            body = messageResult
        )

    }//end invoke

}//end GetPageChatsUseCase