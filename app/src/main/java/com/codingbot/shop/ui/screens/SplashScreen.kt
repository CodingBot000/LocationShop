package com.codingbot.shop.ui.screens

import androidx.compose.material3.Text
import com.codingbot.shop.core.common.Screen
import com.codingbot.shop.ui.theme.CustomTheme

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
//    splashViewModel: SplashViewModel = hiltViewModel()
) {
//    val onBoardingIsCompleted by splashViewModel.onBoardingIsCompleted.collectAsState()
    val scale = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.8f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(100L)
        navController.popBackStack()

        navController.navigate(Screen.MainScreen.route)
//        if (onBoardingIsCompleted) navController.navigate(Graph.MAIN)
//        else navController.navigate(Screen.SelectListScreen.route)
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
            .background(CustomTheme.colors.bgOpposite)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .scale(scale)
                .padding(all = 64.dp),
            text = "Start"
        )
//        Image(
//            modifier = Modifier
//                .scale(scale)
//                .padding(all = 64.dp),
//            painter = painterResource(id = R.drawable.img_logo_app),
//            contentDescription = stringResource(R.string.logo_app)
//        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    Splash(scale = 0f)
}