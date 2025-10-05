package com.example.movielist.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movielist.data.local.dao.MovieDao
import com.example.movielist.data.local.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}