package com.example.sharedui.uiElement.style.theme

import androidx.compose.runtime.Composable

//Dark Mode
private val DarkColor = CustomTheme(
    background = White,
    redDark = RedDark,
    redIcon = RedIcon,
    darkBlue = BlueDark,
    blackLight = BlackLight,
    brownLight = brownLight,
    black = Black,
    blackFailed = BlackTitleFailed,
    gray = Gray,
    grayDark = GrayDark,
    redMedium = RedMedium,
    grayLight = GrayLight,
    grayLineLight = GrayLineLight,
    visibleGray = VisibleGray
)

//Light Mode
private val LightColor = CustomTheme(
    background = White,
    redDark = RedDark,
    redIcon = RedIcon,
    darkBlue = BlueDark,
    blackLight = BlackLight,
    brownLight = brownLight,
    black = Black,
    blackFailed = BlackTitleFailed,
    gray = Gray,
    grayDark = GrayDark,
    redMedium = RedMedium,
    grayLight = GrayLight,
    grayLineLight = GrayLineLight,
    visibleGray = VisibleGray
)

@Composable
fun MediSupportAppTheme(darkTheme: Boolean = false): CustomTheme {

    return if (darkTheme) {
        DarkColor
    } else {
        LightColor
    }
}//end MediSupportAppTheme