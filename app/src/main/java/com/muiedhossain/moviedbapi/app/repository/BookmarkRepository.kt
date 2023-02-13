package com.muiedhossain.moviedbapi.app.repository

import androidx.lifecycle.LiveData
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.model.BookmarkModel

class BookmarkRepository(var dao: MovieDao) {
    fun getBookmarkedMovie() : LiveData<List<BookmarkModel>> = dao.getBookmarkedMovie()

    suspend fun deleteBookmarked(id: Long){
        try {
            dao.deleteBookmarked(id)
        }catch (e:Exception){}
    }
}