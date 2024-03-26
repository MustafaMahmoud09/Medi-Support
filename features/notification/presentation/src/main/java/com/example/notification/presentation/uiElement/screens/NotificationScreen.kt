package com.example.notification.presentation.uiElement.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.notification.presentation.uiElement.components.items.NotificationHeaderSection
import com.example.notification.presentation.uiElement.components.items.NotificationSection
import com.example.sharedui.R
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

    //create container here
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
            .padding(
                top = dimen.dimen_1.dp
            ),
        contentPadding = PaddingValues(
            start = dimen.dimen_2.dp,
            end = dimen.dimen_2.dp,
            bottom = dimen.dimen_2.dp,
            top = dimen.dimen_1.dp
        ),
    ) {

        //create notification header item here
        item(
            key = "Notification header id"
        ) {

            //create notification header section here
            NotificationHeaderSection(
                dimen = dimen,
                theme = theme,
                title = stringResource(
                    id = R.string.notifications
                ),
                readTitle = stringResource(
                    id = R.string.mark_all_read
                ),
                readCheckBoxState = true,
                readOnCheckBoxChanged = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        bottom = dimen.dimen_2.dp
                    )
            )

        }//end item


        //create notification items here
        items(
            count = 10
        ) {

            //create single notification here
            NotificationSection(
                dimen = dimen,
                theme = theme,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items

    }//end LazyColumn

}//end NotificationContent