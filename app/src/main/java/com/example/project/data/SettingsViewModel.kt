package com.example.project.data

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.models.UserSettings
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.firestore


class SettingsViewModel : ViewModel() {
    private val _darkModeEnabled = MutableLiveData<Boolean>()
    val darkModeEnabled: LiveData<Boolean> = _darkModeEnabled

    private val _language = MutableLiveData<String>()
    val language: LiveData<String> = _language

    init {
        // Load user settings from Firestore
        readUserSettings("userId") { userSettings ->
            _darkModeEnabled.value = userSettings.darkModeEnabled
            _language.value = userSettings.language
        }
    }

    fun toggleDarkMode(enabled: Boolean) {
        _darkModeEnabled.value = enabled
        val userSettings = UserSettings(enabled, _language.value ?: "English")
        writeUserSettings("userId", userSettings)
    }

    fun changeLanguage(language: String) {
        _language.value = language
        val userSettings = UserSettings(_darkModeEnabled.value ?: false, language)
        writeUserSettings("userId", userSettings)
    }
}
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}

// Read and write data to Firestore
@SuppressLint("StaticFieldLeak")
val db = Firebase.firestore



// Read user settings from Firestore
fun readUserSettings(userId: String, onComplete: (UserSettings) -> Unit) {
    db.collection("users").document(userId).get()
        .addOnSuccessListener { document ->
            val userSettings = document.toObject(UserSettings::class.java)
            if (userSettings != null) {
                onComplete(userSettings)
            }
        }
        .addOnFailureListener { exception ->
            // Handle error
        }
}

// Write user settings to Firestore
fun writeUserSettings(userId: String, userSettings: UserSettings) {
    db.collection("users").document(userId).set(userSettings)
        .addOnSuccessListener {
            // Settings saved successfully
        }
        .addOnFailureListener { exception ->
            // Handle error
        }
}