package com.muiedhossain.moviedbapi.app.view.movieDetails.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.diffUtils.RetrofitInstance
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.view.bookmark.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.view.movieDetails.model.MovieDetailsModel
import com.muiedhossain.moviedbapi.app.view.movieDetails.repository.DetailsRepository
import kotlinx.coroutines.launch

class MovieDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private lateinit var repository: DetailsRepository
    private lateinit var api: ApiInterface
    private lateinit var dao: MovieDao

    init {
        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        api = RetrofitInstance.getRetrofitInstance().create(ApiInterface::class.java)
        repository = DetailsRepository(dao, api)
    }

    fun getMovieDetails(): LiveData<MovieDetailsModel> {
        viewModelScope.launch {
            repository.getMovieDetails()
        }
        return repository.detailsLiveData
    }

    fun insertBookmark(bookmarkModel: BookmarkModel) {
        viewModelScope.launch {
            repository.insertBookmark(bookmarkModel)
            Log.d("viewModel", "insertBookMarks: "+bookmarkModel)
        }
    }
    fun deleteBookmark(id: Long){
        viewModelScope.launch {
            try {
                repository.deleteBookMarks(id)
            }catch (e:Exception){}
        }
    }

    fun getMovieById(bookmarkId: Long): LiveData<BookmarkModel> {
        viewModelScope.launch {
            try {
                repository.getMovieById(bookmarkId)
            } catch (e: Exception) {
            }
        }
        return repository.bookmarkedLiveData
    }
}