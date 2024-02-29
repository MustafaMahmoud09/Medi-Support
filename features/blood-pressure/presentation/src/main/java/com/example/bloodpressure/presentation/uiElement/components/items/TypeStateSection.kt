package com.example.bloodpressure.presentation.uiElement.components.items

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.components.composable.TextNormalView
import com.example.sharedui.uiElement.components.modifier.appBorder
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun TypeStateSection(
    theme: CustomTheme,
    dimen: CustomDimen,
    type: String,
    typeColor: Color = theme.redMedium,
    typeSize: Float = dimen.dimen_1_75,
    shape: Shape = RoundedCornerShape(
        percent = 90
    ),
    borderSize: Float = dimen.dimen_0_125,
    borderColor: Color = theme.redDark,
    modifier: Modifier = Modifier
) {

    //create container here
    Box(
        modifier = modifier
            .appBorder(
                shape = shape,
                borderWidth = borderSize,
                borderColor = borderColor
            )
            .padding(
                vertical = dimen.dimen_0_75.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        //create type here
        TextNormalView(
            theme = theme,
            dimen = dimen,
            text = type,
            size = typeSize,
            fontColor = typeColor,
        )

    }//end ConstraintLayout

}//end TypeStateSection
