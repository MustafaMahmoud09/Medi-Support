package com.example.booking.uiElement.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun LazyDoctorsSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    doctorIsOnline: Boolean = false,
    doctorsHeight: Float,
    contentPadding: PaddingValues = PaddingValues(
        bottom = dimen.dimen_2.dp,
        top = dimen.dimen_1_5.dp
    ),
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(
        space = dimen.dimen_1_5.dp
    ),
    onClickOnBookingButton: (Boolean, Int) -> Unit,
    modifier: Modifier = Modifier,
){

    //create container here
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .height(
                height = doctorsHeight.dp
            ),
        contentPadding = contentPadding,
        verticalArrangement = verticalArrangement
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
                onClickOnButton = onClickOnBookingButton,
                doctorIsOnline = doctorIsOnline,
                modifier = Modifier
                    .fillMaxWidth()
            )

        }//end items

    }//end LazyColumn

}//end LazyDoctorsSection