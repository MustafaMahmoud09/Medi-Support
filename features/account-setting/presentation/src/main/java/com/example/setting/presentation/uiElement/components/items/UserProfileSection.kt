package com.example.setting.presentation.uiElement.components.items

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.LoadImageViewII
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun UserProfileSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    painter: Uri?,
    onClick: () -> Unit,
    size: Float = dimen.dimen_18_75,
    profileBorderColor: Color = theme.redDark,
    profileBorderWidth: Float = dimen.dimen_0_125,
    profileShape: Shape = CircleShape,
    editShape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    editBorderColor: Color = theme.redDarkDash,
    editBorderWidth: Float = dimen.dimen_0_125,
    editBoxWidth: Float = dimen.dimen_3,
    editBoxHeight: Float = dimen.dimen_2_5,
    editIconSize: Float = dimen.dimen_1_5,
    editIconTint: Color = theme.white,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .width(
                (size + dimen.dimen_3).dp
            )
            .height(size.dp)
    ) {
        //create ids for components here
        val (edit, profile) = createRefs()
        //create guides here
        val guideFromBottom15P = createGuidelineFromBottom(.15f)

        //create profile image box here
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
                .appBorder(
                    borderWidth = profileBorderWidth,
                    borderColor = profileBorderColor,
                    shape = profileShape
                )
        ) {

            //create image here
            painter?.let {
                LoadImageViewII(
                    imageUri = it,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(
                            shape = CircleShape
                        )
                )
            }

        }//end Box

        //create edit box here
        Box(
            modifier = Modifier
                .constrainAs(edit) {
                    end.linkTo(
                        parent.end,
                        dimen.dimen_2.dp
                    )
                    bottom.linkTo(guideFromBottom15P)
                }
                .size(
                    width = editBoxWidth.dp,
                    height = editBoxHeight.dp
                )
                .appBorder(
                    borderColor = editBorderColor,
                    borderWidth = editBorderWidth,
                    shape = editShape
                )
                .background(
                    color = theme.redDark
                )
                .clickableWithoutHover {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {

            //create edit icon here
            Icon(
                painter = painterResource(
                    id = com.example.sharedui.R.drawable.edit
                ),
                contentDescription = "",
                modifier = Modifier
                    .size(
                        size = editIconSize.dp
                    ),
                tint = editIconTint
            )

        }//end Box

    }//end ConstraintLayout

}//end UserProfileSection