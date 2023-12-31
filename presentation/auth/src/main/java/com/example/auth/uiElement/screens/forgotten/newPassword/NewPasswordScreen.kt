package com.example.auth.uiElement.screens.forgotten.newPassword

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavHostController
import com.example.auth.R
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldBlackView
import com.example.sharedui.uiElement.components.items.DialogBasicSection
import com.example.sharedui.uiElement.components.items.FailedHintSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun NewPasswordScreen(
    backToLoginNavGraph: () -> Unit
) {

    NewPasswordContent(
        onClickBackLogin = { backToLoginNavGraph() }
    )
}//end NewPasswordScreen

@Composable
private fun NewPasswordContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBackLogin: () -> Unit
) {

    var isShowDialog by rememberSaveable { mutableStateOf(false) }

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
            val (title, passwordFailed, confirmPassword, resetPasswordButton, dialog) = createRefs()
//            val guideLineFromTop25P = createGuidelineFromTop(25f)
//            val guideLineFromBottom25P = createGuidelineFromBottom(25f)

            //dialog back to login destination
            AnimatedVisibility(
                visible = isShowDialog,
                enter = fadeIn(
                    animationSpec = tween(
                        durationMillis = 150
                    )
                ),
                exit = fadeOut(
                    animationSpec = tween(
                        durationMillis = 150
                    )
                )
            ) {

                DialogBasicSection(
                    onDismissRequest = {},
                    dimen = dimen,
                    theme = theme,
                    logo = painterResource(
                        id = com.example.sharedui.R.drawable.success
                    ),
                    tint = theme.greenLight,
                    buttonTitle = stringResource(
                        id = com.example.sharedui.R.string.go_to_back
                    ),
                    onClick = onClickBackLogin,
                    proText = stringResource(
                        id = com.example.sharedui.R.string.done
                    ),
                    modifier = Modifier
                        .constrainAs(dialog) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            width = Dimension.fillToConstraints
                        }
                        .aspectRatio(.9f)
                        .padding(
                            horizontal = dimen.dimen_2.dp
                        )
                )

            }//end if

            TextBoldBlackView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    R.string.new_password
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

            FailedHintSection(
                theme = theme,
                dimen = dimen,
                title = stringResource(
                    com.example.sharedui.R.string.password
                ),
                hint = stringResource(
                    com.example.sharedui.R.string.your_password
                ),
                value = "",
                password = true,
                onChange = {},
                modifier = Modifier
                    .constrainAs(passwordFailed) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            title.bottom,
                            dimen.dimen_4.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            FailedHintSection(
                theme = theme,
                dimen = dimen,
                title = stringResource(
                    R.string.confirm_password
                ),
                hint = stringResource(
                    com.example.sharedui.R.string.your_password
                ),
                value = "",
                password = true,
                onChange = {},
                modifier = Modifier
                    .constrainAs(confirmPassword) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            passwordFailed.bottom,
                            dimen.dimen_2.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(R.string.resat_password),
                onClick = { isShowDialog = true },
                fontSize = dimen.dimen_2_5,
                modifier = Modifier
                    .constrainAs(resetPasswordButton) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            confirmPassword.bottom,
                            dimen.dimen_2.dp
                        )
                        width = Dimension.fillToConstraints
                    }
                    .height(
                        dimen.dimen_7.dp
                    )
            )

        }//end ConstraintLayout

    }//end BaseScreen

}//end NewPasswordContent


