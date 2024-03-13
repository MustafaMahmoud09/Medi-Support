package com.example.auth.presentation.uiElement.components.items

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Checkbox
import androidx.compose.material.Text
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedui.R
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.robotoRegular
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun RememberSection(
    dimen: CustomDimen,
    theme: CustomTheme,
    fontColor: Color,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = Modifier
                .size(
                    dimen.dimen_3.dp
                ),
            colors = CheckboxDefaults.colors(
                checkedColor = theme.redDark,
                uncheckedColor = theme.redDark
            )
        )

        Spacer(
            modifier = Modifier
                .width(dimen.dimen_1.dp)
        )

        Text(
            text = stringResource(
                R.string.remember_me
            ),
            fontSize = dimen.dimen_1_75.sp,
            fontFamily = robotoRegular,
            color = fontColor
        )

    }//end Row

}//end RememberSection