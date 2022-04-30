package com.example.edzienniczek.subject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope


import com.example.edzienniczek.TeacherDataDAO
import com.example.edzienniczek.TeacherDataDatabase
import com.example.edzienniczek.enitities.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectListViewModel(application: Application): AndroidViewModel(application) {

    private val teacherDataDao: TeacherDataDAO = TeacherDataDatabase.getInstance(application).teacherDataDao()
    val subjects:LiveData<List<Subject>> = teacherDataDao.getAll()

    private val teacherDataDaoAdd: TeacherDataDAO
    init {
        teacherDataDaoAdd = TeacherDataDatabase.getInstance(application).teacherDataDao()
    }

    fun addSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDataDaoAdd.insertSubject(subject) }
    }

    fun deleteSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDataDao.deleteSubject(subject) }
    }

}