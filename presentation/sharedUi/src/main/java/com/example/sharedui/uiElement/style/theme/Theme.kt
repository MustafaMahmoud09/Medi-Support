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
    blackFailed = CoolGray,
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
    green = green,
    coolGray = CoolGray,
    redDarkTR50 = RedDarkTR50,
    green2BEF4A = green2BEF4A,
    grayC1C7CD = GrayC1C7CD,
    redDarkTR77 = RedDarkTR77,
    grayECECEC = GrayECECEC,
    grayD7D7D7 = GrayD7D7D7,
    grayDark646464 = GrayDark646464,
    blueLight7891B7 = BlueLight7891B7,
    redLightDB9E9C = RedLightDB9E9C,
    redSemiLightC66363 = RedSemiLightC66363,
    gold = Gold,
    blackTR25 = BlackTR25,
    redLightFE8499 = RedLightFE8499,
    binkLightFB5DAA = BinkLightFB5DAA,
    grayLightF2F2F2 = GrayLightF2F2F2,
    greenMed4CAF50 = greenMed4CAF50,
    grayDark6B6B6B = GrayDark6B6B6B,
    blue1DA1F2 = Blue1DA1F2,
    blackTR70 = BlackTR70,
    redLightFF979B = RedLightFF979B,
    redBF171DTR76 = RedBF171DTR76,
    blackLight353535 = BlackLight353535,
    black333333 = Black333333
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
    blackFailed = CoolGray,
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
    green = green,
    coolGray = CoolGray,
    redDarkTR50 = RedDarkTR50,
    green2BEF4A = green2BEF4A,
    grayC1C7CD = GrayC1C7CD,
    redDarkTR77 = RedDarkTR77,
    grayECECEC = GrayECECEC,
    grayD7D7D7 = GrayD7D7D7,
    grayDark646464 = GrayDark646464,
    blueLight7891B7 = BlueLight7891B7,
    redLightDB9E9C = RedLightDB9E9C,
    redSemiLightC66363 = RedSemiLightC66363,
    gold = Gold,
    blackTR25 = BlackTR25,
    redLightFE8499 = RedLightFE8499,
    binkLightFB5DAA = BinkLightFB5DAA,
    grayLightF2F2F2 = GrayLightF2F2F2,
    greenMed4CAF50 = greenMed4CAF50,
    grayDark6B6B6B = GrayDark6B6B6B,
    blue1DA1F2 = Blue1DA1F2,
    blackTR70 = BlackTR70,
    redLightFF979B = RedLightFF979B,
    redBF171DTR76 = RedBF171DTR76,
    blackLight353535 = BlackLight353535,
    black333333 = Black333333
)

@Composable
fun MediSupportAppTheme(darkTheme: Boolean = false): CustomTheme {

    return if (darkTheme) {
        DarkColor
    } else {
        LightColor
    }
}//end MediSupportAppTheme