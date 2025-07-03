package com.example.presentation.uiState.state

import androidx.paging.PagingData
import com.example.chat.domain.model.ChatModel
import kotlinx.coroutines.flow.Flow

data class ChatsUiState(
    val totalChatStatus: Flow<PagingData<ChatModel>>? = null,
    val chatsBackup: Flow<PagingData<ChatModel>>? = null,
    val chatPlaceHolder: ChatModel = ChatModel(
        doctorName = "",
        doctorId = -1,
        doctorImageUrl = "",
        lastMessage = "",
        activeStatus = false,
        unseenCount = "0",
        lastMessageTime = "",
        doctorJobType = ""
    )
)
