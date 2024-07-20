package com.example.chat.domain.usecase.execution

import com.example.chat.domain.dto.declarations.chat.IContactDto
import com.example.chat.domain.mapper.declarations.child.IChatDtoToChatModelMapper
import com.example.chat.domain.model.ChatModel
import com.example.chat.domain.repository.declarations.IChatRepository
import com.example.chat.domain.usecase.declarations.IGetPageChatsUseCase
import com.example.libraries.core.remote.data.response.status.Status
import com.example.libraries.core.remote.data.response.status.UnEffectResponse
import kotlinx.coroutines.flow.collect
import java.util.LinkedList

class GetPageChatsUseCase(
    private val chatRepository: IChatRepository,
    private val chatDtoToChatModelMapper: IChatDtoToChatModelMapper
): IGetPageChatsUseCase {

    override suspend fun invoke(
        page: Int,
        perPage: Int
    ): UnEffectResponse<List<ChatModel>> {

        val chatResult = LinkedList<ChatModel>()
        var lastPage = 0

        while (true) {

            var breakCondition = false

            //make request on repository for get data
            //collect result flow
            //in success status map data to model and kill collect
            chatRepository.getPageChat(
                page = page,
                perPage = perPage
            ).collect { status ->

                when (status) {

                    is Status.Success -> {

                        if (status.toData()?.statusCode == 200) {

                            //get offline doctors
                            val chatDto = status.toData()?.body?.contactDto

                            //map offline doctors form dto to model
                            val chatModels =
                                chatDtoToChatModelMapper.listConvertor(
                                    list = chatDto as List<IContactDto>
                                )

                            //add offline doctors model list to result list
                            chatResult.addAll(chatModels)
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
            body = chatResult
        )

    }//end invoke

}//end GetPageChatsUseCase