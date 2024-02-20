package com.example.chat.uiElement.components.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.sharedui.uiElement.components.composable.BasicTextFieldViewII
import com.example.sharedui.uiElement.components.composable.IconButtonView
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoBold
import com.example.sharedui.uiElement.style.robotoMedium
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun EndIconEditTextView(
    dimen: CustomDimen,
    theme: CustomTheme,
    shape: Shape = RoundedCornerShape(
        size = dimen.dimen_1_5.dp
    ),
    background: Color = theme.grayF3F6F6,
    icon: Painter,
    onClickOnIcon: () -> Unit,
    tint: Color = theme.gray797C7B,
    iconSize: Float = dimen.dimen_3,
    value: String,
    onChanged: (String) -> Unit,
    editTextColor: Color = theme.blue0F0C22,
    editTextSize: Float = dimen.dimen_1_5,
    editTextFontFamily: FontFamily = robotoBold,
    hint: String,
    hintColor: Color = theme.gray797C7B,
    hintSize: Float = dimen.dimen_1_5,
    hintFontFamily: FontFamily = robotoMedium,
    maxLines: Int = 6,
    modifier: Modifier = Modifier
) {

    //create container here
    ConstraintLayout(
        modifier = modifier
            .clip(
                shape = shape
            )
            .background(
                color = background
            )
            .padding(
                vertical = dimen.dimen_1.dp
            )
    ) {
        //create ids for components here
        val (editTextId, iconId) = createRefs()

        //create icon here
        IconButtonView(
            dimen = dimen,
            theme = theme,
            onClick = onClickOnIcon,
            icon = icon,
            tint = tint,
            size = iconSize,
            modifier = Modifier
                .constrainAs(iconId) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(
                        parent.end,
                        dimen.dimen_1.dp
                    )
                }
        )

        //create edit text field here
        BasicTextFieldViewII(
            dimen = dimen,
            theme = theme,
            input = value,
            onChange = onChanged,
            textColor = editTextColor,
            fontSize = editTextSize,
            fontFamily = editTextFontFamily,
            hint = hint,
            hintColor = hintColor,
            hintFontFamily = hintFontFamily,
            hintSize = hintSize,
            maxLines = maxLines,
            modifier = Modifier
                .constrainAs(editTextId) {
                    start.linkTo(
                        parent.start,
                        dimen.dimen_1_5.dp
                    )
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(
                        iconId.start,
                        dimen.dimen_0_5.dp
                    )
                    width = Dimension.fillToConstraints
                }
        )

    }//end ConstraintLayout

}//end EditTextView