package com.ivancoder.elektrainterviewtechnicaltest.presentation.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ivancoder.elektrainterviewtechnicaltest.R
import com.ivancoder.elektrainterviewtechnicaltest.config.Consts
import com.ivancoder.elektrainterviewtechnicaltest.config.dateToString
import com.ivancoder.elektrainterviewtechnicaltest.config.picassoLoadImageUrl
import com.ivancoder.elektrainterviewtechnicaltest.config.toDate
import com.ivancoder.elektrainterviewtechnicaltest.databinding.MovieFragmentBinding
import com.ivancoder.elektrainterviewtechnicaltest.domain.models.Movie
import com.ivancoder.elektrainterviewtechnicaltest.presentation.network.responses.VideoMovieResponse
import com.ivancoder.elektrainterviewtechnicaltest.tools.VBFragment
import com.ivancoder.elektrainterviewtechnicaltest.presentation.ui.customs.VideoPager
import com.ivancoder.elektrainterviewtechnicaltest.presentation.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment: VBFragment<MovieFragmentBinding>(),View.OnClickListener{

    private val movieViewModel by activityViewModels<MovieViewModel>()
    private var movieItem:Movie? = null
    private var videoPager:VideoPager? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> MovieFragmentBinding
        get() = MovieFragmentBinding::inflate

    override fun setup() {
        movieItem = arguments?.get("movieItem") as Movie
        binding.btnTryAgain.setOnClickListener(this)
        setValuesViewElements()
        loadVideosMovie()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnTryAgain -> tryAgain()
        }
    }

    private fun setValuesViewElements(){
        binding.imgvPoster.picassoLoadImageUrl(binding.clpPoster, Consts.END_POINTS.POSTER_URL+movieItem?.posterPath)
        binding.txtvTitle.text = movieItem?.title
        binding.txtvPopularity.text = movieItem?.popularity.toString()
        binding.txtvReleaseDate.text = movieItem?.releaseDate?.toDate()?.dateToString()
        binding.txtvVoteAverage.text = movieItem?.voteAverage.toString()
        binding.txtvVoteCount.text = movieItem?.voteCount.toString()
        binding.txtvOverview.text = movieItem?.overview
    }

    private fun loadVideosMovie(){
        movieViewModel.loadVideos(movieItem?.id!!){
            binding.clpVideos.visibility = View.GONE
            val videos = it?.filter{ video -> video.site == "YouTube" }?.take(5)
            if(videos?.isNotEmpty()!!){
                showViewsVideosSuccess(videos)
            }else{
                showFeedBackVideosEmpty(videos.isEmpty())
            }
        }
    }

    private fun showViewsVideosSuccess(videos:List<VideoMovieResponse>){
        videoPager = VideoPager(this,videos)
        binding.vprVideosMovie.adapter = videoPager
        binding.wdiIndicator.setViewPager(binding.vprVideosMovie)
        binding.wdiIndicator.visibility = if(videos.size > 1) View.VISIBLE else View.GONE
    }

    private fun showFeedBackVideosEmpty(empty:Boolean){
        binding.vprVideosMovie.visibility = if(empty) View.GONE else View.VISIBLE
        binding.txtvEmptyData.visibility = if(!empty) View.GONE else View.VISIBLE
        binding.btnTryAgain.visibility = if(!empty) View.GONE else View.VISIBLE
    }

    private fun tryAgain(){
        binding.clpVideos.visibility = View.VISIBLE
        binding.wdiIndicator.visibility = View.GONE
        showFeedBackVideosEmpty(false)
        loadVideosMovie()
    }
}