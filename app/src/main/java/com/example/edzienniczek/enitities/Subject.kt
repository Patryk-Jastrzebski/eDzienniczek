package com.example.edzienniczek.enitities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "subjects_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="subject_id")
    var subjectID: Long=0L,
    @ColumnInfo(name="subject_name")
    var SubjectName:String,
    @ColumnInfo(name= "start_time")
    var startTime: String,
    @ColumnInfo(name="end_time")
    var endTime: String
)
