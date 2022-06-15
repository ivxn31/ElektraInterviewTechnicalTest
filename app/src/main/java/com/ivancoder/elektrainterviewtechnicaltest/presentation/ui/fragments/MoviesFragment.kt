package com.ivancoder.elektrainterviewtechnicaltest.presentation.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ivancoder.elektrainterviewtechnicaltest.databinding.MoviesFragmentBinding
import com.ivancoder.elektrainterviewtechnicaltest.tools.MVVMFragment
import com.ivancoder.elektrainterviewtechnicaltest.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import com.ivancoder.elektrainterviewtechnicaltest.R
import com.ivancoder.elektrainterviewtechnicaltest.BR
import com.ivancoder.elektrainterviewtechnicaltest.domain.enums.TypeFilterMovie
import com.ivancoder.elektrainterviewtechnicaltest.domain.models.Movie
import com.ivancoder.elektrainterviewtechnicaltest.tools.ItemClick

@AndroidEntryPoint
class MoviesFragment: MVVMFragment<MoviesFragmentBinding, MoviesViewModel>(), ItemClick<Movie> {

    private val moviesViewModel by activityViewModels<MoviesViewModel>()

    override fun getLayoutId() = R.layout.movies_fragment

    override fun getBindingVariable() = BR.vm

    override fun getViewModel() = moviesViewModel

    override fun loadedView() {
        setHasOptionsMenu(true)
        moviesViewModel.itemClick = this
        loadMovies(moviesViewModel.typeFilterMovie)
    }

    override fun onItemClick(model: Movie, position: Int) {
        Timber.i("model $model, position $position")
        findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieFragment(model))
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.filters, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.itemNowPlaying -> {
                Timber.i("peliculas recientes")
                loadMovies(TypeFilterMovie.NOW_PLAYING)
                true
            }

            R.id.itemMorePopular -> {
                Timber.i("peliculas populares")
                loadMovies(TypeFilterMovie.MORE_POPULAR)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadMovies(typeFilterMovie: TypeFilterMovie){
        moviesViewModel.clearListMovie()
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.title = titleMovies(typeFilterMovie)
        moviesViewModel.typeFilterMovie = typeFilterMovie
        moviesViewModel.setupList()
    }

    private fun titleMovies(typeFilterMovie: TypeFilterMovie):String = when(typeFilterMovie){
        TypeFilterMovie.NOW_PLAYING -> resources.getString(R.string.textNowPlaying)
        TypeFilterMovie.MORE_POPULAR -> resources.getString(R.string.textMorePopular)
    }
}