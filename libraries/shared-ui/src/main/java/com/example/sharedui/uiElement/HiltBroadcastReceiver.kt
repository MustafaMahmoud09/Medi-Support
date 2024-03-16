package com.example.sharedui.uiElement

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.CallSuper


abstract class HiltBroadcastReceiver : BroadcastReceiver() {

    @CallSuper
    abstract override fun onReceive(context: Context?, intent: Intent?)

}