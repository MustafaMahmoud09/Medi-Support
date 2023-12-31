package com.example.auth.uiElement.screens.forgotten.email

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.example.auth.R
import com.example.sharedui.uiElement.components.composable.BackButtonView
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldBlackView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.items.FailedHintSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun EmailScreen(
    popForgotPasswordNavGraph: () -> Unit,
    navigateToCodeDestination: () -> Unit
) {

    EmailContent(
        onClickBack = { popForgotPasswordNavGraph() },
        onClickSendEmail = { navigateToCodeDestination() }
    )
}//end EmailScreen

@Composable
private fun EmailContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickBack: () -> Unit,
    onClickSendEmail: () -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        ConstraintLayout(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize()
                .background(
                    color = theme.background
                )
        ) {
            val (backButton, title, message, emailFailed, sendButton) = createRefs()
            val guideLineFromEnd44DP = createGuidelineFromEnd(dimen.dimen_5_5.dp)

            BackButtonView(
                dimen = dimen,
                theme = theme,
                onClick = onClickBack,
                modifier = Modifier
                    .constrainAs(backButton) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            parent.top,
                            dimen.dimen_2.dp
                        )
                    }
            )

            TextBoldBlackView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    R.string.forgotten_your_password
                ),
                size = dimen.dimen_2_5,
                modifier = Modifier
                    .constrainAs(title) {
                        start.linkTo(
                            backButton.end,
                            dimen.dimen_1.dp
                        )
                        end.linkTo(
                            guideLineFromEnd44DP,
                            dimen.dimen_1.dp
                        )
                        top.linkTo(backButton.top)
                        bottom.linkTo(backButton.bottom)
                        width = Dimension.fillToConstraints
                    }
            )

            TextNormalView(
                theme = theme,
                dimen = dimen,
                text = stringResource(
                    R.string.message_email_forgotten
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

            FailedHintSection(
                theme = theme,
                dimen = dimen,
                title = stringResource(R.string.email_address),
                hint = stringResource(
                    R.string.hint_email_forgotten
                ),
                value = "",
                onChange = {},
                modifier = Modifier
                    .constrainAs(emailFailed) {
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
                    }
            )

            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    R.string.send_email
                ),
                onClick = onClickSendEmail,
                fontSize = dimen.dimen_2_5,
                modifier = Modifier
                    .constrainAs(sendButton) {
                        start.linkTo(
                            parent.start,
                            dimen.dimen_2.dp
                        )
                        end.linkTo(
                            parent.end,
                            dimen.dimen_2.dp
                        )
                        top.linkTo(
                            emailFailed.bottom,
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

}//end EmailContent