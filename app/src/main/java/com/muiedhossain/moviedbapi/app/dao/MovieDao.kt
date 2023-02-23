package com.muiedhossain.moviedbapi.app.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.muiedhossain.moviedbapi.app.view.bookmark.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.view.genres.Genre

@Dao
interface MovieDao {

    @Insert
    suspend fun insertGenre(genre: List<Genre>)
    @Insert
    suspend fun insertBookmark(bookmarkModel: BookmarkModel)

    @Query("DELETE FROM bookmark_Table WHERE bookmark_id = :bookmarkId")
    suspend fun deleteBookmarked(bookmarkId : Long)

    @Query("SELECT * FROM bookmark_Table WHERE bookmark_id = :bookmarkId")
    suspend fun getMovieById(bookmarkId : Long) : BookmarkModel

    @Query("SELECT * FROM bookmark_Table")
    fun getBookmarkedMovie() : LiveData<List<BookmarkModel>>

    @Query("SELECT * FROM genre_Table WHERE id = :id")
    suspend fun getGenreDataByID(id : Int) : List<Genre>

    @Query("SELECT * FROM genre_Table")
    suspend fun getAllGenres() : List<Genre>

}