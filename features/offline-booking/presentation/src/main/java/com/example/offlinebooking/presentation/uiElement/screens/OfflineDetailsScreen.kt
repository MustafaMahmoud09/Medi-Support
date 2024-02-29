package com.example.offlinebooking.presentation.uiElement.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun OfflineDetailsScreen(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToChatNavGraph: () -> Unit
) {

    //create offline details content here
    OfflineDetailsContent(
        dimen = dimen,
        theme = theme,
        onClickOnChatButton = navigateToChatNavGraph
    )

}//end OfflineDetailsScreen

@Composable
private fun OfflineDetailsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    onClickOnChatButton: () -> Unit
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

        //create offline booking items here
        items(
            count = 10
        ){

            //create offline booking here
            com.example.offlinebooking.presentation.uiElement.components.items.OfflineBookingSection(
                dimen = dimen,
                theme = theme,
                onClickOnChatButton = onClickOnChatButton,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items

    }//end LazyColumn

}//end OfflineDetailsContent