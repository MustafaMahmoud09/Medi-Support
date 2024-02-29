package com.example.sharedui.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
fun AllHistorySection(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_0_75.dp
    ),
    borderWidth: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .background(
                color = theme.background
            )
            .appBorder(
                shape = shape,
                borderWidth = borderWidth,
                borderColor = borderColor
            )
    ) {
        //create ids for components here
        val (histories) = createRefs()

        //create column contain on all histories here
        LazyColumn(
            modifier = Modifier
                .constrainAs(histories) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
            contentPadding = PaddingValues(
                horizontal = dimen.dimen_1.dp,
                vertical = dimen.dimen_2.dp
            ),
            verticalArrangement = Arrangement.spacedBy(
                space = dimen.dimen_2.dp
            )
        ) {

            //create history items here
            items(
                count = 20
            ) {

                //create single history here
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {

                    SingleHistorySection(
                        dimen = dimen,
                        theme = theme
                    )

                }//end Box

            }//end items

        }//end LazyColumn

    }//end ConstraintLayout

}//end AllHistorySection
