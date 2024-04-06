package com.example.auth.presentation.uiElement.screens.forgotten.code

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.presentation.uiElement.components.composable.CodeFieldView
import com.example.sharedui.R
import com.example.auth.presentation.uiState.state.ForgottenUiState
import com.example.auth.presentation.uiState.viewModel.ForgottenViewModel
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun CodeScreen(
    viewModel: ForgottenViewModel = hiltViewModel(),
    navigateToNewPasswordDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    CodeContent(
        onClickOnVerifyButton = viewModel::onVerifyCodeSending,
        uiState = state.value,
        onFirstCodeChanged = viewModel::onFirstCodeChanged,
        onSecondCodeChanged = viewModel::onSecondCodeChanged,
        onThirdCodeChanged = viewModel::onThirdCodeChanged,
        onFourthCodeChanged = viewModel::onFourthCodeChanged
    )

    //on code sending navigate to new password destination
    LaunchedEffect(
        key1 = state.value.verifyCodeEventStatus.success
    ) {

        //check if sending code event status is success
        //navigate to new password screen
        if (state.value.verifyCodeEventStatus.success) {
            navigateToNewPasswordDestination()
        }//end if

    }//end LaunchedEffect

}//end CodeScreen

@Composable
private fun CodeContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickOnVerifyButton: () -> Unit,
    uiState: ForgottenUiState,
    onFirstCodeChanged: (String) -> Unit,
    onSecondCodeChanged: (String) -> Unit,
    onThirdCodeChanged: (String) -> Unit,
    onFourthCodeChanged: (String) -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
        ) {
            val (title, message, rowCodeFields, verifyButton) = createRefs()

            TextBoldView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    R.string.verification_code
                ),
                size = dimen.dimen_2_5,
                modifier = Modifier
                    .constrainAs(title) {
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
                            dimen.dimen_2.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )


            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    R.string.message_forgot_code
                ),
                size = dimen.dimen_1_75,
                fontColor = theme.black,
                modifier = Modifier
                    .constrainAs(message) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_3.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_3.dp
                        )
                        top.linkTo(
                            title.bottom,
                            dimen.dimen_1_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            Row(
                modifier = Modifier
                    .constrainAs(rowCodeFields) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            message.bottom,
                            dimen.dimen_4.dp
                        )
                        width = Dimension.fillToConstraints
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                CodeFieldView(
                    dimen = dimen,
                    theme = theme,
                    value = uiState.firstCodeKey,
                    enabled = !uiState.verifyCodeEventStatus.loading,
                    onChange = onFirstCodeChanged
                )

                CodeFieldView(
                    dimen = dimen,
                    theme = theme,
                    value = uiState.secondCodeKey,
                    enabled = !uiState.verifyCodeEventStatus.loading,
                    onChange = onSecondCodeChanged
                )

                CodeFieldView(
                    dimen = dimen,
                    theme = theme,
                    value = uiState.thirdCodeKey,
                    enabled = !uiState.verifyCodeEventStatus.loading,
                    onChange = onThirdCodeChanged
                )

                CodeFieldView(
                    dimen = dimen,
                    theme = theme,
                    value = uiState.fourthCodeKey,
                    enabled = !uiState.verifyCodeEventStatus.loading,
                    onChange = onFourthCodeChanged
                )

            }//end Row

            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    R.string.verify
                ),
                onClick = if (!uiState.verifyCodeEventStatus.loading) {
                    onClickOnVerifyButton
                } else {
                    {}
                },
                fontSize = dimen.dimen_2_5,
                modifier = Modifier
                    .constrainAs(verifyButton) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            rowCodeFields.bottom,
                            dimen.dimen_3_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

        }//end ConstraintLayout

    }//end BaseScreen

}//end CodeContent