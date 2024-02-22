package com.example.room.uiElement.screens.room

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
internal fun OnlineRoomScreen() {

    OnlineRoomContent()
}//end ChatScreen

@Composable
private fun OnlineRoomContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme()
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

    }

}//end ChatContent