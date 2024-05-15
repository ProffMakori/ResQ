package com.example.project.ui.theme.screens.emergencycontactacts

import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.core.net.toUri
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.HospitalViewModel
import com.example.project.models.Hospital
import com.example.project.navigation.EMERGENCY_URL
import com.example.project.ui.theme.ResQTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HospitalsScreen(navController: NavHostController, viewModel: HospitalViewModel){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mContext = LocalContext.current as ComponentActivity
        val hospitals = viewModel.hospitals.collectAsState(initial = emptyList()).value



        //TopAppBar
        TopAppBar(title = { Text(text = "Hospitals PhoneBook", color = Color.White) },
            colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate(EMERGENCY_URL)
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
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
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
            text = "HOSPITALS",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(30.dp))

        Column {
            hospitals.forEach { hospital ->
                HospitalRow(hospital = hospital)
            }
        }

    }

}


@Composable
fun HospitalRow(hospital: Hospital) {
    val mContext = LocalContext.current as ComponentActivity
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { // Handle navigation to dialer directly
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:${hospital.phoneNumber}")
                }
                mContext.startActivity(intent) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = hospital.name,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = hospital.phoneNumber,
            fontSize = 16.sp,
            color = Color.Gray
        )

    }

}



@Composable
@Preview(showBackground = true)
fun HospitalsScreenPreview(){
    ResQTheme {
        val viewModel = HospitalViewModel()

        // Create a NavHostController instance
        val navController = rememberNavController()

        // Pass both the view model and navController to the NotificationScreen
        HospitalsScreen(viewModel = viewModel,navController= rememberNavController())


    }
}