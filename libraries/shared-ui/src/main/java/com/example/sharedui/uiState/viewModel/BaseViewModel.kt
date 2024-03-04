package com.example.sharedui.uiState.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class BaseViewModel : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    fun getCoroutineScope(): CoroutineScope {

        return coroutineScope

    }//end getCoroutineScope

    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
    }//end onCleared

}//end BaseViewModel