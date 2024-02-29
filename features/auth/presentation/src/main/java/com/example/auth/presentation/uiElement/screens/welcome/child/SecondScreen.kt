package com.example.auth.presentation.uiElement.screens.welcome.child

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.auth.presentation.R
import com.example.auth.presentation.uiElement.components.items.WelcomeSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme


@Composable
internal fun SecondScreen(
    theme: CustomTheme,
    dimen: CustomDimen
) {

    SecondContent(
        theme = theme,
        dimen = dimen
    )
}//end SecondScreen

@Composable
private fun SecondContent(
    theme: CustomTheme,
    dimen: CustomDimen,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {

        com.example.auth.presentation.uiElement.components.items.WelcomeSection(
            theme = theme,
            dimen = dimen,
            image = painterResource(
                id = R.drawable.welcome_two
            ),
            title = stringResource(
                R.string.connect_with_professionals
            ),
            message = stringResource(R.string.welcome_message_two),
            modifier = Modifier
                .fillMaxSize()
        )

    }//end ConstraintLayout

}//ent SecondContent