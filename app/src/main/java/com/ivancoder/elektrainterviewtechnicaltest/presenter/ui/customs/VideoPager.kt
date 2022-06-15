package com.ivancoder.elektrainterviewtechnicaltest.presenter.ui.customs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ivancoder.elektrainterviewtechnicaltest.presenter.network.responses.VideoMovieResponse
import com.ivancoder.elektrainterviewtechnicaltest.R
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class VideoPager(private var fgm: Fragment, private var itemList: List<VideoMovieResponse>) :PagerAdapter() {

    private var layoutInflater: LayoutInflater = this.fgm.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount() = itemList.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.video_slide_item, container, false)
        val ypwVideo = itemView.findViewById<YouTubePlayerView>(R.id.ypwVideo)
        val clpVideo = itemView.findViewById<ContentLoadingProgressBar>(R.id.clpVideo)
        fgm.lifecycle.addObserver(ypwVideo)
        (container as ViewPager).addView(itemView)
        ypwVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener(){
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val videoId = itemList[position].key
                youTubePlayer.loadVideo(videoId,0F)
                youTubePlayer.pause()
                clpVideo.visibility = View.GONE
            }
        })
        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}