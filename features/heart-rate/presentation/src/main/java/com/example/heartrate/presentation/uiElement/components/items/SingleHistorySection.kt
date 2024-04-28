package com.example.heartrate.presentation.uiElement.components.items

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.heart.rate.domain.domain.model.SimpleHeartRateModel
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

@Composable
fun SingleHistorySection(
    dimen: CustomDimen,
    theme: CustomTheme,
    roundSize: Float = dimen.dimen_0_75,
    borderColor: Color = theme.redDark,
    modifier: Modifier = Modifier,
    record: SimpleHeartRateModel,
    placeHolderState: Boolean = false,
    placeHolderColor: Color = theme.grayLight
) {

    Row(
        modifier = modifier
            .clip(
                shape = RoundedCornerShape(
                    roundSize.dp
                )
            )
            .placeholder(
                visible = placeHolderState,
                color = placeHolderColor
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
            text = record.type,
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
            text = "${record.rate}",
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
            text = stringResource(
                id = com.example.sharedui.R.string.bpm
            ),
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
            text = record.createdAt,
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
            text = record.dayName,
            size = dimen.dimen_1_75,
            fontColor = theme.black
        )

    }//end ConstraintLayout

}//end SingleHistorySection