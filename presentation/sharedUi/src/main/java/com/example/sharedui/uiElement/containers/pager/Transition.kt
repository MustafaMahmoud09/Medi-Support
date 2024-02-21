@file:OptIn(ExperimentalFoundationApi::class)

package com.example.sharedui.uiElement.containers.pager

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun PagerState.animateScrollToPage(
    coroutineScope: CoroutineScope,
    page: Int,
    animationSpec: AnimationSpec<Float> = spring(
        stiffness = Spring.StiffnessLow
    )
) {

    //create coroutine scope to call suspend function ,
    //coroutine scope make thread execute on it multi function in same time
    //convert processor from sync to async
    coroutineScope.launch {

        //if not exist in page scroll to it
        if (this@animateScrollToPage.currentPage != page) {

            //execute scroll here
            this@animateScrollToPage.animateScrollToPage(
                page = page,
                animationSpec = animationSpec
            )

        }//end if

    }//end launch

}//end animateScrollToPage

fun PagerState.scrollToPage(
    coroutineScope: CoroutineScope,
    page: Int,
) {

    //create coroutine scope here
    coroutineScope.launch {

        //if not exist in page scroll to it
        if (this@scrollToPage.currentPage != page) {

            //execute scroll here
            this@scrollToPage.scrollToPage(
                page = page
            )

        }//end if

    }//end launch

}//end scrollToPage