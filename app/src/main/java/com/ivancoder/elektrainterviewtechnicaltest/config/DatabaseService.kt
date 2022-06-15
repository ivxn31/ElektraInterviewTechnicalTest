package com.ivancoder.elektrainterviewtechnicaltest.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ivancoder.elektrainterviewtechnicaltest.presenter.persistence.db.daos.MovieDao
import com.ivancoder.elektrainterviewtechnicaltest.presenter.persistence.db.entities.MovieEntity

@Database(
    entities = [
        MovieEntity::class
    ],
    version = 1,
    exportSchema = false)
abstract class DatabaseService: RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "movies.db"

        private var instance: DatabaseService? = null

        private fun create(context: Context): DatabaseService =
            Room.databaseBuilder(context, DatabaseService::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): DatabaseService =
            (instance ?: create(context)).also { instance = it }
    }

    abstract fun movieDao(): MovieDao
}