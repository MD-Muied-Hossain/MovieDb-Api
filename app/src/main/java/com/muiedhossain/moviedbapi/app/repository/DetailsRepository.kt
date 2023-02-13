package com.muiedhossain.moviedbapi.app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.MovieDetailsModel

class DetailsRepository (
    private var dao: MovieDao,
    private var api: ApiInterface
    ) {

    private var detailsMutableData = MutableLiveData<MovieDetailsModel>()
    private var bookmarkedMutableData = MutableLiveData<Boolean>()

    val detailsLiveData : LiveData<MovieDetailsModel>
        get() = detailsMutableData

    val bookmarkedLiveData : LiveData<Boolean>
        get() = bookmarkedMutableData

    suspend fun getMovieDetails(){
        try {
            val movieId = ConstraintUtils.movieDetails.selectedMovieID
            Log.d("movieDetails", "getMovieDetails ID: "+movieId)
            var result = api.getMovieDetails(movieId)
            Log.d("movieDetails", "getMovieDetails: "+result.body())
            if(result!=null){
                detailsMutableData.postValue(result.body())
            }
        }catch (e:Exception){
            Log.d("movieDetails", "asena: ")
        }
    }

    suspend fun insertBookMarks(bookmarkModel: BookmarkModel){
        Log.d("bookmark2", "repo1: bookmarkModel")
        try {
            dao.insertBookMarks(bookmarkModel)
            Log.d("bookmark2", "repo2: "+bookmarkModel)
        }catch (e:Exception){
            Log.d("error", "insertBookMarks: "+ e.localizedMessage)
        }

    }

    fun getMovieById(bookmarkId : Long){
        try {
            var result = dao.getMovieById(bookmarkId)
            bookmarkedMutableData.postValue(result.value)

        }catch (e:Exception){}

    }
}