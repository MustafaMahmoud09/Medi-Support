package com.example.setting.uiElement.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun FeatureSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    icon: Painter,
    text: String,
    borderColor: Color
) {

    ConstraintLayout(
        modifier = Modifier
            .width(
                width = dimen.dimen_15.dp
            )
            .aspectRatio(
                ratio = .8f
            )
            .clip(
                shape = RoundedCornerShape(
                    dimen.dimen_1_25.dp
                )
            )
            .border(
                width = dimen.dimen_0_125.dp,
                color = borderColor,
                shape = RoundedCornerShape(
                    dimen.dimen_1_25.dp
                )
            )
    ) {
        val (iconId, textId) = createRefs()

        Image(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .constrainAs(iconId) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1.dp
                    )
                }
                .size(
                    size = dimen.dimen_3_5.dp
                )
        )

        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = text,
            defineLine = true,
            maxLines = 10,
            size = dimen.dimen_1,
            fontColor = theme.black,
            modifier = Modifier
                .constrainAs(textId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_0_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_0_5.dp
                    )
                    top.linkTo(
                        iconId.bottom,
                        dimen.dimen_1_75.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }

}//end FeatureSection