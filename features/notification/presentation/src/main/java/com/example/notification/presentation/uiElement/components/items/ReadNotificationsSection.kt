package com.example.notification.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.notification.presentation.uiElement.components.composable.CheckBoxView
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun ReadNotificationsSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    title: String,
    titleSize: Float = (dimen.dimen_1_5 + dimen.dimen_0_125),
    titleColor: Color = theme.blackLight353535,
    checkBoxSize: Float = dimen.dimen_1_25,
    checkColor: Color = theme.redDark,
    checkBoxBorderWidth: Float = dimen.dimen_0_125,
    checkBoxBorderShape: Shape = RectangleShape,
    checkBoxState: Boolean,
    onCheckBoxChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
){

    //create container here
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ){

        //create title here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = title,
            size = titleSize,
            fontColor = titleColor
        )

        //create padding here
        Spacer(
            modifier = Modifier
                .width(
                    width = dimen.dimen_1_25.dp
                )
        )

        //create read box here
        CheckBoxView(
            dimen = dimen,
            theme = theme,
            checked = checkBoxState,
            onChanged = onCheckBoxChanged,
            size = checkBoxSize,
            color = checkColor,
            borderWidth = checkBoxBorderWidth,
            borderShape = checkBoxBorderShape
        )

    }//end Row

}//end ReadNotificationsSection