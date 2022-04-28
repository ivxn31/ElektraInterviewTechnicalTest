package com.ivancoder.elektrainterviewtechnicaltest.viewmodels

import androidx.lifecycle.viewModelScope
import com.ivancoder.elektrainterviewtechnicaltest.models.Movie
import com.ivancoder.elektrainterviewtechnicaltest.repositories.MoviesRepository
import com.ivancoder.elektrainterviewtechnicaltest.responses.MovieResponse
import com.ivancoder.elektrainterviewtechnicaltest.tools.ItemClick
import com.ivancoder.elektrainterviewtechnicaltest.tools.MVVMList
import com.ivancoder.elektrainterviewtechnicaltest.tools.MVVMRecycler
import com.ivancoder.elektrainterviewtechnicaltest.tools.ToolViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject
import com.ivancoder.elektrainterviewtechnicaltest.R
import com.ivancoder.elektrainterviewtechnicaltest.BR
import com.ivancoder.elektrainterviewtechnicaltest.config.dateToString
import com.ivancoder.elektrainterviewtechnicaltest.config.toDate
import com.ivancoder.elektrainterviewtechnicaltest.enums.Status
import com.ivancoder.elektrainterviewtechnicaltest.enums.TypeFilterMovie
import kotlinx.coroutines.launch

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository): ToolViewModel(),
    MVVMList<Movie> {

    var adapter = MVVMRecycler(this, this)
    var typeFilterMovie = TypeFilterMovie.NOW_PLAYING
    private var listMovies: ArrayList<Movie>? = null

    override lateinit var itemClick: ItemClick<Movie>

    override fun getLayoutIdForPosition(position: Int) = R.layout.movie_item

    override fun getBindingVariable() = BR.vm

    override fun getPositionVariable() = BR.position

    override fun onItemClick(index: Int) = itemClick.onItemClick(getItem(index),index)

    override fun setupList() {
        loading.set(true)
        try{
            viewModelScope.launch{
                when(typeFilterMovie){
                    TypeFilterMovie.NOW_PLAYING -> getNowPlayingMovies()
                    TypeFilterMovie.MORE_POPULAR -> getMorePopularMovies()
                }
            }
        }catch (e:Exception){
            Timber.e(e)
        }
    }

    override fun setList(data: ArrayList<Movie>?) {
        listMovies = data
        adapter.setList(data)
    }

    override fun getItem(position: Int) = listMovies!![position]

    private suspend fun getNowPlayingMovies(){
        repository.getMoviesRemote(TypeFilterMovie.NOW_PLAYING).let {
            when(it.status){
                Status.SUCCESS -> loadMoviesToCache(it.data?.movieList,false)
                Status.ERROR -> errorRequestAPIAndGetMovieFromDB(it.message)
                Status.LOADING -> loading.set(true)
            }
        }
    }

    private suspend fun getMorePopularMovies(){
        repository.getMoviesRemote(TypeFilterMovie.MORE_POPULAR).let {
            when(it.status){
                Status.SUCCESS -> loadMoviesToCache(it.data?.movieList,true)
                Status.ERROR -> errorRequestAPIAndGetMovieFromDB(it.message)
                Status.LOADING -> loading.set(true)
            }
        }
    }

    private suspend fun loadMoviesToCache(movies:ArrayList<MovieResponse>?,isPopular:Boolean){
        val moviesResponseToEntity = movies?.map {
            it.toEntity(isPopular)
        }
        repository.insertMultiple(typeFilterMovie,moviesResponseToEntity)
        listMovies = repository.getMoviesCache(typeFilterMovie)
        loading.set(false)
        setList(listMovies)
    }

    private suspend fun errorRequestAPIAndGetMovieFromDB(message:String?){
        Timber.e("error setup $message")
        viewModelScope
        listMovies = repository.getMoviesCache(typeFilterMovie)
        loading.set(false)
        setList(listMovies)
    }

    fun clearListMovie(){
        listMovies?.clear()
        setList(listMovies)
    }

    fun formatDate(position: Int) = getItem(position).releaseDate.toDate()?.dateToString()
}