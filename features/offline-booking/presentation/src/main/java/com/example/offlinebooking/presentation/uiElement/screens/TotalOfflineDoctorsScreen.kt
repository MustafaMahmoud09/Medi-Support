package com.example.offlinebooking.presentation.uiElement.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.items.DoctorPrimaryInformationSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun TotalOfflineDoctorsScreen(
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToBookingNavGraph: (Int) -> Unit
) {

    //create total offline doctors content here
    TotalOfflineDoctorsContent(
        theme = theme,
        dimen = dimen,
        navigateToBookingNavGraph = navigateToBookingNavGraph
    )
}//end TotalOfflineDoctorsScreen

@Composable
private fun TotalOfflineDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Int) -> Unit,
) {

    //create container here
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(
            bottom = dimen.dimen_2.dp,
            top = dimen.dimen_1_5.dp,
            start = dimen.dimen_2.dp,
            end = dimen.dimen_2.dp
        ),
        verticalArrangement = Arrangement.spacedBy(
            space = dimen.dimen_1_5.dp
        ),
    ) {

        //create doctor items
        items(
            count = 10
        ) {

            //create single doctor here
            DoctorPrimaryInformationSection(
                dimen = dimen,
                theme = theme,
                name = "DR: Alaa Ahmed",
                location = "Cairo",
                time = "12.00 AM -3:00 PM",
                image = painterResource(
                    id = R.drawable.doctor_test
                ),
                textButton = stringResource(
                    id = R.string.book_now
                ),
                onClickOnButton = navigateToBookingNavGraph,
                doctorIsOnline = false,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items

    }//end LazyColumn

}//end TotalOfflineDoctorsContent