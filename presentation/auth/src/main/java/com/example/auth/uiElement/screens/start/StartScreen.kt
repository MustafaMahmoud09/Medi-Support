package com.example.auth.uiElement.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.auth.uiElement.components.items.LogoSection
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import kotlinx.coroutines.delay

@Composable
internal fun StartScreen(
    navigateToWelcomeDestination: () -> Unit
) {

    StartContent(
        navigateToWelcomeDestination = navigateToWelcomeDestination
    )

}//end StartScreen

@Composable
private fun StartContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme(),
    navigateToWelcomeDestination: () -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = theme.background
                )
                .padding(
                    horizontal = dimen.dimen_2.dp
                ),
            contentAlignment = Alignment.Center
        ) {

            LogoSection(
                theme = theme,
                dimen = dimen
            )

        }//end Box

    }//end BaseScreen

    LaunchedEffect(
        key1 = true
    ) {

        delay(1000)
        navigateToWelcomeDestination()

    }//end LaunchedEffect

}//end StartContent