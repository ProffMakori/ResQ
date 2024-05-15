package com.example.project.ui.theme.screens.settings

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.SettingsViewModel
import com.example.project.navigation.ABOUT_URL
import com.example.project.navigation.DASHBOARD_URL
import com.example.project.ui.theme.ResQTheme



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController:NavHostController,viewModel: SettingsViewModel) {
    val darkModeEnabled by viewModel.darkModeEnabled.observeAsState(false)
    val language by viewModel.language.observeAsState("English")
    val mContext = LocalContext.current as ComponentActivity

    Column(
        modifier = Modifier.fillMaxSize(),
        
    ) {
        
        //TopAppBar
        TopAppBar(title = { Text(text = "Settings", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(DASHBOARD_URL)
                })
                {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { val shareIntent= Intent(Intent.ACTION_SEND)
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "App link")
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share")) }) {
                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "share",
                        tint = Color.White
                    )
                }



            }

        )
        //End of TopAppbar
        
        Row (modifier = Modifier
            .background(color = Color.Black)
            .fillMaxWidth()){
            DarkModeSwitch(darkModeEnabled) { viewModel.toggleDarkMode(it) }
        }
        Spacer(modifier = Modifier.height(16.dp))
       Row (modifier = Modifier
           .background(color = Color.Black)
           .fillMaxWidth()){
           LanguageSelection(language) { viewModel.changeLanguage(it) }
       }
        Spacer(modifier = Modifier.height(16.dp))
       Row (modifier = Modifier
           .background(color = Color.Black)
           .fillMaxWidth()){
           Text(text = "About The App", modifier = Modifier
                    .padding(16.dp)
                     .clickable {
                   navController.navigate(
                       ABOUT_URL
                   )
               },
               color = Color.White
               )
       }
    }
}

@Composable
private fun <T> LiveData<T>.observeAsState(defaultValue: T): State<T> {
    val state = remember { mutableStateOf(defaultValue) }
    observeForever { value ->
        state.value = value ?: defaultValue
    }
    DisposableEffect(this) {
        onDispose {
            removeObserver { }
        }
    }
    return state
}


@Composable
fun DarkModeSwitch(darkModeEnabled: Boolean, onToggle: (Boolean) -> Unit) {
  Row {
      Column {
          Text(
              text = "DarkMode",
              modifier = Modifier
                  .background(color = Color.Black)
                  .padding(16.dp),
              color = Color.White
          )
      }

      Column {
          Switch(
              checked = darkModeEnabled,
              onCheckedChange = onToggle,
              modifier = Modifier.padding(10.dp)
          )
      }
  }
}

@Composable
fun LanguageSelection(language: String, onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("English", "Spanish", "French", "German") // Add more languages as needed
    val selectedIndex = languages.indexOf(language)

    Box(
        modifier = Modifier
            .padding(16.dp)
            .clickable { expanded = true }
    ) {
        Text(
            text = "Language: $language",
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEachIndexed { index, lang ->
                DropdownMenuItem(text = {  Text(text = lang, style = if (index == selectedIndex) TextStyle(color = Color.Black) else TextStyle(color = Color.Gray))},
                    onClick = { onLanguageSelected(lang)
                    expanded = false})


            }
        }
    }
}



@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    ResQTheme {
        SettingsScreen(navController = rememberNavController(), viewModel = SettingsViewModel())
    }
}

