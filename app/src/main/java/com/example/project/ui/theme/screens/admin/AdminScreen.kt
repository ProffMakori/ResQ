package com.example.project.ui.theme.screens.admin

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.project.navigation.ADDFIRSTAID_URL
import com.example.project.navigation.ADDHOSPITAL_URL
import com.example.project.navigation.DASHBOARD_URL
import com.example.project.navigation.HOME_URL
import com.example.project.navigation.HOSPITAL_URL
import com.example.project.ui.theme.ResQTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminScreen(navController: NavHostController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mContext = LocalContext.current as ComponentActivity

        //TopAppBar
        TopAppBar(title = { Text(text = "ADMIN INTERPHASE", color = Color.White) },
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
                IconButton(onClick = { val shareIntent=Intent(Intent.ACTION_SEND)
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "App Link")
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
        Text(
            text = "ADMIN",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {  navController.navigate(ADDHOSPITAL_URL) }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Update Hospitals")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = {   }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Update Police Stations page")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Update KPLC page")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Update Gender Violence page")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Update Nature Disasters page")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = " Update Mental Health page")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = " Update Covid page")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { navController.navigate(ADDFIRSTAID_URL)}, modifier = Modifier.fillMaxWidth()) {
            Text(text = " Update First Aid Kit page")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { }, modifier = Modifier.fillMaxWidth()) {
            Text(text = " AddFirst Videos")
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
            Text(text = " Send Notifications")

        }



    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun AdminScreenPreview() {
    ResQTheme {
        AdminScreen(navController = rememberNavController())
    }
}

