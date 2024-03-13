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
internal fun FirstScreen(
    theme: CustomTheme,
    dimen: CustomDimen
) {

    FirstContent(
        theme = theme,
        dimen = dimen
    )
}//end FirstScreen

@Composable
private fun FirstContent(
    theme: CustomTheme,
    dimen: CustomDimen,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        WelcomeSection(
            theme = theme,
            dimen = dimen,
            image = painterResource(
                id = com.example.auth.presentation.R.drawable.welcome_one
            ),
            title = stringResource(
                R.string.welcome_to_medisupport
            ),
            message = stringResource(
                R.string.welcome_first_message
            ),
            modifier = Modifier
                .fillMaxSize()
        )

    }//end ConstraintLayout

}//ent FirstContent