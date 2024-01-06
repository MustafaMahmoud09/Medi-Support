package com.example.sharedui.uiElement.style.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

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
    visibleGray = VisibleGray,
    greenLight = greenLight,
    hintIconBottom = hintIconBottom,
    redDarkDash = RedDarkDash,
    white = White,
    transparent = Color.Transparent,
    green = green
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
    visibleGray = VisibleGray,
    greenLight = greenLight,
    hintIconBottom = hintIconBottom,
    redDarkDash = RedDarkDash,
    white = White,
    transparent = Color.Transparent,
    green = green
)

@Composable
fun MediSupportAppTheme(darkTheme: Boolean = false): CustomTheme {

    return if (darkTheme) {
        DarkColor
    } else {
        LightColor
    }
}//end MediSupportAppTheme