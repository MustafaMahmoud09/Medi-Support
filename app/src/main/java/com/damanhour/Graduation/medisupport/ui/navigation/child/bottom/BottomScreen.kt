@file:OptIn(ExperimentalAnimationApi::class)

package com.damanhour.Graduation.medisupport.ui.navigation.child.bottom

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.BottomNavGraph
import com.damanhour.Graduation.medisupport.ui.navigation.child.bottom.child.more.MORE_VAV_GRAPH_DATA
import com.example.activity.uiElement.screens.activity.ACTIVITY_DESTINATION_DATA
import com.example.article.uiElement.screens.articles.ARTICLES_DESTINATION_DATA
import com.example.booking.uiElement.screens.search.DOCTOR_SEARCH_DESTINATION_DATA
import com.example.profile.uiElement.screens.profile.PROFILE_DESTINATION_DATA
import com.example.profile.uiElement.screens.profile.popProfileDestination
import com.example.sharedui.uiElement.components.items.BottomNavigationSection
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.screen.BaseScreen
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.dimens.MediSupportAppDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.uiElement.style.theme.MediSupportAppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@Composable
internal fun BottomScreen(
    navHostController: NavHostController = rememberAnimatedNavController(),
    navigateToActivityDestination: () -> Unit
) {

    val items = listOf(
        DOCTOR_SEARCH_DESTINATION_DATA,
        ACTIVITY_DESTINATION_DATA,
        ARTICLES_DESTINATION_DATA,
        PROFILE_DESTINATION_DATA,
        MORE_VAV_GRAPH_DATA
    )

    BottomContent(
        navHostController = navHostController,
        items = items,
        navigateToActivityDestination = navigateToActivityDestination
    )
}//end BottomScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun BottomContent(
    theme: CustomTheme = MediSupportAppTheme(),
    dimen: CustomDimen = MediSupportAppDimen(),
    navHostController: NavHostController,
    items: List<BottomDestination>,
    navigateToActivityDestination: () -> Unit
) {

    BaseScreen(
        navigationColor = theme.background,
        statusColor = theme.background
    ) {

        Scaffold(
            modifier = Modifier
                .safeDrawingPadding()
                .background(
                    color = theme.background
                )
                .fillMaxSize(),
            bottomBar = {

                BottomAppBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            dimen.dimen_8_5.dp
                        )
                        .background(
                            color = theme.background
                        ),
                    contentPadding = PaddingValues(
                        dimen.dimen_0.dp
                    ),
                    backgroundColor = theme.background,
                ) {

                    BottomNavigationSection(
                        navController = navHostController,
                        routeNull = ACTIVITY_DESTINATION_DATA.route,
                        ifRouteNull = navigateToActivityDestination,
                        items = items,
                        dimen = dimen,
                        theme = theme
                    )

                }//end BottomAppBar

            }//end bottomBar

        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = theme.background
                    )
                    .padding(
                        bottom = dimen.dimen_8_5.dp
                    )
            ) {

                BottomNavGraph(
                    navHostController = navHostController,
                    popProfileDestination = navHostController::popProfileDestination
                )

            }//end Box

        }//end Scaffold

    }//end BaseScreen

}//end BottomContent