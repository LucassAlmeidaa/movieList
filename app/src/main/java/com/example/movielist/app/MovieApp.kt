package com.example.movielist.app

import com.example.movielist.data.local.database.AppDatabase
import android.app.Application
import androidx.room.Room

class MovieApp : Application() {
    companion object {
        lateinit var database: AppDatabase
            private set
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "movies_db"
        ).build()
    }
}
