package com.example.sharedui.uiElement.components.items

//import android.os.Build
//import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
//import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
//import java.time.LocalDate
//import java.time.format.DateTimeFormatter

@Composable
fun DaySection(
    dimen: CustomDimen,
    dayName: String,
    dayNumber: String,
    theme: CustomTheme,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = dayName,
            size = dimen.dimen_1_75,
            fontColor = theme.black
        )

        Spacer(
            modifier = Modifier
                .height(
                    dimen.dimen_1.dp
                )
        )

        Box(
            modifier = Modifier
                .size(
                    dimen.dimen_5_5.dp
                )
                .clip(
                    shape = CircleShape
                )
                .border(
                    width = dimen.dimen_0_125.dp,
                    color = theme.redDarkTR50,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = dayNumber,
                size = dimen.dimen_1_75,
                fontColor = theme.black
            )

        }//end Box

    }//end Column

}//end DaySection

//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun DayListScreen(endDate: LocalDate) {
//    val startDate = LocalDate.now()
//    val dateList = generateDateList(startDate, endDate)
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        LazyColumn {
//            items(dateList) { (dayName, formattedDate) ->
//                Text("يوم $dayName - $formattedDate")
//            }
//        }
//    }
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//private fun generateDateList(startDate: LocalDate, endDate: LocalDate): List<Pair<String, String>> {
//    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    val dateList = mutableListOf<Pair<String, String>>()
//
//    var currentDate = startDate
//    while (!currentDate.isBefore(endDate)) {
//        val dayName = currentDate.dayOfWeek.toString()
//        val formattedDate = currentDate.format(formatter)
//        dateList.add(dayName to formattedDate)
//        currentDate = currentDate.minusDays(1)
//    }
//
//    return dateList
//}
//
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun PreviewDayListScreen() {
//    DayListScreen(LocalDate.now().minusDays(10))
//}
