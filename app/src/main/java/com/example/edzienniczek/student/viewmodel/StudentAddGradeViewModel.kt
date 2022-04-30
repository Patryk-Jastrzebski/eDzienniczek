package com.example.edzienniczek.student

import android.app.Application
import androidx.lifecycle.*
import com.example.edzienniczek.TeacherDataDAO
import com.example.edzienniczek.TeacherDataDatabase
import com.example.edzienniczek.enitities.Grade
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.enitities.Subject
import com.example.edzienniczek.subject.SubjectDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StudentAddGradeViewModel(application: Application, private val detailNameOfStudent: String, private val detailSubjectName: String): AndroidViewModel(application) {

    private val teacherDataDao: TeacherDataDAO = TeacherDataDatabase.getInstance(application).teacherDataDao()
    val studentGrades:LiveData<List<Grade>> = teacherDataDao.showStudentGrade(detailNameOfStudent, detailSubjectName)

    fun addGrade(subject: String, student: String, grade: Float) {
        val grade = Grade(studentName = student, subjectName = subject, grade = grade)
        insertGrade(grade)
    }

    private fun insertGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            teacherDataDao.insertGrade(grade)
        }
    }
}

class StudentAddGradeViewModelFactory(
    private val application: Application,
    private val detailNameOfStudent: String,
    private val detailSubjectName: String
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentAddGradeViewModel::class.java)) {
            @Suppress ("UNCHECKED_CAST")
            return StudentAddGradeViewModel(application, detailNameOfStudent, detailSubjectName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}