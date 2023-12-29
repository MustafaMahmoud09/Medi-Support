package com.example.auth.uiElement.screens.forgotten.newPassword

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
import com.example.sharedui.uiElement.components.composable.BasicButtonView
import com.example.sharedui.uiElement.components.composable.TextBoldBlackView
import com.example.sharedui.uiElement.components.items.FailedHintSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun NewPasswordScreen(
    navHostController: NavHostController
) {

    NewPasswordContent()
}//end NewPasswordScreen

@Composable
private fun NewPasswordContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen()
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
            val (title, passwordFailed, confirmPassword, resetPasswordButton) = createRefs()

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
                onClick = {},
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