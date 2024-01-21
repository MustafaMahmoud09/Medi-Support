package com.example.auth.uiElement.screens.forgotten.code

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.auth.R
import com.example.auth.uiElement.components.composable.NumberFailedView
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
    navigateToNewPasswordDestination: () -> Unit
) {

    CodeContent(
        onClickNewPassword = { navigateToNewPasswordDestination() }
    )
}//end CodeScreen

@Composable
private fun CodeContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    onClickNewPassword: () -> Unit
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
            val (title, message, rowCodeFaileds, verifyButton) = createRefs()

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
                    .constrainAs(rowCodeFaileds) {
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

                NumberFailedView(
                    dimen = dimen,
                    theme = theme,
                    value = "",
                    onChange = {}
                )

                NumberFailedView(
                    dimen = dimen,
                    theme = theme,
                    value = "",
                    onChange = {}
                )

                NumberFailedView(
                    dimen = dimen,
                    theme = theme,
                    value = "",
                    onChange = {}
                )

                NumberFailedView(
                    dimen = dimen,
                    theme = theme,
                    value = "",
                    onChange = {}
                )

            }//end Row

            BasicButtonView(
                dimen = dimen,
                theme = theme,
                text = stringResource(
                    R.string.verify
                ),
                onClick = onClickNewPassword,
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
                            rowCodeFaileds.bottom,
                            dimen.dimen_3_5.dp
                        )
                        width = Dimension.fillToConstraints
                    }
            )

        }//end ConstraintLayout

    }//end BaseScreen

}//end CodeContent