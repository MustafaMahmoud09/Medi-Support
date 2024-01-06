package com.example.profile.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.CircleImageView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun UserProfileSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    painter: Painter,
    onClick: () -> Unit,
    size: Float = dimen.dimen_18_75,
    borderColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    ConstraintLayout(
        modifier = modifier
            .width(
                (size + dimen.dimen_3).dp
            )
            .height(size.dp)
    ) {
        val (edit, profile) = createRefs()
        val guideFromBottom15P = createGuidelineFromBottom(.15f)

        Box(
            modifier = Modifier
                .constrainAs(profile) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .size(
                    size.dp
                )
                .clip(
                    shape = CircleShape
                )
                .border(
                    width = dimen.dimen_0_125.dp,
                    color = borderColor,
                    shape = CircleShape
                )
        ) {

            CircleImageView(
                painter = painter,
                contentDescription = "profile",
                modifier = Modifier
                    .fillMaxSize()
            )

        }//end Box

        Row(
            modifier = Modifier
                .constrainAs(edit) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(guideFromBottom15P)
                }
                .size(
                    dimen.dimen_3_5.dp
                )
                .clip(
                    shape = CircleShape
                )
                .background(
                    color = theme.redDark
                )
                .border(
                    width = dimen.dimen_0_125.dp,
                    color = theme.redDarkDash,
                    shape = CircleShape
                )
                .clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = null
                ) {
                    onClick()
                }
                .padding(
                    dimen.dimen_0_5.dp
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            Icon(
                painter = painterResource(
                    id = com.example.sharedui.R.drawable.edit
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(
                        dimen.dimen_1_5.dp
                    ),
                tint = theme.white
            )

        }//end Row

    }//end ConstraintLayout

}//end UserProfileSection