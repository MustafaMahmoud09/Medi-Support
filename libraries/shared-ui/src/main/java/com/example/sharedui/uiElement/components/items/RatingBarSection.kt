package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.sharedui.uiElement.components.composable.BasicIconView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun RatingBarSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    rating: Float,
    modifier: Modifier = Modifier,
    size: Float = dimen.dimen_1_75,
    color: Color = theme.gold,
) {
    //create container here
    Row(
        modifier = modifier
    ) {
        //create star is complete first here
        for (i in 0 until rating.toInt()) {
            //create star complete here
            BasicIconView(
                color = color,
                size = size,
                painter = painterResource(
                    id = com.example.sharedui.R.drawable.star_complete
                )
            )

        }//end for

        //create star is half or empty here
        for (i in 0 until 5 - rating.toInt()) {

            //create star is half here
            if (
                i == 0 &&
                (5 - rating) - (5 - rating).toInt() <= 0.5
                && (5 - rating) - (5 - rating).toInt() > 0F
            ) {

                BasicIconView(
                    color = color,
                    size = size,
                    painter = painterResource(
                        id = com.example.sharedui.R.drawable.star_half
                    )
                )

            }//end if
            //create star is empty here
            else {

                BasicIconView(
                    size = size,
                    color = color,
                    painter = painterResource(
                        id = com.example.sharedui.R.drawable.star_empty
                    )
                )

            }//end else

        }//end for

    }//end Row

}//end RatingBarSection
