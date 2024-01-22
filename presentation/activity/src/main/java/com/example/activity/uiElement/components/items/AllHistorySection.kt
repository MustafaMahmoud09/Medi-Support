package com.example.activity.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun AllHistorySection(
    dimen: CustomDimen,
    theme: CustomTheme,
    roundSize: Float = dimen.dimen_0_75,
    borderColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .background(
                color = theme.background
            )
            .clip(
                shape = RoundedCornerShape(
                    roundSize.dp
                )
            )
            .border(
                width = dimen.dimen_0_125.dp,
                color = borderColor,
                shape = RoundedCornerShape(
                    roundSize.dp
                )
            )
    ) {
        val (histories) = createRefs()

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

            items(
                count = 20
            ) {

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