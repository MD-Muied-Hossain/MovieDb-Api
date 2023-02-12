package com.muiedhossain.moviedbapi.app.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muiedhossain.moviedbapi.app.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.model.Genre

@Dao
interface MovieDao {
    @Insert
    suspend fun insertGenre(genre: List<Genre>)
    @Insert
    suspend fun insertBookMarks(bookmarkModel: BookmarkModel)

    @Query("SELECT bookmark_id FROM bookmark_Table WHERE bookmark_id = :bookmarkId")
    fun getMovieById(bookmarkId : Long) : LiveData<Boolean>

    @Query("SELECT * FROM bookmark_Table")
    fun getBookmarkedMovie() : LiveData<List<BookmarkModel>>
}