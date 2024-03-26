package com.example.offlinebooking.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharedui.R
import com.example.sharedui.uiElement.components.composable.IconView
import com.example.sharedui.uiElement.components.composable.TextSemiBoldView
import com.example.sharedui.uiElement.components.modifier.appShadow
import com.example.sharedui.uiElement.components.modifier.clickableWithoutHover
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BookingChatSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    title: String,
    titleColor: Color,
    titleSize: Float = dimen.dimen_1_5,
    background: Color,
    shape: Shape = RoundedCornerShape(
        percent = 90
    ),
    elevation: Float = dimen.dimen_0_5,
    ambientColor: Color = theme.black,
    spotColor: Color = theme.black,
    height: Float = dimen.dimen_2_5,
    chatIcon: Painter = painterResource(
        id = R.drawable.chat_icon
    ),
    chatIconSize: Float = dimen.dimen_2_5,
    chatIconTint: Color = theme.greenLight,
    onClickOnChatButton: () -> Unit,
    modifier: Modifier = Modifier
) {

    //create container here
    Row(
        modifier = modifier
            .appShadow(
                shape = shape,
                elevation = elevation,
                ambientColor = ambientColor,
                spotColor = spotColor
            )
            .height(
                height = height.dp
            )
            .background(
                color = background
            )
            .clickableWithoutHover {
                onClickOnChatButton()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        //create chat button here
        IconView(
            dimen = dimen,
            theme = theme,
            size = chatIconSize,
            icon = chatIcon,
            tint = chatIconTint,
            modifier = Modifier
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .width(
                    width = dimen.dimen_1.dp
                )
        )

        //create title here
        TextSemiBoldView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor,
            modifier = Modifier
        )

    }//end Box

}//end BookingChatSection