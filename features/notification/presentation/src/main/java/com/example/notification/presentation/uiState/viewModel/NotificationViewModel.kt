package com.example.notification.presentation.uiState.viewModel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.notification.domain.model.NotificationModel
import com.example.notification.domain.usecase.declarations.IGetPageNotificationsUseCase
import com.example.notification.domain.usecase.declarations.IReadAllNotificationUseCase
import com.example.notification.domain.usecase.declarations.IReadNotificationUseCase
import com.example.notification.pagination.NotificationDataSource
import com.example.notification.presentation.uiState.state.NotificationUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getPageNotificationsUseCase: IGetPageNotificationsUseCase,
    private val readNotificationUseCase: IReadNotificationUseCase,
    private val readAllNotificationUseCase: IReadAllNotificationUseCase
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(NotificationUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    init {
        onGetNotification()
    }//end init

    //function for get total offline doctors
    private fun onGetNotification() {

        //get total offline doctors flow here
        val notifications = Pager(
            config = PagingConfig(
                pageSize = 10
            )
        ) {
            NotificationDataSource(
                getPageNotificationsUseCase = getPageNotificationsUseCase
            )
        }.flow
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)

        //change total offline doctor state here
        _state.update {
            it.copy(
                totalNotificationStatus = notifications
            )
        }//end update

    }//end onGetTotalOnlineDoctors


    //function for make read on single notification
    fun onReadNotificationById(id: String, read: Boolean) {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                if (!read) {

                    //update read notification in local mobile data
                    val notifications = state.value.totalNotificationStatus?.map { notifications ->
                        notifications.map { notification ->
                            if (notification.id == id) {
                                NotificationModel(
                                    id = notification.id,
                                    notification = notification.notification,
                                    read = true,
                                    bookingId = notification.bookingId,
                                    type = notification.type
                                )
                            } else {
                                notification
                            }
                        }//end map
                    }//end map

                    //update total notification by notifications after updated
                    _state.update {
                        it.copy(
                            totalNotificationStatus = notifications
                        )
                    }//end update

                    readNotificationUseCase(
                        notificationId = id
                    ).collectLatest {}

                }//end if

            } catch (ex: Exception) {
            }

        }//end viewModelScope

    }//end onReadNotificationById


    //function for make read all notification
    fun onReadAllNotification() {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                //update read notification in local mobile data
                val notifications = state.value.totalNotificationStatus?.map { notifications ->
                    notifications.map { notification ->
                        NotificationModel(
                            id = notification.id,
                            notification = notification.notification,
                            read = true,
                            bookingId = notification.bookingId,
                            type = notification.type
                        )
                    }//end map
                }//end map

                //update total notification by notifications after updated
                _state.update {
                    it.copy(
                        totalNotificationStatus = notifications
                    )
                }//end update

                readAllNotificationUseCase().collectLatest {}

            }//end try
            catch (ex: Exception) { }

        }//end viewModelScope

    }//end onReadAllNotification


    //function for create notification backup
    fun onNotificationBackupCreated() {

        //update backup notification by total notification
        _state.update {
            it.copy(
                backupTotalNotificationStatus = state.value.totalNotificationStatus
            )
        }//end update

    }//end onNotificationBackupCreated

}//end NotificationViewModel