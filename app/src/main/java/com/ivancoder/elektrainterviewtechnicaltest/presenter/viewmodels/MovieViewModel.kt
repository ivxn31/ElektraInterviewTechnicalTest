package com.ivancoder.elektrainterviewtechnicaltest.presenter.viewmodels

import androidx.lifecycle.viewModelScope
import com.ivancoder.elektrainterviewtechnicaltest.domain.enums.Status
import com.ivancoder.elektrainterviewtechnicaltest.data.repositories.MovieRepository
import com.ivancoder.elektrainterviewtechnicaltest.presenter.network.responses.VideoMovieResponse
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