package com.example.project.ui.theme.screens.splash



import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.project.R
import com.example.project.navigation.DASHBOARD_URL
import com.example.project.ui.theme.ResQTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SplashScreen(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val coroutine = rememberCoroutineScope()
        coroutine.launch {
            delay(4000)
            navController.navigate(DASHBOARD_URL)
        }

        // Load Lottie animation from assets folder
        val lottieComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation))

        // Control animation playback
        val progress by animateLottieCompositionAsState(
            composition = lottieComposition,
            iterations = LottieConstants.IterateForever
        )

        // Display the animation
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            LottieAnimation(
                composition = lottieComposition,
                progress = progress,
                modifier = Modifier.size(300.dp)
            )
        }
    }


}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun SplashScreenPreview(){
    ResQTheme {
        SplashScreen(navController = rememberNavController())
    }

}