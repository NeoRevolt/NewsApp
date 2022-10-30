package com.dzalfikri.newsapp.data.offline.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
class NewsEntity(

    @field:ColumnInfo(name = "title")
    @field:PrimaryKey
    val title: String,

    @field:ColumnInfo(name = "publishedAt")
    val publishedAt: String,

    @field:ColumnInfo(name = "urlToImage")
    val urlToImage: String? = null,

    @field:ColumnInfo(name = "url")
    val url: String? = null,

    @field:ColumnInfo(name = "author")
    val author: String? = null,

    @field:ColumnInfo(name = "source")
    val source: String? = null,

    @field:ColumnInfo(name = "desc")
    val desc: String? = null,

    @field:ColumnInfo(name = "content")
    val content: String? = null,

    @field:ColumnInfo(name = "bookmarked")
    var isBookmarked: Boolean
)