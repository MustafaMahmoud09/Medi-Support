package com.example.activity.uiElement.screens.history.child

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.activity.uiElement.components.items.AllHistorySection
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun BMIHistoryScreen(
    theme: CustomTheme,
    dimen: CustomDimen,
) {

    BMIHistoryContent(
        theme = theme,
        dimen = dimen,
        heightScreen = LocalConfiguration.current.screenHeightDp
    )
}//end BMIHistoryScreen

@Composable
private fun BMIHistoryContent(
    theme: CustomTheme,
    dimen: CustomDimen,
    heightScreen: Int,
) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = theme.background
            )
            .padding(
                top = dimen.dimen_1_5.dp,
                bottom = dimen.dimen_2.dp,
                start = dimen.dimen_2.dp,
                end = dimen.dimen_2.dp
            ),
    ) {

        AllHistorySection(
            dimen = dimen,
            theme = theme,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(
                    min = dimen.dimen_0.dp,
                    max = (heightScreen - dimen.dimen_17).dp
                )
        )

    }//end ConstraintLayout

}//end BMIHistoryContent