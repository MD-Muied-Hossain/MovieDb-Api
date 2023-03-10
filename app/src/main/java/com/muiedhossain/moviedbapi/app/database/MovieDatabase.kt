package com.muiedhossain.moviedbapi.app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muiedhossain.moviedbapi.app.dao.MovieDao
import com.muiedhossain.moviedbapi.app.view.bookmark.model.BookmarkModel
import com.muiedhossain.moviedbapi.app.view.genres.Genre

@Database(entities = [Genre::class, BookmarkModel::class], version = 1/*,exportSchema = false*/)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getDao() : MovieDao
    companion object{
        @Volatile
        private var movieDataBase : MovieDatabase? = null

        fun getDataBaseInstance(context : Context) : MovieDatabase {
            if(movieDataBase==null){
                synchronized(this){
                    movieDataBase = Room.databaseBuilder(
                        context,
                        MovieDatabase::class.java,
                        "movie_database"
                    )
                        .allowMainThreadQueries()
                        .build()
                }

            }
            return movieDataBase!!
        }
    }
}