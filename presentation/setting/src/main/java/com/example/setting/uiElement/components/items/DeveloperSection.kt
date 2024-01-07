package com.example.setting.uiElement.components.items

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.TextBoldView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun DeveloperSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    borderColor: Color,
    teamName: String,
    developers: List<String>,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
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
            .padding(
                bottom = dimen.dimen_3.dp,
                end = dimen.dimen_2.dp
            )
    ) {
        val (teamNameId, items) = createRefs()

        TextBoldView(
            theme = theme,
            dimen = dimen,
            text = teamName,
            size = dimen.dimen_1_25,
            color = theme.black,
            modifier = Modifier
                .constrainAs(teamNameId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        parent.top,
                        dimen.dimen_1_25.dp
                    )
                }
        )

        Column(
            modifier = Modifier
                .constrainAs(items) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_25.dp
                    )
                    top.linkTo(
                        teamNameId.bottom,
                        dimen.dimen_1_5.dp
                    )
                }
        ) {

            for (item in developers) {

                Spacer(
                    modifier = Modifier
                        .height(
                            dimen.dimen_0_25.dp
                        )
                )

                TextNormalView(
                    theme = theme,
                    dimen = dimen,
                    text = "- $item",
                    size = dimen.dimen_1,
                )

            }//end for

        }//end Row

    }//end ConstraintLayout

}//end DeveloperSection