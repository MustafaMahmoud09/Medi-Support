@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.heartrate.presentation.uiElement.screens.measurement

import android.annotation.SuppressLint
import androidx.camera.core.Camera
import androidx.camera.core.CameraControl
import androidx.camera.core.ImageProxy
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.heartrate.presentation.uiElement.components.composable.CameraPreviewView
import com.example.heartrate.presentation.uiElement.components.items.CalculateHeartRateSection
import com.example.heartrate.presentation.uiState.state.MeasurementHeartRateUiState
import com.example.heartrate.presentation.uiState.viewModel.measurement.MeasurementHeartRateViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState


@Composable
internal fun MeasurementHeartRateScreen(
    viewModel: MeasurementHeartRateViewModel = hiltViewModel(),
    popMeasurementHeartRateDestination: () -> Unit,
    navigateToStatisticsHeartRateDestination: () -> Unit,
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    val cameraPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.CAMERA)

    val snackbarHostState = remember { SnackbarHostState() }

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    if (state.value.isPPGTechnologySupported) {

        MeasurementHeartRateContent(
            onClickOnBackButton = popMeasurementHeartRateDestination,
            cameraPermissionState = cameraPermissionState,
            onImageAnalysed = viewModel::onImageAnalysed,
            onCameraObjectDefined = viewModel::onCameraObjectDefined,
            uiState = state.value,
            snackbarHostState = snackbarHostState
        )

    }//end if

    LaunchedEffect(
        key1 = cameraPermissionState.status.isGranted
    ) {

        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addHeartRateRecordStatus.success
    ) {

        if (state.value.addHeartRateRecordStatus.success) {
            navigateToStatisticsHeartRateDestination()
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addHeartRateRecordStatus.internetError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = internetError
            )
        }//end if

    }//end LaunchedEffect

    LaunchedEffect(
        key1 = state.value.addHeartRateRecordStatus.serverError
    ) {

        if (!state.value.startRunning) {

            snackbarHostState.showSnackbar(
                message = serverError
            )
        }//end if

    }//end LaunchedEffect

}//end MeasurementHeartRateScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun MeasurementHeartRateContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    onClickOnBackButton: () -> Unit,
    cameraPermissionState: PermissionState,
    uiState: MeasurementHeartRateUiState,
    onImageAnalysed: (ImageProxy) -> Unit,
    onCameraObjectDefined: (Camera) -> CameraControl,
    snackbarHostState: SnackbarHostState
) {

    //create base screen to set navigation and status bar color here
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }//end snack bar Host
        ) {

            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .appDefaultContainer(
                        color = theme.background
                    )
            ) {
                //create ids for screen components here
                val (backButtonId, recyclerContainerId) = createRefs()

                //create back button here
                IconButtonView(
                    dimen = dimen,
                    theme = theme,
                    onClick = onClickOnBackButton,
                    modifier = Modifier
                        .constrainAs(backButtonId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                parent.top,
                                dimen.dimen_2_5.dp
                            )
                        }
                )

                //create column contain on all components here
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(recyclerContainerId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                backButtonId.bottom,
                                dimen.dimen_2.dp
                            )
                            bottom.linkTo(parent.bottom)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        top = dimen.dimen_0_25.dp,
                        bottom = dimen.dimen_2.dp
                    )
                ) {

                    //create item contain on container here
                    item(
                        key = 1
                    ) {

                        //create container here
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            //create ids for components here
                            val (
                                heartRateIconId, calculateHeartRateId,
                                measuringTextId, messageId, cameraId
                            ) = createRefs()

                            //create guides here
                            val guideFromStart21P = createGuidelineFromStart(0.21f)
                            val guideFromEnd21P = createGuidelineFromEnd(0.21f)

                            //create heart rate calculate image here
                            Image(
                                painter = painterResource(
                                    id = R.drawable.heart_rate_calculate_image
                                ),
                                contentDescription = "image",
                                modifier = Modifier
                                    .constrainAs(heartRateIconId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_2.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_2.dp
                                        )
                                        top.linkTo(parent.top)
                                        width = Dimension.fillToConstraints
                                    }
                                    .aspectRatio(
                                        ratio = 1.33f
                                    )
                            )

                            //create calculate heart rate section here
                            CalculateHeartRateSection(
                                dimen = dimen,
                                theme = theme,
                                unit = stringResource(
                                    id = R.string.bpm
                                ),
                                load = uiState.addHeartRateRecordStatus.loading,
                                rate = "${uiState.heartRateResultValue}",
                                modifier = Modifier
                                    .constrainAs(calculateHeartRateId) {
                                        start.linkTo(guideFromStart21P)
                                        end.linkTo(guideFromEnd21P)
                                        top.linkTo(
                                            heartRateIconId.bottom,
                                            dimen.dimen_5.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

//                            //create box contain on camera
//                            Box(
//                                modifier = Modifier
//                                    .constrainAs(createRef()) {
//                                        start.linkTo(parent.start)
//                                        top.linkTo(
//                                            calculateHeartRateId.bottom,
//                                            dimen.dimen_4_75.dp
//                                        )
//                                    }
//                                    .size(
//                                        size = dimen.dimen_15.dp
//                                    )
////                                .clip(
////                                    shape = CircleShape
////                                )
//                            ) {
//
//
//                                if (uiState.imageResult != null) {
//
//                                    Image(
//                                        bitmap = uiState.imageResult.asImageBitmap(),
//                                        contentDescription = "",
//                                        modifier = Modifier
//                                            .fillMaxSize()
//                                    )
//
//                                }
//
//                            }

                            //create box contain on camera
                            Box(
                                modifier = Modifier
                                    .constrainAs(cameraId) {
                                        start.linkTo(parent.start)
                                        end.linkTo(parent.end)
                                        top.linkTo(
                                            calculateHeartRateId.bottom,
                                            dimen.dimen_4_75.dp
                                        )
                                    }
                                    .size(
                                        size = dimen.dimen_15.dp
                                    )
                                    .clip(
                                        shape = CircleShape
                                    )
                                    .background(
                                        color = theme.black
                                    )
                            ) {

                                //if camera permission state equal true
                                if (cameraPermissionState.status.isGranted) {

                                    //open camera and flash
                                    CameraPreviewView(
                                        onImageAnalyzed = onImageAnalysed,
                                        onCameraObjectDefined = onCameraObjectDefined,
                                        processCameraProvider = uiState.processCameraProvider,
                                        modifier = Modifier
                                            .fillMaxSize()
                                    )

                                }//end if

                            }//end Box

                            //create measuring text here
                            TextSemiBoldView(
                                theme = theme,
                                dimen = dimen,
                                text = stringResource(
                                    id = R.string.measuring
                                ) + if (uiState.measurementRatio == "0") {
                                    "...."
                                } else {
                                    ".(${uiState.measurementRatio}%)"
                                },
                                size = dimen.dimen_2,
                                fontColor = theme.black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .constrainAs(measuringTextId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_1.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_1.dp
                                        )
                                        top.linkTo(
                                            cameraId.bottom,
                                            dimen.dimen_3.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                            //create message text here
                            TextSemiBoldView(
                                theme = theme,
                                dimen = dimen,
                                text = if (!uiState.measurementState) {
                                    stringResource(
                                        id = R.string.calculate_heart_rate_message
                                    )
                                } else {
                                    stringResource(
                                        id = R.string.your_pulse_is_being_detected_just_wait
                                    )
                                },
                                size = dimen.dimen_1_75,
                                fontColor = theme.black,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .constrainAs(messageId) {
                                        start.linkTo(
                                            parent.start,
                                            dimen.dimen_1.dp
                                        )
                                        end.linkTo(
                                            parent.end,
                                            dimen.dimen_1.dp
                                        )
                                        top.linkTo(
                                            measuringTextId.bottom,
                                            dimen.dimen_3.dp
                                        )
                                        width = Dimension.fillToConstraints
                                    }
                            )

                        }//end ConstraintLayout

                    }//end item

                }//end LazyColumn

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end MeasurementHeartRateContent
