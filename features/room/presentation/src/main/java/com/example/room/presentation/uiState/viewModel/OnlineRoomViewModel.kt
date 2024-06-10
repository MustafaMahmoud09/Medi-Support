package com.example.room.presentation.uiState.viewModel

import android.content.Context
import android.util.Log
import com.example.room.presentation.uiState.state.OnlineRoomUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.twilio.video.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import tvi.webrtc.Camera2Enumerator
import javax.inject.Inject


@HiltViewModel
class OnlineRoomViewModel @Inject constructor(
    private val context: Context
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OnlineRoomUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //function for make connect with room
    private fun connectToRoom(roomName: String) {

        //make connection option object here
        val connectOptions = ConnectOptions.Builder(
            "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiIsImN0eSI6InR3aWxpby1mcGE7dj0xIn0.eyJqdGkiOiJTSzNjODcyYTJlZmE4NDY1MWNkYmQ3Y2M1YmEwNGVlYjkyLTE3MTc5OTE0MTMiLCJpc3MiOiJTSzNjODcyYTJlZmE4NDY1MWNkYmQ3Y2M1YmEwNGVlYjkyIiwic3ViIjoiQUM4NjQyNDAwYWE3Zjk3NjE5Zjc3NzAzMTI4NjAxYTU4MSIsImV4cCI6MTcxNzk5ODYxMywiZ3JhbnRzIjp7ImlkZW50aXR5IjoibXVzdGFmYW1haG1vdWRAZ21haWwuY29tIiwidmlkZW8iOnsicm9vbSI6Im15X3Jvb20ifX19.4zSgCE97u5x41mQy2oV8_1bi8SWl11YaYOX4ieBsxXM"
        ).roomName(roomName)
            .audioTracks(listOf(state.value.localAudioTrack))
            .videoTracks(listOf(state.value.localVideoTrack))
            .build()

        //make connection with room here
        val room = Video.connect(context, connectOptions, roomListener())

        //update room state here
        _state.update {
            it.copy(
                room = room
            )
        }//end update

    }//end connectToRoom


    //function for make listener on room
    private fun roomListener(): Room.Listener {
        return object : Room.Listener {
            override fun onConnected(room: Room) {
                Log.d("TAG", "onConnected")
                room.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onConnectFailure(p0: Room, p1: TwilioException) {
                Log.d("TAG", p1.message.toString())
                p0.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onReconnecting(p0: Room, p1: TwilioException) {
                Log.d("TAG", "onReconnecting")
                p0.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onReconnected(p0: Room) {
                Log.d("TAG", "onReconnected")
                p0.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onDisconnected(p0: Room, p1: TwilioException?) {
                Log.d("TAG", "onDisconnected")
                p0.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onParticipantConnected(room: Room, participant: RemoteParticipant) {
                Log.d("TAG", "onParticipantConnected")
                room.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onParticipantDisconnected(room: Room, participant: RemoteParticipant) {
                Log.d("TAG", "onParticipantDisconnected")
                room.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onRecordingStarted(p0: Room) {
                Log.d("TAG", "onRecordingStarted")
                p0.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onRecordingStopped(p0: Room) {
                Log.d("TAG", "onRecordingStopped")
                p0.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }
        }
    }//end roomListener


    //function for make remote particular listener
    private fun remoteParticularListener(): RemoteParticipant.Listener {

        return object : RemoteParticipant.Listener {

            override fun onAudioTrackPublished(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication
            ) {
                Log.d("TAG", "onAudioTrackPublished")
            }//end onAudioTrackPublished

            override fun onAudioTrackUnpublished(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication
            ) {
                Log.d("TAG", "onAudioTrackUnpublished")
            }//end onAudioTrackUnpublished

            override fun onAudioTrackSubscribed(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication,
                remoteAudioTrack: RemoteAudioTrack
            ) {
                Log.d("TAG", "onAudioTrackSubscribed")
                remoteAudioTrack.enablePlayback(true)

            }//end onAudioTrackSubscribed

            override fun onAudioTrackSubscriptionFailed(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication,
                p2: TwilioException
            ) {
                Log.d("TAG", "onAudioTrackSubscriptionFailed")
            }//end onAudioTrackSubscriptionFailed

            override fun onAudioTrackUnsubscribed(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication,
                p2: RemoteAudioTrack
            ) {
                Log.d("TAG", "onAudioTrackUnsubscribed")
            }//end onAudioTrackUnsubscribed

            override fun onVideoTrackPublished(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication
            ) {
                Log.d("TAG", "onVideoTrackPublished")
            }//end onVideoTrackPublished

            override fun onVideoTrackUnpublished(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication
            ) {
                Log.d("TAG", "onVideoTrackUnpublished")
            }//end onVideoTrackUnpublished

            override fun onVideoTrackSubscribed(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication,
                p2: RemoteVideoTrack
            ) {
                Log.d("TAG", "onVideoTrackSubscribed")
                //update remote video track by p2
                _state.update {
                    it.copy(
                        remoteVideoTrack = p2
                    )
                }//end update

            }//end onVideoTrackSubscribed

            override fun onVideoTrackSubscriptionFailed(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication,
                p2: TwilioException
            ) {
                Log.d("TAG", "onVideoTrackSubscriptionFailed")
            }//end onVideoTrackSubscriptionFailed

            override fun onVideoTrackUnsubscribed(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication,
                p2: RemoteVideoTrack
            ) {
                Log.d("TAG", "onVideoTrackUnsubscribed")
                //update remote video track by null
                _state.update {
                    it.copy(
                        remoteVideoTrack = null
                    )
                }//end update

            }//end onVideoTrackUnsubscribed

            override fun onDataTrackPublished(
                p0: RemoteParticipant,
                p1: RemoteDataTrackPublication
            ) {
                Log.d("TAG", "onDataTrackPublished")
            }//end onDataTrackPublished

            override fun onDataTrackUnpublished(
                p0: RemoteParticipant,
                p1: RemoteDataTrackPublication
            ) {
                Log.d("TAG", "onDataTrackUnpublished")
            }//end onDataTrackUnpublished

            override fun onDataTrackSubscribed(
                p0: RemoteParticipant,
                p1: RemoteDataTrackPublication,
                p2: RemoteDataTrack
            ) {
                Log.d("TAG", "onDataTrackSubscribed")
            }//end onDataTrackSubscribed

            override fun onDataTrackSubscriptionFailed(
                p0: RemoteParticipant,
                p1: RemoteDataTrackPublication,
                p2: TwilioException
            ) {
                Log.d("TAG", "onDataTrackSubscriptionFailed")
            }//end onDataTrackSubscriptionFailed

            override fun onDataTrackUnsubscribed(
                p0: RemoteParticipant,
                p1: RemoteDataTrackPublication,
                p2: RemoteDataTrack
            ) {
                Log.d("TAG", "onDataTrackUnsubscribed")
            }//end onDataTrackUnsubscribed

            override fun onAudioTrackEnabled(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication
            ) {
                Log.d("TAG", "onAudioTrackEnabled")
            }//end onAudioTrackEnabled

            override fun onAudioTrackDisabled(
                p0: RemoteParticipant,
                p1: RemoteAudioTrackPublication
            ) {
                Log.d("TAG", "onAudioTrackDisabled")
            }//end onAudioTrackDisabled

            override fun onVideoTrackEnabled(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication
            ) {
                Log.d("TAG", "onVideoTrackEnabled")
            }//end onVideoTrackEnabled

            override fun onVideoTrackDisabled(
                p0: RemoteParticipant,
                p1: RemoteVideoTrackPublication
            ) {
                Log.d("TAG", "onVideoTrackDisabled")
            }//end onVideoTrackDisabled

        }//end RemoteParticipant enums class

    }//end remoteParticularListener


    //function for initialize local video object
    fun onInitializeLocalVideoComponent() {

        // A video track requires an implementation of a VideoCapturer. Here's how to use the front camera with a Camera2Capturer.
        val camera2Enumerator = Camera2Enumerator(context)
        var frontCameraId: String? = null
        for (cameraId in camera2Enumerator.deviceNames) {
            if (camera2Enumerator.isFrontFacing(cameraId)) {
                frontCameraId = cameraId
                break
            }//end if
        }//end for

        if (frontCameraId != null) {

            // Create the CameraCapturer with the front camera
            val cameraCapturer = Camera2Capturer(context, frontCameraId)

            //update camera capturer status
            _state.update {
                it.copy(
                    cameraCapturer = cameraCapturer
                )
            }//end update

            // Create a video track
            val localVideoTrack =
                state.value.cameraCapturer?.let { LocalVideoTrack.create(context, true, it) }

            // update local video track here
            _state.update {
                it.copy(
                    localVideoTrack = localVideoTrack
                )
            }//end update

        }//end if

        //generate local audio track
        val localAudioTrack = LocalAudioTrack.create(context, true)!!

        //update local audio track here
        _state.update {
            it.copy(
                localAudioTrack = localAudioTrack
            )
        }

        connectToRoom(
            roomName = "my_room"
        )

    }//end onInitializeLocalVideoComponent


    //function for change local video enable status
    fun onChangeLocalVideoEnableStatus() {

        //update local video enable status here
        _state.update {
            it.copy(
                localVideoEnable = !state.value.localVideoEnable
            )
        }//end update

        //change local video enable here
        state.value.localVideoTrack?.enable(state.value.localVideoEnable)

    }//end onChangeLocalVideoEnableStatus


    //function for change local audio enable status here
    fun onChangeLocalAudioEnableStatus() {

        //update local audio enable status here
        _state.update {
            it.copy(
                localAudioEnable = !state.value.localAudioEnable
            )
        }//end update

        //change local audio enable here
        state.value.localAudioTrack?.enable(state.value.localAudioEnable)

    }//end onChangeLocalAudioEnableStatus


    //function for rotate camera from position to other position
    fun onRotateCamera() {

        val camera2Enumerator = Camera2Enumerator(context)

        _state.update {
            it.copy(
                cameraPosition = !state.value.cameraPosition
            )
        }//end update

        if (!state.value.cameraPosition) {

            var backCameraId: String? = null
            for (cameraId in camera2Enumerator.deviceNames) {
                if (camera2Enumerator.isBackFacing(cameraId)) {
                    backCameraId = cameraId
                    break
                }//end if
            }//end for

            backCameraId?.let { state.value.cameraCapturer?.switchCamera(it) }

        }//end if
        else {

            var frontCameraId: String? = null
            for (cameraId in camera2Enumerator.deviceNames) {
                if (camera2Enumerator.isFrontFacing(cameraId)) {
                    frontCameraId = cameraId
                    break
                }//end if
            }//end for

            frontCameraId?.let { state.value.cameraCapturer?.switchCamera(it) }

        }//end else

    }//end onRotateCamera


    //function for close video call
    fun onCloseVideoCall() {

        state.value.room?.disconnect()

        state.value.localVideoTrack?.release()

        state.value.localAudioTrack?.release()

        state.value.cameraCapturer?.stopCapture()

        //update room status here
        _state.update {
            it.copy(
                room = null,
                localAudioTrack = null,
                localVideoTrack = null,
                cameraCapturer = null
            )
        }//end update

    }//end onCloseVideoCall

}//end OnlineRoomViewModel