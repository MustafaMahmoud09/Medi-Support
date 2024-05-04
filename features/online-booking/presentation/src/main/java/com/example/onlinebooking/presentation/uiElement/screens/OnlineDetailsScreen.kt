package com.example.onlinebooking.presentation.uiElement.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onlinebooking.presentation.uiElement.components.items.OnlineBookingSection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun OnlineDetailsScreen(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToOnlineRoomNavGraph: () -> Unit
) {

    //create online details content here
    OnlineDetailsContent(
        dimen = dimen,
        theme = theme,
        onClickOnVideoCallButton = navigateToOnlineRoomNavGraph
    )

}//end OnlineDetailsScreen

@Composable
private fun OnlineDetailsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnVideoCallButton: () -> Unit
) {

    //create container here
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            ),
        contentPadding = PaddingValues(
            all = dimen.dimen_2.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = dimen.dimen_1_5.dp
        )
    ) {

        //create online booking items here
        items(
            count = 10
        ){

            //create online booking here
            OnlineBookingSection(
                dimen = dimen,
                theme = theme,
                onClickOnVideoCallButton = onClickOnVideoCallButton,
                message = "Now you Can Make Video Call With The Doctor",
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items


    }//end LazyColumn

}//end OnlineDetailsContent