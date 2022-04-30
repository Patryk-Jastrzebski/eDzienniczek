package com.example.edzienniczek

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.edzienniczek.enitities.Grade
import com.example.edzienniczek.enitities.Student
import com.example.edzienniczek.enitities.Subject

@Dao
interface TeacherDataDAO {

    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM students_table")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT DISTINCT * FROM students_table WHERE subject_name=:id ")
    fun getStudentsWithSubject(id: String): LiveData<List<Student>>

    @Query("SELECT * FROM grades_table WHERE student_name=:id AND subject_name=:subject")
    fun showStudentGrade(id: String, subject: String): LiveData<List<Grade>>

    @Query("SELECT AVG(student_grade) FROM grades_table WHERE student_name=:id AND subject_name=:subject")
    fun showAvarage(id: String, subject: String): Double

    @Insert
    fun insertGrade(grade: Grade)

    @Insert
    fun insertSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)

    @Query("SELECT * FROM subjects_table")
    fun getAll(): LiveData<List<Subject>>

    @Query("SELECT * FROM subjects_table WHERE subject_id=:id")
    fun loadSingle(id: String): LiveData<List<Subject>>

}