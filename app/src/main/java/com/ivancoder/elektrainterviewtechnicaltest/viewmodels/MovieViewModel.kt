package com.ivancoder.elektrainterviewtechnicaltest.viewmodels

import androidx.lifecycle.viewModelScope
import com.ivancoder.elektrainterviewtechnicaltest.enums.Status
import com.ivancoder.elektrainterviewtechnicaltest.repositories.MovieRepository
import com.ivancoder.elektrainterviewtechnicaltest.responses.VideoMovieResponse
import com.ivancoder.elektrainterviewtechnicaltest.tools.ToolViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository): ToolViewModel() {

    fun loadVideos(movieID:Int,handler: (videos: List<VideoMovieResponse>?) -> Unit){
        viewModelScope.launch {
            repository.getVideosMovie(movieID).let {
                when(it.status){
                    Status.SUCCESS -> handler(it.data)
                    Status.ERROR -> handler(emptyList())
                    Status.LOADING -> loading.set(true)
                }
            }
        }
    }
}