package com.example.project.ui.theme.screens.dashboard

import android.content.Intent
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import androidx.compose.material3.TopAppBar as TopAppBar



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mContext = LocalContext.current
        //TopAppBar
        TopAppBar(
            title = { Text(text = "ResQ", color = Color.White) }, colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Red),
            navigationIcon = {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Default.Settings,
                        contentDescription = "settings", tint = Color.White)
                }
            },
            actions = {
                IconButton(onClick = {
                    val shareIntent= Intent(Intent.ACTION_SEND)
                    shareIntent.type="text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this is a cool content")
                    mContext.startActivity(Intent.createChooser(shareIntent, "Share"))
                }) {
                    Icon(imageVector = Icons.Default.Share, contentDescription ="share", tint = Color.White )
                }
                IconButton(onClick = {


                }) {
                    Icon(imageVector = Icons.Default.Notifications, contentDescription ="notifications", tint = Color.White )
                }
            })
        //End of TopAppBar
        Spacer(modifier = Modifier.height(30.dp))
        Column (
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "EMERGENCIES",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            //Row1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //card 1
                Card (
                    modifier = Modifier
                        .clickable {

                        }
                        .size(width = 150.dp, height = 120.dp)
                ){
                            Box(
                                modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
                            ) {
                                Image(painter = painterResource(id = R.drawable.demo), contentDescription = "Accident",
                                    modifier = Modifier.size(70.dp))
                            }
                                Text(
                                    text = "Accident",
                                    fontSize = 25.sp,
                                    color = Color.Red,
                                    FontWeight = FontWeight.Bold,
                                )
                }
            }
                //card 2
                Card {
                    Box {
                        Image(painter = painterResource(id = R.drawable.demo ), contentDescription = "Police")
                        Text(
                            text = "Police",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }
            //end of raw 1
            Spacer(modifier = Modifier.height(16.dp))
            //Row 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //card 1
                Card {
                    Box {
                        Image(painter = painterResource(id = R.drawable.demo), contentDescription = "FireOutBreak")
                        Text(
                            text = "FireOutBreak",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,

                            )

                    }
                }
                //card 2
                Card {
                    Box {
                        Image(painter = painterResource(id = R.drawable.demo), contentDescription = "Natural Disasters")
                        Text(
                            text = "Natural Disasters",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }

            }
            //end of raw 2
            Spacer(modifier = Modifier.height(16.dp))
            //Row3
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //card 1
                Card {
                    Box {
                        Image(painter = painterResource(id = R.drawable.demo), contentDescription = "Natural Disasters")
                        Text(
                            text = "Natural Disasters",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,

                            )

                    }

                }
                //card 2
                Card {
                    Box {
                        Image(painter = painterResource(id = R.drawable.demo), contentDescription = "Tracker")
                        Text(
                            text = "Tracker",
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,

                            )
                    }
                }

            }
            //end of raw 3
            Spacer(modifier = Modifier.height(16.dp))
        }

            // BottomBar
            BottomAppBar{


            }

                //End of BottomBar



    }

}


@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DashboardScreenPreview(){
    DashboardScreen(navController = rememberNavController())
}
