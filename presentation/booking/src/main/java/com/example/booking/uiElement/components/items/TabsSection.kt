@file:OptIn(ExperimentalFoundationApi::class)

package com.example.booking.uiElement.components.items

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.booking.uiElement.components.data.TabData
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

//function for create tabs section used android material
@Composable
internal fun TabsSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    driverHeight: Float = dimen.dimen_0_25,
    indicatorHeight: Float = dimen.dimen_0_25 + dimen.dimen_0_125,
    driverShape: Shape = RoundedCornerShape(
        percent = 90
    ),
    indicatorShape: Shape = RoundedCornerShape(
        percent = 90
    ),
    driverColor: Color = theme.grayLight,
    indicatorColor: Color = theme.redDark,
    selectedTitleColor: Color = theme.brownLight,
    unselectedTitleColor: Color = theme.grayDark,
    titleSize: Float = dimen.dimen_2_25,
    currentItem: Int,
    tabs: Array<TabData>,
    modifier: Modifier = Modifier,
) {

    //create tab row here
    TabRow(
        modifier = modifier,
        selectedTabIndex = currentItem,
        indicator = { tabPositions ->

            //create  indicator here
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(
                        tabPositions[currentItem]
                    )
                    .clip(
                        shape = indicatorShape
                    ),
                height = indicatorHeight.dp,
                color = indicatorColor
            )

        },
        divider = {

            //create driver box here
            Box(
                modifier = Modifier
                    .height(
                        height = indicatorHeight.dp
                    )
                    .background(
                        color = theme.transparent
                    ),
                contentAlignment = Alignment.Center
            ) {

                //create driver here
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            shape = driverShape
                        )
                        .height(
                            height = driverHeight.dp
                        )
                        .background(
                            color = driverColor
                        )
                )

            }//end Box

        },
        containerColor = theme.background,
    ) {

        for (index in tabs.indices) {

            Tab(
                selected = index == currentItem,
                onClick = tabs[index].onClick,
                selectedContentColor = selectedTitleColor,
                unselectedContentColor = unselectedTitleColor,
                text = {

                    //create title box here
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {

                        //create title here
                        TextSemiBoldView(
                            theme = theme,
                            dimen = dimen,
                            text = tabs[index].title,
                            size = titleSize,
                            fontColor = if (currentItem == index) {
                                selectedTitleColor
                            } else {
                                unselectedTitleColor
                            }
                        )

                    }//end Box

                }//end icon scope
            )

        }//end forEach

    }//end TabRow Scope

}//end TabsSection


//function for create tabs section build in medi support project
@Composable
internal fun TabsSectionII(
    theme: CustomTheme,
    dimen: CustomDimen,
    unselectedTabHeight: Float = dimen.dimen_0_25,
    selectedTabHeight: Float = dimen.dimen_0_25 + dimen.dimen_0_125,
    endUnselectedShape: Shape = RoundedCornerShape(
        topEndPercent = 90,
        bottomEndPercent = 90
    ),
    firstUnselectedShape: Shape = RoundedCornerShape(
        topStartPercent = 90,
        bottomStartPercent = 90
    ),
    selectedShape: Shape = RoundedCornerShape(
        percent = 90
    ),
    unselectedColor: Color = theme.grayLight,
    selectedColor: Color = theme.redDark,
    titles: Array<String>,
    selectedTitleColor: Color = theme.brownLight,
    unselectedTitleColor: Color = theme.grayDark,
    titleSize: Float = dimen.dimen_2_25,
    selectedItem: Int,
    onClickOnTab: Array<() -> Unit>,
    modifier: Modifier = Modifier,
) {

    //create container here
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center
    ) {

        //create tabs here
        for (count in titles.indices) {

            //create tab here
            TabSection(
                dimen = dimen,
                theme = theme,
                title = titles[count],
                titleSize = titleSize,
                selectedTitleColor = selectedTitleColor,
                unselectedTitleColor = unselectedTitleColor,
                unselectedTabHeight = unselectedTabHeight,
                selectedTabHeight = selectedTabHeight,
                unselectedShape = when (count) {
                    0 -> {
                        firstUnselectedShape
                    }

                    titles.size - 1 -> {
                        endUnselectedShape
                    }

                    else -> {
                        RectangleShape
                    }
                },
                selectedShape = selectedShape,
                unselectedColor = unselectedColor,
                selectedColor = selectedColor,
                numberOfItem = count,
                selectedItem = selectedItem,
                onClickOnTab = onClickOnTab[count],
                modifier = Modifier
                    .weight(
                        weight = 1f
                    )
            )

        }//end for

    }//end ConstraintLayout

}//end TabsSection