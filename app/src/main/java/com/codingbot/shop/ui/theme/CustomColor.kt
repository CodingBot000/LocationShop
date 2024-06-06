package com.codingbot.shop.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class CustomColors(
    textColorPrimary: Color,
    bg: Color,
    bgOpposite: Color,
    buttonBackground: Color,
    buttonBackgroundDisabled: Color,
    buttonIcon: Color,
    elementBarBackground: Color,
    elementBarResult: Color,
    orange60: Color,
    white: Color,
    black: Color,
) {
    var textColorPrimary by mutableStateOf(textColorPrimary)
        private set
    var bg by mutableStateOf(bg)
        private set
    var bgOpposite by mutableStateOf(bgOpposite)
        private set
    var buttonBackground by mutableStateOf(buttonBackground)
        private set
    var buttonBackgroundDisabled by mutableStateOf(buttonBackgroundDisabled)
        private set
    var buttonIcon by mutableStateOf(buttonIcon)
        private set
    var elementBarBackground by mutableStateOf(elementBarBackground)
        private set
    var elementBarResult by mutableStateOf(elementBarResult)
        private set
    var orange60 by mutableStateOf(orange60)
        private set

    var white by mutableStateOf(white)
        private set

    var black by mutableStateOf(black)
        private set

}

fun lightColors() = with(com.codingbot.shop.ui.theme.Color) {
    CustomColors(
        textColorPrimary = Black,
        bg = White,
        bgOpposite = Black,
        buttonBackground = Blue_30,
        buttonBackgroundDisabled = Gray_40,
        buttonIcon = Black.copy(alpha = .1f),
        elementBarBackground = Blue_Gray_10,
        elementBarResult = Red_40,
        orange60 = Orange_60 ,
        white = White,
        black = Black,
    )
}

fun darkColors() = with(com.codingbot.shop.ui.theme.Color) {
    CustomColors(
        textColorPrimary = White,
        bg = Gray_95,
        bgOpposite = White,
        buttonBackground = Blue_70,
        buttonBackgroundDisabled = Gray_70,
        buttonIcon = White.copy(alpha = .1f),
        elementBarBackground = Blue_Gray_50,
        elementBarResult = Red_70,
        orange60 = Orange_80,
        white = White,
        black = Black,
    )
}

val LocalColors = staticCompositionLocalOf { lightColors() }
