package com.example.auth.presentation.uiElement.screens.welcome.child

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.R
import com.example.auth.presentation.uiElement.components.items.WelcomeSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ThirdScreen(
    theme: CustomTheme,
    dimen: CustomDimen
) {

    ThirdContent(
        theme = theme,
        dimen = dimen
    )
}//end ThirdScreen

@Composable
private fun ThirdContent(
    theme: CustomTheme,
    dimen: CustomDimen,
) {

    ConstraintLayout (
        modifier = Modifier
            .fillMaxSize()
    ){

        WelcomeSection(
            theme = theme,
            dimen = dimen,
            image = painterResource(
                id = com.example.auth.presentation.R.drawable.welcome_three
            ),
            title = stringResource(
                R.string.cmprehensive_health_metrics
            ),
            message = stringResource(
                R.string.welcome_message_three
            ),
            modifier = Modifier
                .fillMaxSize()
        )

    }//end ConstraintLayout

}//ent ThirdContent