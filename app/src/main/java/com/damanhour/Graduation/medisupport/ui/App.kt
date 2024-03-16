@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.damanhour.Graduation.medisupport.ui.uiElement.navigation.RootNavGraph
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
fun App() {

    val navHostController = rememberAnimatedNavController()
    RootNavGraph(
        navHostController = navHostController
    )
}//end App