package com.damanhour.Graduation.medisupport.ui


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.example.sharedui.R
import com.facebook.FacebookSdk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //CUT SYSTEM UI (STATUS AND NAVIGATION)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        //initialize facebook sdk
        FacebookSdk.setClientToken(
            getString(
                R.string.facebook_client_token
            )
        )
        FacebookSdk.sdkInitialize(this)

        setContent {
            //SINGLE ACTIVITY --- MULTI SCREENS
            App()
        }

    }//end onCreate

}//end class MainActivity

