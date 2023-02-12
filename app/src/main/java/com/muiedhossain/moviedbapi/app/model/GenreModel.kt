package com.muiedhossain.moviedbapi.app.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class GenreModel(
    val genres: List<Genre>
)

@Entity(tableName = "genre_Table")
data class Genre(
    @PrimaryKey
    val id: Int,
    val name: String
)