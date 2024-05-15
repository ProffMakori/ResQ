package com.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.project.data.HospitalViewModel
import com.example.project.data.NotificationRepository
import com.example.project.navigation.AppNavHost


class MainActivity : ComponentActivity() {
    val notificationRepository = NotificationRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
            AppNavHost(notificationRepository = notificationRepository, viewModel = viewModel)
        }
    }
}


