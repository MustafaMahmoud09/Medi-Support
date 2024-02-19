package com.example.booking.uiElement.screens.doctors.child.top.child

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.booking.uiElement.components.items.DoctorSearchSection
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun TopOnlineDoctorsScreen(
    theme: CustomTheme,
    dimen: CustomDimen,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit
) {

    //create top online doctors content here
    TopOnlineDoctorsContent(
        theme = theme,
        dimen = dimen,
        navigateToBookingNavGraph = navigateToBookingNavGraph
    )
}//end TopOnlineDoctorsScreen

@Composable
private fun TopOnlineDoctorsContent(
    dimen: CustomDimen,
    theme: CustomTheme,
    navigateToBookingNavGraph: (Boolean, Int) -> Unit,
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
            DoctorSearchSection(
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
                doctorIsOnline = true,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items

    }//end LazyColumn

}//end TopOnlineDoctorsScreen