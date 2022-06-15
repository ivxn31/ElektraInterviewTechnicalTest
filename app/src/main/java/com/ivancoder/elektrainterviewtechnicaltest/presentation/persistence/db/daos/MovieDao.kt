package com.ivancoder.elektrainterviewtechnicaltest.presentation.persistence.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ivancoder.elektrainterviewtechnicaltest.presentation.persistence.db.entities.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiple(movie: List<MovieEntity>?)

    @Query("SELECT * FROM movies WHERE isPopular = :popular")
    suspend fun getMovies(popular:Boolean = true):List<MovieEntity>

    @Query("DELETE FROM movies WHERE isPopular = :popular")
    suspend fun deleteMovies(popular:Boolean = true)
}