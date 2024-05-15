package com.example.project.ui.theme.screens.dashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.R
import com.example.project.navigation.ADDFIRSTAID_URL
import com.example.project.navigation.ADMIN_URL
import com.example.project.navigation.EMERGENCY_URL
import com.example.project.navigation.FIRSTAID_URL
import com.example.project.navigation.SETTINGS_URL
import com.example.project.ui.theme.ResQTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {
    private val _isMenuOpen = MutableStateFlow(false)
    val isMenuOpen: StateFlow<Boolean> get() = _isMenuOpen

    fun openMenu() {
        _isMenuOpen.value = true
    }
    fun onFabClick() {
        _isMenuOpen.value = !_isMenuOpen.value
    }

    fun closeMenu() {
        _isMenuOpen.value = false
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController,viewModel: MyViewModel){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mContext = LocalContext.current as ComponentActivity
        // Check if the CALL_PHONE permission is granted
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Permission granted, initiate the call
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = "tel:0711642436".toUri()
                mContext.startActivity(callIntent)
            } else {
                // Permission denied, handle accordingly
                // Maybe show a message to the user or disable the functionality
            }
        }


        var selected by remember { mutableIntStateOf(0) }
        var showMenu by remember { mutableStateOf(false) }
        Scaffold(
            bottomBar = {
                NavigationBar {
                    bottomNavItems.forEachIndexed { index, bottomNavItem ->
                        NavigationBarItem(
                            selected = index == selected,
                            onClick = {
                                selected = index
                                navController.navigate(bottomNavItem.route)
                            },
                            icon = {
                                BadgedBox(
                                    badge = {
                                        if (bottomNavItem.badges != 0) {
                                            Badge {
                                                Text(text = bottomNavItem.badges.toString())
                                            }
                                        } else if (bottomNavItem.hasNews) {
                                            Badge()
                                        }
                                    }
                                ) {
                                    Icon(imageVector =
                                    if (index == selected)
                                        bottomNavItem.selectedIcon
                                    else
                                        bottomNavItem.unselectedIcon,
                                        contentDescription = bottomNavItem.title)
                                }

                            },
                            label = {
                                Text(text = bottomNavItem.title)
                            })
                    }
                }
            },


            floatingActionButton = {
                FloatingActionButton(onClick = { viewModel.onFabClick()}) {
                    Icon(

                        imageVector = Icons.Default.Add,
                        contentDescription = "Scroll up"
                    )
                    val isMenuOpen by viewModel.isMenuOpen.collectAsState()
                    // or observeAsState() if using LiveData
                    // Display the menu if the FAB is clicked
                    if (isMenuOpen) {
                        MenuContent(navController = navController, onOutsideClick = { viewModel.closeMenu() })
                    }

                }
            },



            //Content Section
            content = @Composable{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Text(
                        text = "EMERGENCY",
                        fontFamily = FontFamily.Cursive,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 40.sp,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )

                    // Row 1

                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 4.dp)
                            .height(180.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {

                        Card(onClick ={
                            // Check if permission is already granted
                            if (ContextCompat.checkSelfPermission(
                                    mContext,
                                    android.Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permission granted, initiate the call
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = "tel:0711642436".toUri()
                                mContext.startActivity(callIntent)
                            } else {
                                // Permission not granted, request it
                                launcher.launch(android.Manifest.permission.CALL_PHONE)
                            }

                        }) {
                            Box{
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.accident),
                                        contentDescription = "Accident",
                                        modifier = Modifier
                                            .padding(start = 10.dp, top = 4.dp)
                                            .size(120.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Accident",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .padding(start = 30.dp,top = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Card(onClick = {
                            // Check if permission is already granted
                            if (ContextCompat.checkSelfPermission(
                                    mContext,
                                    android.Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permission granted, initiate the call
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = "tel:0711642436".toUri()
                                mContext.startActivity(callIntent)
                            } else {
                                // Permission not granted, request it
                                launcher.launch(android.Manifest.permission.CALL_PHONE)
                            }


                        }) {
                            Box {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.fire),
                                        contentDescription = "fire",
                                        modifier = Modifier
                                            .padding(start = 10.dp, top = 4.dp)
                                            .size(120.dp), contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Fire",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .padding(start = 40.dp, top = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }

                    }
                    //End of Row 2
                    Spacer(modifier = Modifier.height(10.dp))
                    // Row 2

                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 4.dp)
                            .height(180.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {

                        Card(onClick = {
                            // Check if permission is already granted
                            if (ContextCompat.checkSelfPermission(
                                    mContext,
                                    android.Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permission granted, initiate the call
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = "tel:0711642436".toUri()
                                mContext.startActivity(callIntent)
                            } else {
                                // Permission not granted, request it
                                launcher.launch(android.Manifest.permission.CALL_PHONE)
                            }

                        }) {
                            Box {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.police),
                                        contentDescription = "police",
                                        modifier = Modifier
                                            .padding(start = 10.dp, top = 4.dp)
                                            .size(120.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Police",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .padding(start = 30.dp, top = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.width(10.dp))

                        Card(onClick = {
                            // Check if permission is already granted
                            if (ContextCompat.checkSelfPermission(
                                    mContext,
                                    android.Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permission granted, initiate the call
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = "tel:0711642436".toUri()
                                mContext.startActivity(callIntent)
                            } else {
                                // Permission not granted, request it
                                launcher.launch(android.Manifest.permission.CALL_PHONE)
                            }

                        }) {
                            Box {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.medical),
                                        contentDescription = "medical",
                                        modifier = Modifier
                                            .padding(start = 10.dp, top = 4.dp)
                                            .size(120.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "medical",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .padding(start = 30.dp, top = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }

                    }
                    //End of Row 2
                    Spacer(modifier = Modifier.height(10.dp))
                    // Row 3

                    Row(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 10.dp)
                            .height(180.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {

                        Card(onClick = {
                            // Check if permission is already granted
                            if (ContextCompat.checkSelfPermission(
                                    mContext,
                                    android.Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permission granted, initiate the call
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = "tel:0711642436".toUri()
                                mContext.startActivity(callIntent)
                            } else {
                                // Permission not granted, request it
                                launcher.launch(android.Manifest.permission.CALL_PHONE)
                            }

                        }) {
                            Box {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.breakdown),
                                        contentDescription = "Breakdown",
                                        modifier = Modifier
                                            .padding(start = 10.dp, top = 4.dp)
                                            .size(120.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Breakdown",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(start = 40.dp, top = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }

                        }
                        Spacer(modifier = Modifier.width(30.dp))

                        Card(onClick = {
                            // Check if permission is already granted
                            if (ContextCompat.checkSelfPermission(
                                    mContext,
                                    android.Manifest.permission.CALL_PHONE
                                ) == PackageManager.PERMISSION_GRANTED
                            ) {
                                // Permission granted, initiate the call
                                val callIntent = Intent(Intent.ACTION_CALL)
                                callIntent.data = "tel:0711642436".toUri()
                                mContext.startActivity(callIntent)
                            } else {
                                // Permission not granted, request it
                                launcher.launch(android.Manifest.permission.CALL_PHONE)
                            }

                        }) {
                            Box {
                                Column {
                                    Image(
                                        painter = painterResource(id = R.drawable.general),
                                        contentDescription = "Other",
                                        modifier = Modifier
                                            .padding(start = 10.dp, top = 4.dp)
                                            .size(120.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Other",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 25.sp,
                                        modifier = Modifier.padding(start = 40.dp, top = 4.dp),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }



                    }
                    //End of Row 3


                    //Row 4
                    //Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 80.dp),
                        contentAlignment = Alignment.BottomStart
                    ) {


                        Image(
                            painter = painterResource(id = R.drawable.img) ,
                            contentDescription = "EmergencyContacts",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(EMERGENCY_URL)
                                }
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = " List of Contacts For  Emergencies in Kenya",
                                fontSize = 30.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }

                    }
                    //End of box

                    //End of Row 4




                }

            },
            topBar = {
                //TopAppBar
                TopAppBar(title = { Text(text = "ResQ", color = Color.White, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()) },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(Color.Magenta),
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.navigate(SETTINGS_URL)
                        })
                        {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "settings",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { val shareIntent=Intent(Intent.ACTION_SEND)
                            shareIntent.type="text/plain"
                            shareIntent.putExtra(Intent.EXTRA_TEXT, "APP LINK")
                            mContext.startActivity(Intent.createChooser(shareIntent, "Share"))}) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "share",
                                tint = Color.White
                            )
                        }


                    }

                )
                //End of TopAppbar
            }

        )

    }
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route="dashboard",
        selectedIcon=Icons.Filled.Home,
        unselectedIcon=Icons.Outlined.Home,
        hasNews = false,
        badges=5
    ),



    BottomNavItem(
        title = "Alert",
        route="Notifications",
        selectedIcon=Icons.Filled.Notifications,
        unselectedIcon=Icons.Outlined.Notifications,
        hasNews = true,
        badges=5
    ),
    BottomNavItem(
        title = "Pofile",
        route="Profile",
        selectedIcon=Icons.Filled.Person,
        unselectedIcon=Icons.Outlined.Person,
        hasNews = true,
        badges=1
    ),
    BottomNavItem(
        title = "Videos",
        route="videos",
        selectedIcon=Icons.Default.PlayArrow,
        unselectedIcon=Icons.Outlined.PlayArrow,
        hasNews = true,
        badges=1
    ),


    )

data class BottomNavItem(
    val title :String,
    val route :String,
    val selectedIcon: ImageVector,
    val unselectedIcon :ImageVector,
    val hasNews :Boolean,
    val badges :Int
)

@Composable
fun MenuContent(navController: NavHostController, onOutsideClick: () -> Unit) {

    // Your menu content here
    // Example:
    Column(
        modifier = Modifier
            .size(250.dp)
            .padding(16.dp)
            .background(Color.Black)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onOutsideClick() }
    ) {
        Text(text = "First Aid Kit", modifier = Modifier.clickable { (navController.navigate(
            FIRSTAID_URL)) }, fontWeight = FontWeight.Bold,  color = Color.White,textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(10.dp))
        Text("Admin", modifier = Modifier.clickable { ((navController.navigate(
            ADMIN_URL)) ) }, color = Color.White,fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Rate This App", modifier = Modifier.clickable { ("Item 1") }, fontWeight = FontWeight.Bold,  color = Color.White, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun DashboardScreenPreview(){

    ResQTheme {
        DashboardScreen(navController = rememberNavController(), viewModel = MyViewModel())
    }
}





