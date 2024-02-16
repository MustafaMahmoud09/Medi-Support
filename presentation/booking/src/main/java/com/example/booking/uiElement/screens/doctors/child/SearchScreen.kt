package com.example.booking.uiElement.screens.doctors.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

//function for collect state and execute action from view model
@Composable
internal fun SearchScreen(
    dimen: CustomDimen,
    theme: CustomTheme
) {

    //call search content function
    SearchContent(
        dimen = dimen,
        theme = theme
    )
}//end SearchContent

//function for observe state and draw components
@Composable
private fun SearchContent(
    dimen: CustomDimen,
    theme: CustomTheme
) {
    //create lazy column here
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
        verticalArrangement = Arrangement.spacedBy(
            space = dimen.dimen_1_5.dp
        )
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
                    R.string.book_now
                ),
                modifier = Modifier
                    .fillMaxWidth(),
            )

        }//end items

    }//end LazyColumn

}//end SearchContent