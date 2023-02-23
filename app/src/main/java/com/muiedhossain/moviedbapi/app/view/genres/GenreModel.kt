package com.muiedhossain.moviedbapi.app.view.genres

import androidx.room.Entity
import androidx.room.PrimaryKey

data class GenreModel(
    val genres: List<Genre>
)

@Entity(tableName = "genre_Table")
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String
)