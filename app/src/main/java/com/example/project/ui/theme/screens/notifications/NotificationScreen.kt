package com.example.project.ui.theme.screens.notifications

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.project.data.NotificationRepository
import com.example.project.data.NotificationViewModel
import com.example.project.models.Notification
import androidx.compose.material3.Card as Card


@Composable
fun NotificationScreen(navController: NavHostController, notificationViewModel: NotificationViewModel) {
    val notifications by notificationViewModel.notifications.observeAsState()
    // Display notifications
    Column {
        Text("Notifications",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            textAlign = TextAlign.Center)
        notifications?.let { notificationList: List<Notification>? -> // Specify the type explicitly
            notificationList?.forEach { notification ->
                Card(

                    modifier = Modifier
                       .padding(8.dp)
                       .fillMaxWidth(),

                    ) {
                    Column(
                       modifier = Modifier.padding(16.dp)
                    ) {
                       Text(text = notification.title, fontWeight = FontWeight.Bold)
                       Text(text = notification.message)
                   }
               }
            }
        }
    }
}




@Composable
fun <T : Any?> LiveData<T>.observeAsState(): State<T?> {
    val state = remember { mutableStateOf<T?>(null) }

    // Set up the observer
    val observer = Observer<T> { value ->
        state.value = value
    }

    // Observe the LiveData
    observeForever(observer)

    // Clean up when the composable is destroyed
    DisposableEffect(this) {
        onDispose {
            removeObserver(observer)
        }
    }

    return state
}

fun mockRepository(): NotificationRepository {
    // Create a mock repository instance with dummy data for testing purposes
    return NotificationRepository()
}


@Composable
@Preview(showBackground = true)
fun NotificationScreenPreview() {
    // Create a mock repository instance for testing purposes
    val mockRepository = mockRepository()

    // Create the NotificationViewModel instance with the mock repository
    val notificationViewModel = NotificationViewModel(repository = mockRepository)

    // Create a NavHostController instance
    val navController = rememberNavController()

    // Pass both the view model and navController to the NotificationScreen
    NotificationScreen(navController = navController, notificationViewModel = notificationViewModel)
}
