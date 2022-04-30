package com.example.edzienniczek

import android.content.Context
import androidx.room.Database
import com.example.edzienniczek.enitities.Student
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.edzienniczek.enitities.Grade
import com.example.edzienniczek.enitities.Subject

@Database(entities = [Student::class, Subject::class, Grade::class],
    version = 8,
    exportSchema = false)
abstract class TeacherDataDatabase: RoomDatabase() {

    abstract fun teacherDataDao(): TeacherDataDAO

    companion object {

        @Volatile
        private var INSTANCE: TeacherDataDatabase? = null

        fun getInstance(context: Context): TeacherDataDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TeacherDataDatabase::class.java,
                        "students_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}