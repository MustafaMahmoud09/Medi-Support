@file:OptIn(ExperimentalFoundationApi::class)

package com.example.auth.uiElement.components.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sharedui.uiElement.style.dimens.CustomDimen
import com.example.sharedui.uiElement.style.theme.CustomTheme

@Composable
internal fun TabCircleView(
    dimen: CustomDimen,
    theme: CustomTheme,
    pagerState: PagerState,
    currentItem: Int
) {

    Spacer(
        modifier = Modifier
            .size(dimen.dimen_2.dp)
            .clip(CircleShape)
            .border(
                width = dimen.dimen_0_125.dp,
                color = theme.redDark,
                shape = CircleShape
            )
            .background(
                color = if (pagerState.currentPage == currentItem) theme.redDark else theme.background
            )
    )

}