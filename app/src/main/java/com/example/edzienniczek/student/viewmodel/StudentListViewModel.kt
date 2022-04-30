package com.example.edzienniczek.student.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.edzienniczek.TeacherDataDAO
import com.example.edzienniczek.TeacherDataDatabase
import com.example.edzienniczek.enitities.Student
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentListViewModel(
    application: Application): AndroidViewModel(application) {

    private val teacherDataDao: TeacherDataDAO = TeacherDataDatabase.getInstance(application).teacherDataDao()
    val students: LiveData<List<Student>> = teacherDataDao.getAllStudents()

    fun deleteStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDataDao.deleteStudent(student) }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDataDao.insertStudent(student) }
    }

}