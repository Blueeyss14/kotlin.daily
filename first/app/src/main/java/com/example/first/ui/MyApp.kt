package com.example.first.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp

@Composable
fun MyApp() {
    var isClicked by remember { mutableStateOf(false) }
    var boxWidthPx by remember { mutableStateOf(0) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidthPx = with(density) { configuration.screenWidthDp.dp.toPx() }

    val targetOffsetPx = if (isClicked) 0f else screenWidthPx - boxWidthPx

    val animatedOffsetPx by animateFloatAsState(
        targetValue = targetOffsetPx,
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        ),
        label = "offset"
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { isClicked = !isClicked }) {
                Text("Toggle")
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                modifier = Modifier
                    .offset { IntOffset(animatedOffsetPx.toInt(), 0) }
                    .onGloballyPositioned {
                        boxWidthPx = it.size.width
                    }
                    .background(Color.Green)
                    .height(100.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("This is Dynamic Content")
            }
        }
    }
}
