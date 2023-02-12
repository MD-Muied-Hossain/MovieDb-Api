package com.muiedhossain.moviedbapi.app.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.model.PopularMovieModel

class PopularMovieRepository(
    private val api: ApiInterface,
    private val movieDao: MovieDao
) {
    private val popularMovieLiveData = MutableLiveData<PopularMovieModel>()

    val popularMovie : LiveData<PopularMovieModel>
        get() = popularMovieLiveData

    suspend fun getPopularMovie(page : Int){
        try {
            var popularMovieResult = api.getPopularMovie(page)
            if(popularMovieResult!=null){
                popularMovieLiveData.postValue(popularMovieResult.body())
            }
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
}