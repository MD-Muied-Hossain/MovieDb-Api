package com.muiedhossain.moviedbapi.app.diffUtils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.muiedhossain.moviedbapi.R


@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url : String){
    if(url != null){
        var loadUri = "https://image.tmdb.org/t/p/w500/$url"
        Glide.with(this.context).load(loadUri).into(this)
    }else{
        Glide.with(this.context).load(R.drawable.movie_placeholder).into(this)
    }
}