package com.muiedhossain.moviedbapi.app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.model.NowShowingMovieModel

class NowShowingRepository (private val api: ApiInterface,
private val movieDao: MovieDao) {

    private val nowShowingMovieLiveData = MutableLiveData<NowShowingMovieModel>()

    val movieResult: LiveData<NowShowingMovieModel>
        get() = nowShowingMovieLiveData

    suspend fun getNowShowingMovie(page: Int) {
        try {
            var nowShowingMovieResult = api.getNowShowingMovie(page)

            if (nowShowingMovieResult != null) {
                nowShowingMovieLiveData.postValue(nowShowingMovieResult.body())
                Log.d("nowSHow", "getNowShowingMovie: "+nowShowingMovieResult.body())
            }
        } catch (e: Exception) {
        }
    }
}