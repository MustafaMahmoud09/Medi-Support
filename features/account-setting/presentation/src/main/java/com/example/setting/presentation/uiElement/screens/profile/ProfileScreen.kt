@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.setting.presentation.uiElement.screens.profile

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.setting.presentation.uiElement.components.items.UserProfileSection
import com.example.setting.presentation.uiState.state.ProfileUiState
import com.example.setting.presentation.uiState.viewModel.ProfileViewModel
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.TransparentDialogSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.delay
import kotlin.reflect.KFunction0

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    popProfileDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }

    val latestVersionsMediaPermissionState =
        rememberPermissionState(permission = android.Manifest.permission.READ_EXTERNAL_STORAGE)

    val android33MediaPermissionState =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.READ_MEDIA_VIDEO,
                    android.Manifest.permission.READ_MEDIA_VIDEO
                )
            )
        } else {
            TODO("VERSION.SDK_INT < TIRAMISU")
        }


    val android34MediaPermissionState =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            rememberMultiplePermissionsState(
                permissions = listOf(
                    android.Manifest.permission.READ_MEDIA_VISUAL_USER_SELECTED,
                    android.Manifest.permission.READ_MEDIA_VIDEO,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                )
            )
        } else {
            TODO("VERSION.SDK_INT < UPSIDE_DOWN_CAKE")
        }

    val startRunning = remember {
        mutableStateOf(true)
    }

    //create gallery launcher here
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> viewModel.onImageUriChanged(uri) }
    )
    val imageFormatErrorMessage =
        stringResource(R.string.please_use_png_or_jpeg_or_jpg_image_formats)

    val internetError =
        stringResource(R.string.the_device_is_not_connected_to_the_internet)

    val serverError =
        stringResource(R.string.there_is_a_problem_with_the_server)

    val inputsError =
        stringResource(R.string.there_is_a_problem_with_the_entered_data)

    ProfileContent(
        onClickBack = popProfileDestination,
        uiState = state.value,
        onPasswordChanged = viewModel::onPasswordChanged,
        onPasswordConfirmationChanged = viewModel::onPasswordConfirmationChanged,
        onFirstNameChanged = viewModel::onFirstNameChanged,
        onLastNameChanged = viewModel::onLastNameChanged,
        onClickOnProfileEditButton =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            if (android34MediaPermissionState.allPermissionsGranted) {
                { galleryLauncher.launch("image/*") }
            } else {
                { android34MediaPermissionState.launchMultiplePermissionRequest() }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (android33MediaPermissionState.allPermissionsGranted) {
                { galleryLauncher.launch("image/*") }
            } else {
                { android33MediaPermissionState.launchMultiplePermissionRequest() }
            }
        } else {
            if (latestVersionsMediaPermissionState.status.isGranted) {
                { galleryLauncher.launch("image/*") }
            } else {
                { latestVersionsMediaPermissionState.launchPermissionRequest() }
            }
        },
        snackbarHostState = snackbarHostState,
        onClickOnUpdateButton = viewModel::onUpdateProfileInfo
    )

    LaunchedEffect(
        key1 = state.value.errorTypeMimeSelected
    ) {

        if (!startRunning.value) {
            snackbarHostState.showSnackbar(imageFormatErrorMessage)
        }//end if

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.updateProfileEventStatus.internetError,
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = internetError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.updateProfileEventStatus.serverError,
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = serverError
            )

        }

    }//end LaunchedEffect

    //if register event status is email not valid
    //show snack bar contain on error message
    LaunchedEffect(
        key1 = state.value.updateProfileEventStatus.dataNotFound,
        key2 = state.value.updateProfileEventStatus.dataError
    ) {

        if (!startRunning.value) {

            //show email snack bar here
            snackbarHostState.showSnackbar(
                message = inputsError
            )

        }

    }//end LaunchedEffect

    LaunchedEffect(key1 = true) {
        delay(250)
        startRunning.value = false
    }

}//end ProfileScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ProfileContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    uiState: ProfileUiState,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmationChanged: (String) -> Unit,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onClickOnProfileEditButton: () -> Unit,
    snackbarHostState: SnackbarHostState,
    onClickOnUpdateButton: KFunction0<Unit>,
) {

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
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
            ) {
                //create ids for screen components here
                val (headerId, profile, name, editInfo, fields) = createRefs()

                //create success update profile dialog here
                AnimatedVisibility(
                    visible = uiState.updateProfileEventStatus.success,
                    enter = fadeIn(
                        animationSpec = tween(
                            durationMillis = 100
                        )
                    ),
                    exit = fadeOut(
                        animationSpec = tween(
                            durationMillis = 100
                        )
                    )
                ) {

                    TransparentDialogSection(
                        onDismissRequest = { /*TODO*/ },
                        dimen = dimen,
                        theme = theme,
                        logo = painterResource(
                            id = R.drawable.success_bold
                        ),
                        tint = theme.green,
                        modifier = Modifier
                            .fillMaxSize()
                    )

                }


                //create header section here
                HeaderSection(
                    dimen = dimen,
                    theme = theme,
                    onClickOnBackButton = onClickBack,
                    title = stringResource(
                        R.string.profile
                    ),
                    modifier = Modifier
                        .constrainAs(headerId) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                parent.top,
                                dimen.dimen_3.dp
                            )
                            width = Dimension.fillToConstraints
                        }
                )

                //create profile image user section here
                UserProfileSection(
                    theme = theme,
                    dimen = dimen,
                    painter = uiState.imageUri,
                    urlLoad = uiState.userInfo?.image ?: "",
                    imageUpdated = uiState.imageUpdated,
                    onClick = onClickOnProfileEditButton,
                    modifier = Modifier
                        .constrainAs(profile) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                headerId.bottom,
                                dimen.dimen_4.dp
                            )
                        }//end constrainAs
                )

                //create name user here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = uiState.userInfo?.name ?: "",
                    size = dimen.dimen_2_25,
                    fontColor = theme.black,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .constrainAs(name) {
                            start.linkTo(
                                parent.start,
                                dimen.dimen_2.dp
                            )
                            end.linkTo(
                                parent.end,
                                dimen.dimen_2.dp
                            )
                            top.linkTo(
                                profile.bottom,
                                dimen.dimen_2.dp
                            )
                            width = Dimension.fillToConstraints
                        }//end constrainAs
                )

                //create edit info text here
                TextSemiBoldView(
                    theme = theme,
                    dimen = dimen,
                    text = stringResource(
                        R.string.edit_info
                    ),
                    size = dimen.dimen_1_75,
                    fontColor = theme.hintIconBottom,
                    modifier = Modifier
                        .constrainAs(editInfo) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                name.bottom,
                                dimen.dimen_2.dp
                            )
                        }//end constrainAs
                )

                //create column contain on fields to update profile data
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(fields) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(
                                editInfo.bottom,
                                dimen.dimen_2.dp
                            )
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                            height = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        bottom = dimen.dimen_2.dp,
                        start = dimen.dimen_2.dp,
                        end = dimen.dimen_2.dp,
                        top = dimen.dimen_2.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(
                        dimen.dimen_2.dp
                    )
                ) {

                    //create full name field here
                    item(
                        key = 1
                    ) {

                        //create user name container here
                        ConstraintLayout(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            //create ids for components here
                            val (lastNameId, firstNameId) = createRefs()

                            //create guides here
                            val guideLineFromStart50P = createGuidelineFromStart(.5f)

                            //create first name field here
                            BasicFieldSection(
                                theme = theme,
                                dimen = dimen,
                                title = stringResource(
                                    R.string.first_name
                                ),
                                hint = stringResource(
                                    R.string.fname
                                ),
                                value = uiState.firstNameValue,
                                onChange = onFirstNameChanged,
                                modifier = Modifier
                                    .constrainAs(firstNameId) {
                                        end.linkTo(
                                            guideLineFromStart50P,
                                            dimen.dimen_1.dp
                                        )
                                        start.linkTo(parent.start)
                                        top.linkTo(parent.top)
                                        width = Dimension.fillToConstraints
                                    }//end constrainAs
                            )

                            //create last name field here
                            BasicFieldSection(
                                theme = theme,
                                dimen = dimen,
                                title = stringResource(
                                    R.string.last_name
                                ),
                                hint = stringResource(
                                    R.string.lname
                                ),
                                value = uiState.lastNameValue,
                                onChange = onLastNameChanged,
                                modifier = Modifier
                                    .constrainAs(lastNameId) {
                                        start.linkTo(
                                            guideLineFromStart50P,
                                            dimen.dimen_1.dp
                                        )
                                        end.linkTo(parent.end)
                                        top.linkTo(parent.top)
                                        width = Dimension.fillToConstraints
                                    }//end constrainAs
                            )

                        }//end ConstraintLayout

                    }//end item

                    //create password field here
                    item(
                        key = 2
                    ) {

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.password
                            ),
                            hint = stringResource(
                                R.string.your_password
                            ),
                            value = uiState.passwordValue,
                            fieldIsPassword = true,
                            onChange = onPasswordChanged,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    //create confirm password field here
                    item(
                        key = 3
                    ) {

                        BasicFieldSection(
                            theme = theme,
                            dimen = dimen,
                            title = stringResource(
                                R.string.confirm_password
                            ),
                            hint = stringResource(
                                R.string.your_password
                            ),
                            value = uiState.passwordConfirmPassword,
                            fieldIsPassword = true,
                            onChange = onPasswordConfirmationChanged,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                    //create save button here
                    item(
                        key = 4
                    ) {

                        BasicButtonView(
                            dimen = dimen,
                            theme = theme,
                            text = stringResource(
                                R.string.save
                            ),
                            onClick = if (!uiState.updateProfileEventStatus.loading) {
                                onClickOnUpdateButton
                            } else {
                                {}
                            },
                            load = uiState.updateProfileEventStatus.loading,
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                    }//end item

                }//end LazyColumn

            }//end ConstraintLayout

        }//end Scaffold

    }//end BaseScreen

}//end ProfileContent