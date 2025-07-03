package com.example.presentation.uiState.state

import android.app.DownloadManager
import android.net.Uri
import androidx.paging.PagingData
import com.example.chat.domain.model.DoctorModel
import com.example.chat.domain.model.MessageModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

data class ChatUiState(
    val doctorId: Int = -1,
    val localMessageId: Int = 0,
    val firstItemVisibleIndex: MutableStateFlow<Int> = MutableStateFlow(-1),
    val firstItemVisibleOffset: MutableStateFlow<Int> = MutableStateFlow(-1),
    val currentPage: Int = 0,
    val messageStatus: List<MessageModel> = emptyList(),
    val totalMessagesStatus: Flow<PagingData<MessageModel>>? = null,
    val scrollToFirstMessage: Boolean = false,
    val firstLoadState: Boolean = true,
    val message: String = "",
    val file: FileStatus = FileStatus(),
    val countImageCaptured: Int = 0,
    val onlineDoctorDetailsStatus: GetDoctorDetailsStatus = GetDoctorDetailsStatus(),
    val downloadStatus: DownloadStatus = DownloadStatus(),
    val currentUserAuthId: Long = 0,
    val downloadManager: DownloadManager? = null,
    val loadingMessageCount: Int = 0
)

data class GetDoctorDetailsStatus(
    val data: DoctorModel? = null,
    val loading: Boolean = true,
    val doctorDeleted: Boolean = false,
)

data class FileStatus(
    val fileUri: Uri = Uri.parse(""),
    val fileSelected: Boolean = false,
    val type: String = "",
    val name: String = "",
    val size: String = "",
)


data class DownloadStatus(
    val fileSelected: Boolean = false,
    val type: String = "",
    val localName: String = "",
    val url: String = ""
)