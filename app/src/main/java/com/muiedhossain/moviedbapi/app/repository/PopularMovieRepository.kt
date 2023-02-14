package com.muiedhossain.moviedbapi.app.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.model.PopularMovieModel

class PopularMovieRepository(
    private val api: ApiInterface,
    private val movieDao: MovieDao,
    private val context: Context
) {
    private val popularMovieLiveData = MutableLiveData<PopularMovieModel>()
    private val popularLiveData = MutableLiveData<PopularMovieModel>()
    private val genreMutableLiveData = MutableLiveData<List<Genre>>()

    val popularMovie : LiveData<PopularMovieModel>
        get() = popularMovieLiveData

    val genreLiveData : LiveData<List<Genre>>
        get() = genreMutableLiveData

    suspend fun getPopularMovie(page : Int){
        try {
            var popularMovieResult = api.getPopularMovie(page)
            popularMovieLiveData.postValue(popularMovieResult.body())
           //Log.e("popularMovieResult", "getPopularMovie: "+popularMovieResult.body() )
        }catch (e:Exception){}
    }

    suspend fun getGenre(){
        var genreResult = api.getGenre()
        if(genreResult!=null){
            try {
                movieDao.insertGenre(genreResult.body()!!.genres)
            }catch (e :Exception){}

        }
    }
    suspend fun getGenreDataByID(id : Int) : MutableLiveData<List<Genre>>{
        try {
            genreMutableLiveData.postValue(movieDao.getGenreDataByID(id))
        }catch (e:Exception){}

        return genreMutableLiveData
    }
}