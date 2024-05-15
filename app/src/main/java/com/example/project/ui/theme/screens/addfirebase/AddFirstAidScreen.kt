package com.example.project.ui.theme.screens.addfirebase

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.FirstAidViewModel
import com.example.project.data.HospitalViewModel
import com.example.project.navigation.EMERGENCY_URL
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFirstAidScreen(navController: NavHostController, viewModel: HospitalViewModel) {
    var condition by remember { mutableStateOf("") }
    var aidsStep by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val context = LocalContext.current as ComponentActivity

        // TopAppBar
        TopAppBar(
            title = { Text(text = "ADD FIRSTAIDKIT BOOKLET", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(EMERGENCY_URL) }) {
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
                        putExtra(Intent.EXTRA_TEXT, "AppLink")
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

        OutlinedTextField(
            value = condition,
            onValueChange = { condition = it },
            label = { Text("Condition Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = aidsStep,
            onValueChange = { aidsStep = it },
            label = { Text("Steps Aid") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        val viewModel: FirstAidViewModel = viewModel()
        Button(onClick = {
            viewModel.addFirstAid("Condition", "Aid Steps")
        }) {
            Text("Add First Aid Kit")
        }
    }
}

@Preview
@Composable
fun PreviewAddFirstAidScreen() {
    val viewModel = HospitalViewModel()
    val navController = rememberNavController()
    AddFirstAidScreen(navController = rememberNavController(), viewModel = viewModel)
}
