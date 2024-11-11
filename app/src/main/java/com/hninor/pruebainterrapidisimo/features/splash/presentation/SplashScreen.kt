package com.hninor.pruebainterrapidisimo.features.splash.presentation

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hninor.pruebainterrapidisimo.R
import com.hninor.pruebainterrapidisimo.core.theme.Pink80
import com.hninor.pruebainterrapidisimo.core.theme.Purple80
import com.hninor.pruebainterrapidisimo.core.theme.PurpleGrey80
import com.hninor.pruebainterrapidisimo.core.theme.primaryFontFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToLogin: () -> Unit) {

    val scaleAnimation: Animatable<Float, AnimationVector1D> =
        remember { Animatable(initialValue = 0f) }

    AnimationSplashContent(
        scaleAnimation = scaleAnimation,
        navigateToLogin = navigateToLogin,
        durationMillisAnimation = 1500,
        delayScreen = 3000L
    )

    DesignSplashScreen(
        imagePainter = painterResource(id = R.drawable.logo),
        scaleAnimation = scaleAnimation
    )
}


@Composable
fun DesignSplashScreen(
    modifier: Modifier = Modifier,
    imagePainter: Painter,
    scaleAnimation: Animatable<Float, AnimationVector1D>
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Purple80,
                        PurpleGrey80,
                        Pink80,
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = imagePainter,
            contentDescription = "Logotipo Splash Screen",
            modifier = modifier
                .size(300.dp)
                .scale(scale = scaleAnimation.value),
        )

        Text(
            text = stringResource(id = R.string.bienvenidos_reclutadores),
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = primaryFontFamily,
            textAlign = TextAlign.Center,
            modifier = modifier
                .padding(16.dp)
                .scale(scale = scaleAnimation.value),
            lineHeight = 40.sp
        )
    }

}


@Preview(showBackground = true)
@Composable
fun SplashPreview() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Purple80,
                        PurpleGrey80,
                        Pink80,
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logotipo Splash Screen",
            modifier = Modifier
                .size(300.dp)

        )

        Text(
            text = stringResource(id = R.string.bienvenidos_reclutadores),
            color = Color.White,
            fontSize = 40.sp,
            fontFamily = primaryFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp),
            lineHeight = 40.sp

        )
    }
}


@Composable
fun AnimationSplashContent(
    scaleAnimation: Animatable<Float, AnimationVector1D>,
    navigateToLogin: () -> Unit,
    durationMillisAnimation: Int,
    delayScreen: Long
) {

    LaunchedEffect(key1 = true) {
        scaleAnimation.animateTo(
            targetValue = 1F,
            animationSpec = tween(
                durationMillis = durationMillisAnimation,
                easing = {
                    OvershootInterpolator(3F).getInterpolation(it)
                }
            )
        )

        delay(timeMillis = delayScreen)

        navigateToLogin()
    }
}