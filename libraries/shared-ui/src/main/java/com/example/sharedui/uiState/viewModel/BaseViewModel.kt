package com.example.sharedui.uiState.viewModel

import androidx.lifecycle.ViewModel
import com.patrykandpatrick.vico.core.entry.ChartEntryModel
import com.patrykandpatrick.vico.core.entry.entryModelOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

abstract class BaseViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun getCoroutineScope(): CoroutineScope {

        return coroutineScope

    }//end getCoroutineScope

    fun String.subStringFromEndBefore(char: Char): String{

        var result = ""

        for (count in this.indices){
            if(this[this.length - count - 1] == char){
                break
            }
            result += this[this.length - count - 1]
        }//end for

        var finalResult = ""
        for (count in result.indices){
            finalResult += result[result.length - count - 1]
        }//end for

        return finalResult

    }//end subStringFromEnd

    fun formatMessageTime(time: String, resultFormat:String = "d-M-yyyy HH:mm"): String {

        // تنسيق الوقت المحدد
        val serverTime = Instant.parse(time)

        val localTime = serverTime.atZone(ZoneId.systemDefault()).toLocalDateTime()

        // الوقت الحالي
        val currentTime = LocalDateTime.now()

        // مقارنة الوقتين
        return if (localTime.toLocalDate() == currentTime.toLocalDate()) {

            val finalFormatter = DateTimeFormatter.ofPattern("HH:mm")
            localTime.format(finalFormatter)

        }//end if
        else {

            val finalFormatter = DateTimeFormatter.ofPattern(resultFormat)
            localTime.format(finalFormatter)

        }//end else

    }//end formatMessageTime

    fun formatLocalTime(localTime: LocalTime, pattern: String): String {

        val timeFormatter = DateTimeFormatter.ofPattern(pattern)
        val timeSelectedValue = localTime
            .format(timeFormatter)
            .uppercase(Locale.getDefault())

        return timeSelectedValue

    }//end formatLocalTime

    fun setChartEntries(data: List<Long>): ChartEntryModel {

        return when (data.size) {
            7 -> {
                entryModelOf(data[0], data[1], data[2], data[3], data[4], data[5], data[6])
            }//end if
            6 -> {
                entryModelOf(data[0], data[1], data[2], data[3], data[4], data[5])
            }
            5 -> {
                entryModelOf(data[0], data[1], data[2], data[3], data[4])
            }
            4 -> {
                entryModelOf(data[0], data[1], data[2], data[3])
            }
            3 -> {
                entryModelOf(data[0], data[1], data[2])
            }
            2 -> {
                entryModelOf(data[0], data[1])
            }
            1 -> {
                entryModelOf(data[0])
            }
            else -> {
                return entryModelOf(0f)
            }
        }


    }//end setChartEntries

    override fun onCleared() {
        super.onCleared()
    }//end onCleared

}//end BaseViewModel