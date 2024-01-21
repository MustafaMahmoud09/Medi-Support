package com.example.activity.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun SingleHistorySection(
    dimen: CustomDimen,
    theme: CustomTheme,
    roundSize: Float = dimen.dimen_0_75,
    borderColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
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
            .padding(
                horizontal = dimen.dimen_2.dp,
                vertical = dimen.dimen_1_5.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = "Normal",
            size = dimen.dimen_1_75,
            color = theme.redDark
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = ":",
            size = dimen.dimen_1_75,
            color = theme.redDark
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = "80",
            size = dimen.dimen_1_75,
            color = theme.black
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "Kg",
            size = dimen.dimen_1_75,
            fontColor = theme.black
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "|",
            size = dimen.dimen_1_75,
            fontColor = theme.redDark
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "24-11-2023",
            size = dimen.dimen_1_75,
            fontColor = theme.black
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "|",
            size = dimen.dimen_1_75,
            fontColor = theme.redDark
        )

        Spacer(
            modifier = Modifier
                .width(
                    dimen.dimen_0_5.dp
                )
        )

        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = "Tue",
            size = dimen.dimen_1_75,
            fontColor = theme.black
        )

    }//end ConstraintLayout

}//end SingleHistorySection