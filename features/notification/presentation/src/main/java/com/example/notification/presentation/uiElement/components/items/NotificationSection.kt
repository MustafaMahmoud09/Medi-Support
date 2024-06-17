package com.example.notification.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.notification.domain.model.NotificationModel
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme
import com.google.accompanist.placeholder.placeholder

@Composable
internal fun NotificationSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    contentSize: Float = dimen.dimen_2,
    contentColor: Color = theme.black,
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_25.dp
    ),
    notReadIconColor: Color = theme.redDark,
    notReadIconSize: Float = dimen.dimen_1_5,
    notReadIconShape: Shape = CircleShape,
    modifier: Modifier,
    notification: NotificationModel,
    placeHolderState: Boolean = false,
    onClick: (String, String, Long, Boolean) -> Unit,
    placeHolderColor: Color = theme.grayLight,
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .appBorder(
                borderWidth = borderSize,
                borderColor = if (placeHolderState) theme.grayLight else borderColor,
                shape = shape
            )
            .clickableWithoutHover {
                onClick(
                    notification.id,
                    notification.type,
                    notification.bookingId,
                    notification.read
                )
            }
            .padding(
                vertical = dimen.dimen_1_5.dp
            )
    ) {
        //create ids for components here
        val (contentId, notReadIconId) = createRefs()

        //create guides here
        val guideFromStart86P = createGuidelineFromStart(0.86f)

        //create notification content here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = notification.notification,
            size = contentSize,
            fontColor = contentColor,
            modifier = Modifier
                .constrainAs(contentId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_2_5.dp
                    )
                    end.linkTo(guideFromStart86P)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
                .placeholder(
                    visible = placeHolderState,
                    color = placeHolderColor
                )//end constrainAs
        )

        //if notification not read create this icon
        if (!notification.read) {

            //create icon here
            Spacer(
                modifier = Modifier
                    .size(
                        size = notReadIconSize.dp
                    )
                    .clip(
                        shape = notReadIconShape
                    )
                    .background(
                        color = notReadIconColor
                    )
                    .constrainAs(notReadIconId) {
                        start.linkTo(
                            guideFromStart86P,
                            dimen.dimen_1_25.dp
                        )
                        top.linkTo(
                            parent.top,
                            dimen.dimen_0_75.dp
                        )
                    }
                    .placeholder(
                        visible = placeHolderState,
                        color = placeHolderColor
                    )
            )

        }//end if

    }//end ConstraintLayout

}//end NotificationSection