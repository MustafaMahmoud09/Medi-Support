package com.example.auth.presentation.uiElement.screens.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.auth.presentation.uiElement.components.items.LogoSection
import com.example.auth.presentation.uiState.viewModel.SplashViewModel
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme


@Composable
internal fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel(),
    navigateToWelcomeDestination: () -> Unit,
    navigateToBottomDestination: () -> Unit
) {
    //collect screen state here
    val state = viewModel.state.collectAsState()

    SplashContent(
        navigateToWelcomeDestination = navigateToWelcomeDestination
    )

    LaunchedEffect(
        key1 = state.value.userIsAuth
    ) {

        if (state.value.userIsAuth != null) {

            //if user is auth navigate to bottom destination
            if (state.value.userIsAuth!!) {
                navigateToBottomDestination()
            }//end if
            //else navigate to welcome destination
            else {
                navigateToWelcomeDestination()
            }//end else

        }//end if

    }//end LaunchedEffect

}//end StartScreen

@Composable
private fun SplashContent(
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

}//end StartContent