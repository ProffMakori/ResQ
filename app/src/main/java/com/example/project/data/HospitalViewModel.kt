package com.example.project.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project.models.Hospital
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HospitalViewModel : ViewModel() {

    private val db = FirebaseFirestore.getInstance()
    private val _hospitals = MutableStateFlow<List<Hospital>>(emptyList())
    val hospitals: StateFlow<List<Hospital>> get() = _hospitals

    init {
        fetchHospitals()
    }

    fun fetchHospitals() {
        db.collection("hospitals")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    // Handle error
                    return@addSnapshotListener
                }
                val hospitalsList = mutableListOf<Hospital>()
                for (doc in value!!) {
                    val hospital = doc.toObject(Hospital::class.java)
                    hospitalsList.add(hospital)
                }
                _hospitals.value = hospitalsList
            }
    }

    fun addHospital(name: String, phoneNumber: String) {
        viewModelScope.launch {
            val hospital = Hospital(name, phoneNumber)
            db.collection("hospitals").add(hospital)
        }
    }
}
