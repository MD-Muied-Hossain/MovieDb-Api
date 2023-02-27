package com.muiedhossain.moviedbapi.app.view.bookmark.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.view.bookmark.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.view.genres.Genre
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val api: ApiInterface,
    var movieDao: MovieDao) {
    fun getBookmarkedMovie() : LiveData<List<BookmarkModel>> = movieDao.getBookmarkedMovie()
    private val genreMutableLiveData = MutableLiveData<List<Genre>>()

    val genreLiveData : LiveData<List<Genre>>
        get() = genreMutableLiveData

    suspend fun deleteBookmarked(id: Long){
        try {
            movieDao.deleteBookmarked(id)
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
    suspend fun getGenreDataByID(id : Int) : MutableLiveData<List<Genre>> {
        try {
            genreMutableLiveData.postValue(movieDao.getGenreDataByID(id))
        }catch (e:Exception){}

        return genreMutableLiveData
    }
}