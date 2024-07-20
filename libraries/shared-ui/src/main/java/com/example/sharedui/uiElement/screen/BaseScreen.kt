package com.example.sharedui.uiElement.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun BaseScreen(
    navigationColor: Color,
    statusColor: Color,
    useDarkIcons: Boolean = true,
    content: @Composable () -> Unit
) {
    content()

    val systemUiController = rememberSystemUiController()
    DisposableEffect(systemUiController, useDarkIcons) {

        //NAVIGATION
        systemUiController.setNavigationBarColor(
            color = navigationColor,
            darkIcons = useDarkIcons
        )

        //STATUS
        systemUiController.setStatusBarColor(
            color = statusColor,
            darkIcons = useDarkIcons,
            transformColorForLightContent = {
                 statusColor
            }
        )

        onDispose {}
    }
}//end BaseScreen