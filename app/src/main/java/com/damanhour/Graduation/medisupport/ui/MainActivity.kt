package com.damanhour.Graduation.medisupport.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //CUT SYSTEM UI (STATUS AND NAVIGATION)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            //SINGLE ACTIVITY --- MULTI SCREENS
            App()
        }
    }//end onCreate

}//end class MainActivity

