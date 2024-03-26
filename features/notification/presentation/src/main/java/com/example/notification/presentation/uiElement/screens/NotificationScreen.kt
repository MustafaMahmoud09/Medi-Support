package com.example.notification.presentation.uiElement.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.modifier.appDefaultContainer
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@Composable
fun NotificationScreen() {

    NotificationContent()

}//end NotificationScreen

@Composable
private fun NotificationContent(
    dimen: CustomDimen = MediSupportAppDimen(),
    theme: CustomTheme = MediSupportAppTheme()
) {

    //create screen container here
    ConstraintLayout(
        modifier = Modifier
            .appDefaultContainer(
                color = theme.background
            )
    ) {


    }//end ConstraintLayout

}//end NotificationContent