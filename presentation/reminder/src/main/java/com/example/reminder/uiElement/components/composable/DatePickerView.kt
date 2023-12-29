@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.reminder.uiElement.components.composable


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DatePickerView(
    state: DatePickerState = rememberDatePickerState(),
    title: String = "SELECT DATE",
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    colors: DatePickerColors = DatePickerDefaults.colors(
        titleContentColor = Color.Red,
        headlineContentColor = Color.Red,
        weekdayContentColor = Color.Red,
//        navigationContentColor = Color.Red,
        yearContentColor = Color.Red,
//        disabledYearContentColor = Color.Red,
        currentYearContentColor = Color.Blue,
        selectedYearContentColor = Color.Green,
        selectedYearContainerColor = Color.Yellow,
        dayContentColor = Color.Red,
        selectedDayContentColor = Color.Red,
        selectedDayContainerColor = Color.Yellow,
        todayContentColor = Color.Black,
        todayDateBorderColor = Color.Red,
//        dividerColor = Color.Red,
    ),
    modifier: Modifier = Modifier
) {

    DatePicker(
        modifier = modifier,
        state = state,
        showModeToggle = false,
        title = {

            Box(
                modifier = Modifier
                    .padding(
                        horizontal = dimen.dimen_3.dp,
                        vertical = dimen.dimen_2.dp
                    )
            ) {
                Text(
                    text = title,
                    fontSize = dimen.dimen_1_75.sp
                )
            }
        },//end title
        colors = colors,
    )
}//end DatePickerView


//
//
//@SuppressLint("SimpleDateFormat")
//private fun convertMillisToDate(
//    millis: Long
//): String {
//
//    //convert time to date
//    val formatter = SimpleDateFormat("dd/MM/yyyy")
//    return formatter.format(
//        Date(millis)
//    )
//}//end convertMillisToDate