package com.muiedhossain.moviedbapi.app.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.diffUtils.ConstraintUtils
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.MovieDetailsModel

class DetailsRepository (
    private var dao: MovieDao,
    private var api: ApiInterface) {

    private var detailsMutableData = MutableLiveData<MovieDetailsModel>()
    private var bookmarkedMutableData = MutableLiveData<Boolean>()

    val detailsLiveData : LiveData<MovieDetailsModel>
        get() = detailsMutableData

    val bookmarkedLiveData : LiveData<Boolean>
        get() = bookmarkedMutableData

    suspend fun getMovieDetails(){
        try {
            val id = ConstraintUtils.movieDetails.selectedMovieID
            var result = api.getMovieDetails(id)
            if(result!=null){
                detailsMutableData.postValue(result.body())
            }
        }catch (e:Exception){}
    }

    suspend fun insertBookMarks(bookmarkModel: BookmarkModel){
        try {
            dao.insertBookMarks(bookmarkModel)
        }catch (e:Exception){}

    }

    fun getMovieById(bookmarkId : Long){
        try {
            var result = dao.getMovieById(bookmarkId)
            bookmarkedMutableData.postValue(result.value)
            Log.e("favorite", "checkFavorite: favorite00009"+result.value.toString() )

        }catch (e:Exception){}

    }
}