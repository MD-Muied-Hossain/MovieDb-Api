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

    @Query("DELETE FROM bookmark_Table WHERE bookmark_id = :bookmarkId")
    suspend fun deleteBookMarks(bookmarkId : Long)

    @Query("SELECT bookmark_id FROM bookmark_Table WHERE bookmark_id = :bookmarkId")
    fun getMovieById(bookmarkId : Long) : LiveData<Boolean>

    @Query("SELECT * FROM bookmark_Table")
    fun getBookmarkedMovie() : LiveData<List<BookmarkModel>>

    @Query("SELECT * FROM genre_Table WHERE id = :id")
    suspend fun getGenreDataByID(id : Int) : List<Genre>

}