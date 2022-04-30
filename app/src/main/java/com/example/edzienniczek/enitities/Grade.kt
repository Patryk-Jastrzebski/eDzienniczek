package com.example.edzienniczek.enitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/*
    foreignKeys = [ForeignKey(
        entity = Student::class,
        parentColumns = arrayOf("student_id"),
        childColumns = arrayOf("student_id")
    ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("subject_id"),
            childColumns = arrayOf("subject_id")
        )]
)*/

@Entity(tableName = "grades_table")
data class Grade(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "grade_id")
    val gradeId: Long = 0L,
    @ColumnInfo(name = "student_name")
    val studentName: String,
    @ColumnInfo(name = "subject_name")
    val subjectName: String,
    @ColumnInfo(name = "student_grade")
    val grade: Float
)
