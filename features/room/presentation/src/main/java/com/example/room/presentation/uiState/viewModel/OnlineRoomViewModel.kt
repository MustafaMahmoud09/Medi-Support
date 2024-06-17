package com.example.room.presentation.uiState.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.libraries.core.remote.data.response.status.Status
import com.example.room.domain.usecase.declarations.IFinishOnlineRoomUseCase
import com.example.room.domain.usecase.declarations.IGetOnlineRoomUseCase
import com.example.room.domain.usecase.declarations.IStartOnlineRoomUseCase
import com.example.room.presentation.uiElement.screens.room.OnlineRoomArgs
import com.example.room.presentation.uiState.state.OnlineRoomUiState
import com.example.sharedui.uiState.viewModel.BaseViewModel
import com.twilio.video.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import tvi.webrtc.Camera2Enumerator
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class OnlineRoomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getOnlineRoomUseCase: IGetOnlineRoomUseCase,
    private val startOnlineRoomUseCase: IStartOnlineRoomUseCase,
    private val finishOnlineRoomUseCase: IFinishOnlineRoomUseCase,
    @ApplicationContext private val context: Context
) : BaseViewModel() {

    //for manage screen state from view model
    private val _state = MutableStateFlow(OnlineRoomUiState())

    //for observe by screen
    val state = _state.asStateFlow()

    //get booking arguments here
    private val arguments: OnlineRoomArgs =
        OnlineRoomArgs(
            savedStateHandle
        )

    init {
        _state.update {
            it.copy(
                bookingId = arguments.bookingId
            )
        }//end update
        onGenerateVideoCallTimer()
    }//end init


    //function for make video call timer
    private fun onGenerateVideoCallTimer() {

        viewModelScope.launch(Dispatchers.IO) {

            while (true) {

                if (state.value.remoteVideoTrack != null) {

                    val timer = state.value.timer.plusSeconds(1)

                    _state.update {
                        it.copy(
                            timer = timer,
                            timerFormatter = formatLocalTime(timer, "HH:mm:ss"),
                        )
                    }

                }//end if
                delay(1000)
            }//end while

        }//end coroutine builder scope

    }//end onGenerateVideoCallTimer


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
        }//end update

        onGetOnlineRoomToken()

    }//end onInitializeLocalVideoComponent

    //function for make request on use case for get room token
    private fun onGetOnlineRoomToken() {

        //create coroutine builder scope here
        viewModelScope.launch(Dispatchers.IO) {

            try {

                //make add blood pressure record use case here
                //observe use case flow
                //collect add blood pressure record status
                getOnlineRoomUseCase(
                    bookingId = state.value.bookingId.toLong(),
                ).collectLatest { status ->

                    when (status) {

                        is Status.Success -> {

                            when (status.toData()?.statusCode) {

                                200 -> {
                                    _state.update {
                                        it.copy(
                                            onlineRoomStatus = state.value
                                                .onlineRoomStatus.copy(
                                                    success = true,
                                                    loading = false,
                                                    onlineRoom = status.toData()?.body?.get(0)!!
                                                )
                                        )
                                    }//end update

                                    //make online room here
                                    connectToRoom()
                                }//end success case

                                404, 500 -> {
                                    _state.update {
                                        it.copy(
                                            onlineRoomStatus = state.value
                                                .onlineRoomStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.onlineRoomStatus.serverError
                                                )
                                        )
                                    }//end update
                                }//end error server case

                                400 -> {

                                    _state.update {
                                        it.copy(
                                            onlineRoomStatus = state.value
                                                .onlineRoomStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    onlineRoomNotCompleted = !state.value.onlineRoomStatus.onlineRoomNotCompleted
                                                )
                                        )
                                    }//end update

                                }//end appointment not valid case

                            }//end when

                        }//end success case

                        is Status.Loading -> {

                            _state.update {
                                it.copy(
                                    onlineRoomStatus = state.value
                                        .onlineRoomStatus.copy(
                                            success = false,
                                            loading = true
                                        )
                                )
                            }//end update

                        }//end loading case

                        is Status.Error -> {

                            when (status.status) {

                                400 -> {

                                    _state.update {
                                        it.copy(
                                            onlineRoomStatus = state.value
                                                .onlineRoomStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    internetError = !state.value.onlineRoomStatus.internetError
                                                )
                                        )
                                    }//end update

                                }//end internet error case

                                500 -> {

                                    _state.update {
                                        it.copy(
                                            onlineRoomStatus = state.value
                                                .onlineRoomStatus.copy(
                                                    success = false,
                                                    loading = false,
                                                    serverError = !state.value.onlineRoomStatus.serverError
                                                )
                                        )
                                    }//end update

                                }//end server error case

                            }//end when

                        }//end error case

                    }//end when

                }//end collectLatest

            }//end try
            catch (ex: IOException) {

                _state.update {
                    it.copy(
                        onlineRoomStatus = state.value
                            .onlineRoomStatus.copy(
                                success = false,
                                loading = false,
                                internetError = !state.value.onlineRoomStatus.internetError
                            )
                    )
                }//end update

            }//end catch

        }//end coroutine builder scope

    }//end onGetOnlineRoomToken


    //function for make connect with room
    private fun connectToRoom() {

        //make connection option object here
        val connectOptions = ConnectOptions.Builder(
            state.value.onlineRoomStatus.onlineRoom.token
        ).roomName(state.value.onlineRoomStatus.onlineRoom.roomName)
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
                _state.update {
                    it.copy(
                        doctorName = participant.identity
                    )
                }//end update
                room.remoteParticipants.firstOrNull()?.setListener(remoteParticularListener())
            }

            override fun onParticipantDisconnected(room: Room, participant: RemoteParticipant) {
                Log.d("TAG", "onParticipantDisconnected")
                _state.update {
                    it.copy(
                        doctorName = "",
                        remoteVideoTrack = null
                    )
                }//end update
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
                        remoteVideoTrack = p2,
                        particularExist = true
                    )
                }//end update

                //make request for start video call here
                onStartVideoCall()
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

    //function for make request for start video call
    private fun onStartVideoCall() {

        viewModelScope.launch(Dispatchers.IO) {

            try {

                startOnlineRoomUseCase(
                    callId = state.value.onlineRoomStatus.onlineRoom.callId
                ).collectLatest {

                }//end collectLatest

            }//end try
            catch (ex: Exception) {
            }

        }//end viewModelScope

    }//end onStartVideoCall


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

        onFinishVideoCall()

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

    override fun onCleared() {
        super.onCleared()
        onFinishVideoCall()
    }//end onCleared

    //function for make finish for video call
    private fun onFinishVideoCall() {

        if (state.value.particularExist) {

            viewModelScope.launch(Dispatchers.IO) {

                try {

                    finishOnlineRoomUseCase(
                        bookingId = state.value.bookingId.toLong(),
                        callId = state.value.onlineRoomStatus.onlineRoom.callId
                    ).collectLatest {

                    }//end collectLatest

                }//end try
                catch (ex: Exception) {
                }//end catch

            }//end coroutine builder scope

        }//end if

    }//end onFinishVideoCall

}//end OnlineRoomViewModel