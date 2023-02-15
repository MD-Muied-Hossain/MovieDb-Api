package com.muiedhossain.moviedbapi.app.diffUtils

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
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

@BindingAdapter("app:setGenreView")
fun setGenreView(view : LinearLayout, text: String){
    val strs = text.split(",").toTypedArray()

    view.removeAllViews()

    for(i in 0..strs.size - 2){
        val getGenresString = TextView(view.context)
        getGenresString.text = strs.get(i)
        getGenresString.setBackgroundResource(R.drawable.genre_background)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(0, 10, 15, 5)

        getGenresString.layoutParams = params

        view.setPadding(5,5,5,5)
        view.addView(getGenresString)
    }
}