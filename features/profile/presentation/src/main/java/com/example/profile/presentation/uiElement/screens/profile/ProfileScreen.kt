package com.example.profile.presentation.uiElement.screens.profile

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharedui.R
import com.example.profile.presentation.uiElement.components.items.UserProfileSection
import com.example.profile.presentation.uiState.state.ProfileUiState
import com.example.profile.presentation.uiState.viewModel.ProfileViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.items.BasicFieldSection
import com.example.sharedui.uiElement.components.items.HeaderSection
import com.example.sharedui.uiElement.components.items.TransparentDialogSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    popProfileDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    //create gallery launcher here
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri -> viewModel.onImageUriChanged(uri) }
    )

    ProfileContent(
        onClickBack = popProfileDestination,
        uiState = state.value,
        onPasswordChanged = viewModel::onPasswordChanged,
        onPasswordConfirmationChanged = viewModel::onPasswordConfirmationChanged,
        onFirstNameChanged = viewModel::onFirstNameChanged,
        onLastNameChanged = viewModel::onLastNameChanged,
        onClickOnProfileEditButton = { galleryLauncher.launch("image/*") },
    )
}//end ProfileScreen

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
) {

    var stateDialog by rememberSaveable {
        mutableStateOf(false)
    }

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
            visible = stateDialog,
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
            text = uiState.userName,
            size = dimen.dimen_2_25,
            fontColor = theme.black,
            modifier = Modifier
                .constrainAs(name) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        profile.bottom,
                        dimen.dimen_2.dp
                    )
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
                        com.example.sharedui.R.string.password
                    ),
                    hint = stringResource(
                        com.example.sharedui.R.string.your_password
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
                    onClick = { stateDialog = !stateDialog },
                    modifier = Modifier
                        .fillMaxWidth()
                )

            }//end item

        }//end LazyColumn

    }//end ConstraintLayout

    LaunchedEffect(key1 = stateDialog) {

        if (stateDialog) {
            delay(1000)
            stateDialog = false
        }//end if

    }//end LaunchedEffect

}//end ProfileContent