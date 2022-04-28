package com.ivancoder.elektrainterviewtechnicaltest.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Movie(
    val id:Int,
    val overview:String,
    val popularity:Double,
    val posterPath:String,
    val releaseDate:String,
    val title:String,
    val voteAverage:Double,
    val voteCount:Int,
): Parcelable