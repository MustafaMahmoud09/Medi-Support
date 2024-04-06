package com.example.sharedui.uiState.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

abstract class BaseViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun getCoroutineScope(): CoroutineScope {

        return coroutineScope

    }//end getCoroutineScope

    fun formatLocalTime(localTime: LocalTime, pattern: String): String {

        val timeFormatter = DateTimeFormatter.ofPattern(pattern)
        val timeSelectedValue = localTime
            .format(timeFormatter)
            .uppercase(Locale.getDefault())

        return timeSelectedValue

    }//end formatLocalTime

    override fun onCleared() {
        super.onCleared()
    }//end onCleared

}//end BaseViewModel