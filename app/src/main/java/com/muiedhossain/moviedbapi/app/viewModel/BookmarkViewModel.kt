package com.muiedhossain.moviedbapi.app.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.database.MovieDatabase
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.repository.BookmarkRepository
import kotlinx.coroutines.launch

class BookmarkViewModel (application: Application)
    : AndroidViewModel(application) {
    private lateinit var dao: MovieDao
    private lateinit var repository: BookmarkRepository
    init {
        dao = MovieDatabase.getDataBaseInstance(application).getDao()
        repository = BookmarkRepository(dao)
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

}