package com.example.edzienniczek.enitities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students_table")
data class Student(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="student_id")
    var studnetID: Long=0L,
    @ColumnInfo(name="student_name")
    var studentName:String,
    @ColumnInfo(name = "subject_name")
    var subjectName: String,
    @ColumnInfo(name = "student_album")
    var studentAlbum: String
)
