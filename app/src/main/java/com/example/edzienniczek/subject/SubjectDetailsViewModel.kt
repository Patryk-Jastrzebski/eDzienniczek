package com.example.edzienniczek.subject

import android.app.Application
import androidx.lifecycle.*
import com.example.edzienniczek.TeacherDataDAO
import com.example.edzienniczek.TeacherDataDatabase
import com.example.edzienniczek.enitities.Student

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SubjectDetailsViewModel(application: Application, private val detailNameOfSubject: String): AndroidViewModel(application) {

    private val studentDaoDel: TeacherDataDAO = TeacherDataDatabase.getInstance(application).teacherDataDao()
    val students: LiveData<List<Student>> = studentDaoDel.getStudentsWithSubject(getSubject(detailNameOfSubject))


    fun deleteStudent(student: Student) {
        viewModelScope.launch(Dispatchers.IO ) {
            studentDaoDel.deleteStudent(student) }
    }

    fun getSubject(a: String): String {
        println("Arg: " + a)
        return a
    }

}

class SubjectDetailsViewModelFactory(
    private val application: Application,
    private val detailNameOfSubject: String
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SubjectDetailsViewModel::class.java)) {
            @Suppress ("UNCHECKED_CAST")
            return SubjectDetailsViewModel(application, detailNameOfSubject) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}