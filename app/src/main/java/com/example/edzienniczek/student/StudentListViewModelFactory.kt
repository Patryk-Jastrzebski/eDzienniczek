package com.example.edzienniczek.student


import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.edzienniczek.student.viewmodel.StudentListViewModel

class StudentListViewModelFactory (
    private val application: Application
):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentListViewModel::class.java)) {
            return StudentListViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}