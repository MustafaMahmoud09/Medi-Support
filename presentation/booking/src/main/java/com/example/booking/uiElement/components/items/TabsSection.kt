package com.example.booking.uiElement.components.items


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun TabsSection(
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