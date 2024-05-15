package com.example.project.data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.FirstAidKit
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirstAidViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _firstaids = MutableStateFlow<List<FirstAidKit>>(emptyList())
    val firstaids: StateFlow<List<FirstAidKit>> get() = _firstaids

    init {
        fetchFirstAidKits()
    }

    fun fetchFirstAidKits() {
        db.collection("firstaids")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("FirstAidViewModel", "Error fetching first aid kits", error)
                    return@addSnapshotListener
                }
                if (value != null) {
                    val firstaidsList = value.documents.mapNotNull { doc ->
                        doc.toObject(FirstAidKit::class.java)
                    }
                    _firstaids.value = firstaidsList
                } else {
                    Log.w("FirstAidViewModel", "No data received from firstaids collection")
                }
            }
    }

    fun addFirstAid(condition: String, aidSteps: String) {
        viewModelScope.launch {
            try {
                val firstaid = FirstAidKit(condition, aidSteps)
                db.collection("firstaids").add(firstaid).addOnSuccessListener {
                    Log.d("FirstAidViewModel", "First aid kit added successfully")
                }.addOnFailureListener { e ->
                    Log.e("FirstAidViewModel", "Error adding first aid kit", e)
                }
            } catch (e: Exception) {
                Log.e("FirstAidViewModel", "Exception occurred while adding first aid kit", e)
            }
        }
    }
}
