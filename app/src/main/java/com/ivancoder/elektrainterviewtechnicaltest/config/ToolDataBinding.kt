package com.ivancoder.elektrainterviewtechnicaltest.config

import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import com.ivancoder.elektrainterviewtechnicaltest.R
import com.squareup.picasso.Picasso

object ToolDataBinding {

    @BindingAdapter("android:posterPath")
    @JvmStatic
    fun setPosterPath(view: ImageView, posterPath: String) {
        if(posterPath.trim().isEmpty()){
            view.setImageResource(R.drawable.placeholder_img)
        }else{
            Picasso.get().load(Consts.END_POINTS.POSTER_URL+posterPath)
                .fit()
                .centerInside()
                .placeholder(R.drawable.placeholder_img)
                .error(R.drawable.placeholder_img)
                .into(view)
        }
    }
}