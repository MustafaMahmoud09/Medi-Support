package com.example.bloodsugar.presentation.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ColumnView(
    dimen: CustomDimen,
    theme: CustomTheme,
    bigColumnColor: Color = theme.redDark,
    smallColumnColor: Color = theme.redDarkTR70,
    numberOfItem: Float,
    bigColumnHeight: Float = dimen.dimen_19_5,
    smallColumnHeight: Float = dimen.dimen_12_5,
    width: Float = dimen.dimen_1_25,
    shape: Shape = RectangleShape,
    textNumberColor: Color = theme.black,
    textNumberSize: Float = dimen.dimen_1_75,
    modifier: Modifier = Modifier
) {

    //if number of item is divisible by 5 create execute this body
    if (numberOfItem % 5 == 0f) {

        //create column here
        Column(
            modifier = modifier
                .height(
                    height = (bigColumnHeight + dimen.dimen_2_75).dp
                )
                .padding(
                    horizontal = when (numberOfItem.toString().length) {
                        1 -> {
                            dimen.dimen_0_25.dp
                        }

                        else -> {
                            dimen.dimen_0.dp
                        }
                    }
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Spacer(
                modifier = Modifier
                    .size(
                        width = width.dp,
                        height = bigColumnHeight.dp
                    )
                    .clip(
                        shape = shape
                    )
                    .background(
                        color = bigColumnColor
                    )
            )

            //create text number here
            TextSemiBoldView(
                theme = theme,
                dimen = dimen,
                text = "${numberOfItem.toInt()}",
                size = textNumberSize,
                fontColor = textNumberColor
            )

        }//end Column

    }//end if

    //else execute this body
    else {

        //create container here
        Column {

            //create sub container here
            Box(
                modifier = modifier
                    .height(
                        height = bigColumnHeight.dp
                    )
                    .padding(
                        end = if (
                        //check this item before item divisible by 5
                            numberOfItem.toInt() % 5 == 4 &&
                            numberOfItem - numberOfItem.toInt() != 0f &&
                            (numberOfItem + 1).toInt().toString().length > 2
                        ) {
                            dimen.dimen_0.dp
                        } else {
                            (dimen.dimen_0_25 + dimen.dimen_0_125).dp
                        },
                        start = if (
                            //check this item after item divisible by 5
                            numberOfItem.toInt() % 5 == 0 &&
                            numberOfItem - numberOfItem.toInt() != 0f &&
                            numberOfItem.toInt().toString().length > 2
                        ) {
                            dimen.dimen_0.dp
                        } else {
                            (dimen.dimen_0_25 + dimen.dimen_0_125).dp
                        },
                    ),
                contentAlignment = Alignment.Center
            ) {

                //create column here
                Spacer(
                    modifier = Modifier
                        .size(
                            width = width.dp,
                            height = smallColumnHeight.dp
                        )
                        .clip(
                            shape = shape
                        )
                        .background(
                            color = smallColumnColor
                        )
                )

            }//end Box

            //create padding here
            Spacer(
                modifier = Modifier
                    .size(
                        width = width.dp,
                        height = dimen.dimen_2_75.dp
                    )
            )

        }//end Column

    }//end else

}//end ColumnView
