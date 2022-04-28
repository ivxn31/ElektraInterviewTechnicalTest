package com.ivancoder.elektrainterviewtechnicaltest.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ivancoder.elektrainterviewtechnicaltest.models.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    val overview:String,
    val popularity:Double,
    val posterPath:String,
    val releaseDate:String,
    val title:String,
    val voteAverage:Double,
    val voteCount:Int,
    val isPopular:Boolean,
){
    fun toModel() = Movie(id.toInt(),overview,popularity,posterPath, releaseDate,title,voteAverage,voteCount)
}
