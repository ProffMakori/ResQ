package com.example.project.ui.theme.screens.firstaid

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.FirstAidViewModel
import com.example.project.models.FirstAidKit
import com.example.project.ui.theme.ResQTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirstAidScreen(navController: NavHostController, viewModel: FirstAidViewModel) {
    val context = LocalContext.current as ComponentActivity
    val firstaids = viewModel.firstaids.collectAsState(initial = emptyList()).value

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // TopAppBar
        TopAppBar(
            title = { Text(text = "First Aid Kit Booklet", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = { navController.navigate("dashboard_url") }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = {
                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, "Check out this cool content")
                    }
                    context.startActivity(Intent.createChooser(shareIntent, "Share"))
                }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "share",
                        tint = Color.White
                    )
                }
            }
        )
        // End of TopAppBar

        Text(
            text = "FIRST AID KIT",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            modifier = Modifier.padding(vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            firstaids.forEach { firstaid ->
                FirstAidKitColumn(firstaid = firstaid)
            }
        }
    }
}

@Composable
fun FirstAidKitColumn(firstaid: FirstAidKit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(
            text = firstaid.Condition,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = firstaid.AidStep,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FirstAidScreenPreview() {
    ResQTheme {
        val viewModel = FirstAidViewModel()  // Ideally use a mock ViewModel for previews
        val navController = rememberNavController()
        FirstAidScreen(navController = navController, viewModel = viewModel)
    }
}
