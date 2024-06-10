package com.example.room.presentation.uiState.state

import com.twilio.video.Camera2Capturer
import com.twilio.video.LocalAudioTrack
import com.twilio.video.LocalVideoTrack
import com.twilio.video.RemoteVideoTrack
import com.twilio.video.Room

data class OnlineRoomUiState(
   val localVideoTrack: LocalVideoTrack? = null,
   val localAudioTrack: LocalAudioTrack? = null,
   val localAudioEnable: Boolean = true,
   val localVideoEnable: Boolean = true,
   val remoteVideoTrack: RemoteVideoTrack? = null,
   val cameraCapturer: Camera2Capturer? = null,
   val cameraPosition: Boolean = true,
   val room: Room? = null
)
