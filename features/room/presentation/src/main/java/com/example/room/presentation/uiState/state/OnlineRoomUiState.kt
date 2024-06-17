package com.example.room.presentation.uiState.state

import com.example.domain_model.OnlineRoomModel
import com.twilio.video.Camera2Capturer
import com.twilio.video.LocalAudioTrack
import com.twilio.video.LocalVideoTrack
import com.twilio.video.RemoteVideoTrack
import com.twilio.video.Room
import java.time.LocalTime

data class OnlineRoomUiState(
    val localVideoTrack: LocalVideoTrack? = null,
    val localAudioTrack: LocalAudioTrack? = null,
    val localAudioEnable: Boolean = true,
    val localVideoEnable: Boolean = true,
    val remoteVideoTrack: RemoteVideoTrack? = null,
    val cameraCapturer: Camera2Capturer? = null,
    val cameraPosition: Boolean = true,
    val room: Room? = null,
    val bookingId: Int = -1,
    val particularExist: Boolean = false,
    val timer: LocalTime = LocalTime.of(0,0,0),
    val timerFormatter: String = "",
    val doctorName: String = "",
    val onlineRoomStatus: OnlineRoomStatus = OnlineRoomStatus()
)

data class OnlineRoomStatus(
    val success: Boolean = false,
    val loading: Boolean = false,
    val internetError: Boolean = false,
    val serverError: Boolean = false,
    val onlineRoomNotCompleted: Boolean = false,
    val onlineRoom: OnlineRoomModel = OnlineRoomModel(
        token = "",
        roomName = "",
        callId = 0
    )
)
