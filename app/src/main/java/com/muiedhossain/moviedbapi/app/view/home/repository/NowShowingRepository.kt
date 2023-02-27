package com.muiedhossain.moviedbapi.app.view.home.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.view.home.model.NowShowingMovieModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NowShowingRepository @Inject constructor (private val api: ApiInterface) {

    ///////repository te mutable declare diye viewModel e live data te set kori
    private val nowShowingMovieLiveData = MutableLiveData<NowShowingMovieModel>()

    /////but error er karone eikhane mutable k live data te set kore dei and viewModel e livedata te set kore dei
    val nowShowingMovieResult: LiveData<NowShowingMovieModel> get() = nowShowingMovieLiveData

    suspend fun getNowShowingMovie(page: Int) {
        try {
            var nowShowingMovieResponse = api.getNowShowingMovie(page)

            if (nowShowingMovieResponse != null) {
                nowShowingMovieLiveData.postValue(nowShowingMovieResponse.body())
                Log.d("nowSHow", "getNowShowingMovie: "+nowShowingMovieResponse.body())
            }
        } catch (e: Exception) {
        }
    }
}