package com.muiedhossain.moviedbapi.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_Table")
data class BookmarkModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "bookmark_id")
    val bookmarkId: Long,
    val name: String,
    val runTime: String
)

