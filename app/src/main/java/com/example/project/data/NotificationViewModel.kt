package com.example.project.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.project.models.Notification
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await as await


class NotificationViewModel(private val repository: NotificationRepository) : ViewModel() {

    val notifications: LiveData<List<Notification>> = liveData(Dispatchers.IO) {
        emit(repository.getNotifications())
    }
}
class NotificationRepository {
        private val db = FirebaseFirestore.getInstance()

        // Function to fetch notifications from Firebase Firestore
        suspend fun getNotifications(): List<Notification> {
            return try {
                val snapshot = db.collection("notifications").get().await()
                snapshot.documents.mapNotNull { doc ->
                    doc.toObject(Notification::class.java)
                }
            } catch (e: Exception) {
                // Handle exceptions
                emptyList()
            }
        }

    }