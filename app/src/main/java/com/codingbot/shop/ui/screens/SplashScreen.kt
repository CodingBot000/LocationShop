package com.codingbot.shop.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.theme.CustomTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 1800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(100L)
        navController.popBackStack()

        navController.navigate(Screen.MainScreen.route)
    }
    Splash(scale = scale.value)
}

@Composable
fun Splash(
    modifier: Modifier = Modifier,
    scale: Float
) {
    Box(
        modifier = modifier
            .background(CustomTheme.colors.white)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .scale(scale)
                .padding(all = 64.dp),
            text = "This is SplashScreen",
            style = TextStyle(fontSize = 40.sp, color = CustomTheme.colors.textColorPrimary)

        )
//        Image(
//            modifier = Modifier
//                .scale(scale)
//                .padding(all = 64.dp),
//            painter = painterResource(id = ),
//            contentDescription = stringResource(R.string.)
//        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(scale = 0f)
}