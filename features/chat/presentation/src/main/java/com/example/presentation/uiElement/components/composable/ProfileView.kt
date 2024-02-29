package com.example.presentation.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.sharedui.uiElement.components.composable.LoadImageView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.OnlineView

@Composable
internal fun ProfileView(
    dimen: CustomDimen,
    theme: CustomTheme,
    imageSize: Float = dimen.dimen_5_5,
    imageBackground: Color = theme.redDark,
    imageShape: Shape = CircleShape,
    onlineIconShape: Shape = CircleShape,
    onlineIconSize: Float = dimen.dimen_1,
    onlineIconColor: Color = theme.green2BEF83,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
    ) {
        //create ids for screen components here
        val (profileImageId, onlineIconId) = createRefs()

        //create profile image here
        LoadImageView(
            painter = painterResource(
                id = R.drawable.doctor_test_3
            ),
            modifier = Modifier
                .constrainAs(profileImageId) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .clip(
                    shape = imageShape
                )
                .size(
                    size = imageSize.dp
                )
                .background(
                    color = imageBackground
                )
        )

        //create online icon here
        OnlineView(
            dimen = dimen,
            theme = theme,
            iconColor = onlineIconColor,
            iconShape = onlineIconShape,
            iconSize = onlineIconSize,
            modifier = Modifier
                .constrainAs(onlineIconId) {
                    bottom.linkTo(
                        parent.bottom,
                        dimen.dimen_0_5.dp
                    )
                    end.linkTo(
                        parent.end,
                        dimen.dimen_0_125.dp
                    )
                }//end constrainAs
        )

    }//end ConstraintLayout

}//end ProfileView