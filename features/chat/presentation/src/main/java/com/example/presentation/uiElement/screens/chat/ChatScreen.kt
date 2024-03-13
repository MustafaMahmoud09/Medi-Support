@file:OptIn(ExperimentalLayoutApi::class, ExperimentalComposeUiApi::class)

package com.example.presentation.uiElement.screens.chat

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.presentation.uiElement.components.items.GuestUserMessageSection
import com.example.presentation.uiElement.components.items.OwnerUserMessageSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun ChatScreen(
    popChatDestination: () -> Unit
) {
    //get keyboard visible
    val isKeyboardVisible = WindowInsets.isImeVisible

    val keyboardController = LocalSoftwareKeyboardController.current

    //create files launcher here
    val filesLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents(),
        onResult = {}
    )

    //create camera launcher here
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { check ->

        }
    )

    ChatContent(
        onClickOnBackButton = {

            //if keyboard is visible close keyboard
            if (isKeyboardVisible) {
                keyboardController!!.hide()
            }//end if

            //else pop destination from back stack
            else {
                popChatDestination()
            }//end else

        },
        keyboardIsVisible = isKeyboardVisible,
        onClickOnOpenFileButton = {

            filesLauncher.launch("*/*")
        },
        onClickOnOpenCameraButton = {
            cameraLauncher.launch(Uri.parse(""))
        }
    )
}//end ChatScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun ChatContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    keyboardIsVisible: Boolean,
    onClickOnBackButton: () -> Unit,
    onClickOnOpenFileButton: () -> Unit,
    onClickOnOpenCameraButton: () -> Unit,
) {

    var value by remember {
        mutableStateOf("")
    }
    //create base screen to define navigation bar and status bar color
    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Scaffold(
            topBar = {

                TopAppBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    backgroundColor = theme.background,
                    contentPadding = PaddingValues(
                        bottom = dimen.dimen_3.dp
                    )
                ) {

                    //create top bar content here
                    ChatTopBarContent(
                        dimen = dimen,
                        theme = theme,
                        onClickOnBackButton = onClickOnBackButton
                    )

                }//end TopAppBar

            },//end topBar
            modifier = Modifier
                .appDefaultContainer(
                    color = theme.background
                )
        ) {

            //padding from top screen = padding profile photo from top
            // + height profile photo
            // + padding profile photo from bottom
            // == 21 + 44 + 24
            //create container here
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
                    .padding(
                        top = (
                                dimen.dimen_2_5 +
                                        dimen.dimen_0_125 +
                                        dimen.dimen_4_5 +
                                        dimen.dimen_3
                                ).dp
                    )
            ) {
                //create ids for screen components here
                val (chatToolsId, messagesContainerId) = createRefs()

                //create chat tools section here
                com.example.presentation.uiElement.components.items.ChatToolsSection(
                    dimen = dimen,
                    theme = theme,
                    editTextValue = value,
                    onEditTextChanged = { it -> value = it },
                    hint = stringResource(
                        id = R.string.write_your_message
                    ),
                    onClickOnFileButton = onClickOnOpenFileButton,
                    onClickOnStickerButton = {},
                    onClickOnCameraButton = onClickOnOpenCameraButton,
                    onClickOnSoundButton = {},
                    onClickOnSendButton = {},
                    keyboardIsVisible = keyboardIsVisible,
                    modifier = Modifier
                        .constrainAs(chatToolsId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        }
                )

                //create column contain on all chat messages
                LazyColumn(
                    modifier = Modifier
                        .constrainAs(messagesContainerId) {
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(chatToolsId.top)
                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        },
                    contentPadding = PaddingValues(
                        horizontal = dimen.dimen_2.dp,
                        vertical = dimen.dimen_1.dp
                    )
                ) {

                    items(
                        count = 22
                    ) {

                        if (it % 2 == 0 && it <= 9) {

                            OwnerUserMessageSection(
                                dimen = dimen,
                                theme = theme,
                                message = "hatGPT is a free-to-use AI system. Use it for engaging conversations, gain insights, hatGPT is a free-to-use AI system. Use it for engaging conversations, gain insights",
                                isEndMessage = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }//end if

                        else if (it % 2 == 1 && it <= 9) {

                            GuestUserMessageSection(
                                dimen = dimen,
                                theme = theme,
                                message = "Chat is an intelligent and secure communication and collaboration tool, built for teams. From ad-hoc messaging to topic-based workstream ",
                                isEndMessage = true,
                                isStartMessage = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        } else if (it in 10..15) {

                            OwnerUserMessageSection(
                                dimen = dimen,
                                theme = theme,
                                message = "conversations, gain insights, hatGPT is a free-to-use AI system.",
                                isEndMessage = it == 15,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        } else if (it in 16..20) {

                            GuestUserMessageSection(
                                dimen = dimen,
                                theme = theme,
                                message = "communication and collaboration tool, built for teams. From ad-hoc messaging to topic-based workstream ",
                                isEndMessage = it == 20,
                                isStartMessage = it == 16,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        } else {

                            OwnerUserMessageSection(
                                dimen = dimen,
                                theme = theme,
                                message = "communication and collaboration tool, built for teams. From ad-hoc messaging to topic-based workstream ",
                                isEndChatMessage = true,
                                isEndMessage = true,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )

                        }

                    }


                }//end LazyColumn

            }//end ConstraintLayout

        }//end BaseScreen

    }//end Scaffold

}//end ChatContent

@Composable
private fun ChatTopBarContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnBackButton: () -> Unit
) {

    //create container here
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        //create ids for screen components here
        val (backButtonId, userActiveId) = createRefs()

        //create other user in top screen here
        com.example.presentation.uiElement.components.items.UserActiveSection(
            dimen = dimen,
            theme = theme,
            modifier = Modifier
                .constrainAs(userActiveId) {
                    start.linkTo(
                        parent.start,
                        (dimen.dimen_3_75 + dimen.dimen_3_5).dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    top.linkTo(
                        parent.top,
                        (dimen.dimen_2_5 + dimen.dimen_0_125).dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

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
                    top.linkTo(userActiveId.top)
                    bottom.linkTo(userActiveId.bottom)
                }
        )

    }//end ConstraintLayout

}//end ChatTopBarContent