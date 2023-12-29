package com.damanhour.Graduation.medisupport.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat

class MainActivity : ComponentActivity() {

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

