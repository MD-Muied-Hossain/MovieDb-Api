package com.muiedhossain.moviedbapi.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.repository.BookmarkRepository

class BookmarkViewModel (application: Application)
    : AndroidViewModel(application) {
    private lateinit var dao: MovieDao
    private lateinit var repository: BookmarkRepository
    init {
        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        repository = BookmarkRepository(dao)
    }

    fun getBookMarkMovie() : LiveData<List<BookmarkModel>> {
        return repository.getBookmarkedMovie()
    }

}