package com.muiedhossain.moviedbapi.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.api.ApiInterface
import com.muiedhossain.moviedbapi.app.api.RetrofitInstance
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.Genre
import com.muiedhossain.moviedbapi.app.repository.BookmarkRepository
import kotlinx.coroutines.launch

class BookmarkViewModel (application: Application)
    : AndroidViewModel(application) {
    private lateinit var repository: BookmarkRepository
    private var api: ApiInterface = RetrofitInstance.getRetrofitInstance()
        .create(ApiInterface::class.java)
    private lateinit var dao: MovieDao
    init {

        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        repository = BookmarkRepository(api,dao)
    }

    fun getBookmarkMovie() : LiveData<List<BookmarkModel>> {
        return repository.getBookmarkedMovie()
    }
    fun deleteBookmark(id: Long){
        viewModelScope.launch {
            try {
                repository.deleteBookmarked(id)
            }catch (e:Exception){}
        }
    }
    fun getGenre() {
        viewModelScope.launch {
            repository.getGenre()
        }
    }
    fun getGenreDataByID(id : Int) : LiveData<List<Genre>>{
        try {
            viewModelScope.launch {
                repository.getGenreDataByID(id)
            }
        }catch (e:Exception){}
        return repository.genreLiveData
    }

}