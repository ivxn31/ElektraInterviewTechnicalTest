package com.ivancoder.elektrainterviewtechnicaltest.config

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import com.ivancoder.elektrainterviewtechnicaltest.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

fun ImageView.picassoLoadImageUrl(clpLoading: ContentLoadingProgressBar, url:String?){
    if(url?.trim()?.isEmpty()!!){
        this.setImageResource(R.drawable.placeholder_img)
    }else{
        Picasso.get()
            .load(url)
            .fit()
            .centerInside()
            .placeholder(R.drawable.placeholder_img)
            .error(R.drawable.placeholder_img)
            .into(this, object : Callback {
                override fun onSuccess() {
                    clpLoading.visibility = View.GONE
                }
                override fun onError(e: java.lang.Exception?) {
                    clpLoading.visibility = View.GONE
                }
            })
    }
}

@SuppressLint("SimpleDateFormat")
fun String.toDate(patternFormat:String = "yyyy-MM-dd"): Date? {
    val dateFormat = SimpleDateFormat(patternFormat)
    return dateFormat.parse(this)
}

fun Date.dateToString(format: String = "dd/MM/yyyy"): String {
    val dateFormatter = SimpleDateFormat(format, Locale.getDefault())
    return dateFormatter.format(this)
}