package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val topicId: String,
    val title: String,
    val eraTitle: String,
    val dateOrYear: String,
    val category: String,
    val bookmarkedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "search_history")
data class SearchHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val query: String,
    val timestamp: Long = System.currentTimeMillis()
)
