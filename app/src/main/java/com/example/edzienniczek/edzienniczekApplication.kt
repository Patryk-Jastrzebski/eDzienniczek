package com.example.edzienniczek

import android.app.Application

class edzienniczekApplication : Application() {
    val database: TeacherDataDatabase by lazy {TeacherDataDatabase.getInstance(this) }
}