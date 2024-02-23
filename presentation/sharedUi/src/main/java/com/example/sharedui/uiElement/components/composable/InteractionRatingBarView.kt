package com.example.sharedui.uiElement.components.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.BasicIconView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun InteractionRatingBarView(
    dimen: CustomDimen,
    theme: CustomTheme,
    value: Int,
    onChanged: (Int) -> Unit,
    size: Float,
    color: Color,
    modifier: Modifier = Modifier
) {

    //create container here
    Row(
        modifier = modifier
    ) {

        for (count in 1..5) {

            //create star here
            BasicIconView(
                size = size,
                color = color,
                painter = if (value >= count) {
                    painterResource(
                        id = R.drawable.star_complete
                    )
                } else {
                    painterResource(
                        id = R.drawable.star_empty
                    )
                },
                onClick = { onChanged(count) }
            )

        }//end for

    }//end Row

}//end InteractionRatingBarView