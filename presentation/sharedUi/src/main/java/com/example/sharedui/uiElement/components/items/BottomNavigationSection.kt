package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.sharedui.uiElement.components.navigation.BottomDestination
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun RowScope.BottomNavigationSection(
    navController: NavHostController,
    items: List<BottomDestination>,
    routeNull: String = "",
    ifRouteNull: () -> Unit = {},
    dimen: CustomDimen,
    theme: CustomTheme,
    selectedContentColor: Color = theme.redDark,
    unselectedContentColor: Color = theme.hintIconBottom
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    items.forEach { screen ->

        BottomNavigationItem(
            icon = {

                Icon(
                    painter = painterResource(
                        id = screen.icon
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .size(
                            dimen.dimen_2_5.dp
                        )
                )

            },//end icon
            label = {

                Box(
                    modifier = Modifier
                        .padding(
                            top = dimen.dimen_0_5.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = stringResource(
                            id = screen.title
                        ),
                        fontSize = dimen.dimen_1_5.sp,
                        fontFamily = robotoMedium
                    )

                }//end Box

            },//end label
            selectedContentColor = selectedContentColor,
            unselectedContentColor = unselectedContentColor,
            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
            onClick = {

                clickOnItemInBottomNavigation(
                    navController = navController,
                    screen = screen,
                    routeNull = routeNull,
                    items = items,
                    ifRouteNull = ifRouteNull
                )

            }//end onClick
        )

    }//end forEach

}//end BottomNavigation


private fun clickOnItemInBottomNavigation(
    navController: NavHostController,
    screen: BottomDestination,
    routeNull: String,
    items: List<BottomDestination>,
    ifRouteNull: () -> Unit
) {

    if (screen.route != routeNull) {
        val lastDestinationRoute = navController.backQueue.last().destination.route
        if (
            lastDestinationRoute != screen.route &&
            screen.childList?.contains(lastDestinationRoute) != true
        ) {

            navController.navigate(
                route = screen.route
            ) {

                popUpTo(
                    id = navController.graph.findStartDestination().id
                ) {
                    //save the state to popped screen here
                    saveState = true
                }//end popUpTo

                //support not exit multi copied to single destination in back stack here
                launchSingleTop = true
                //restore last state to this destination here
                restoreState = true
            }//end navigate

        }//end if

    } else {

        ifRouteNull()

        for (count in 1 until items.size) {

            navController.popBackStack(
                route = items[count].route,
                inclusive = true
            )

        }//end for

    }//end else

}//end clickOnItemInBottomNavigation