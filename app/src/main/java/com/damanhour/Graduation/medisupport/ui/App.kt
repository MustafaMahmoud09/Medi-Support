@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.RootNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App() {

    val navHostController = rememberAnimatedNavController()
    RootNavGraph(
        navHostController = navHostController
    )
}//end App