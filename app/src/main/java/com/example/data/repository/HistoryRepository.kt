package com.example.data.repository

import com.example.data.api.ChatHistoryMessage
import com.example.data.api.GeminiHistoryService
import com.example.data.local.BookmarkEntity
import com.example.data.local.HistoryDao
import com.example.data.local.SearchHistoryEntity
import com.example.data.model.HistoricalEra
import com.example.data.model.HistoryTopic
import kotlinx.coroutines.flow.Flow

class HistoryRepository(private val historyDao: HistoryDao) {

    val allBookmarks: Flow<List<BookmarkEntity>> = historyDao.getAllBookmarks()
    val recentSearches: Flow<List<SearchHistoryEntity>> = historyDao.getRecentSearches()

    fun getAllTopics(): List<HistoryTopic> = PhilippineHistoryData.topics

    fun getTopicById(id: String): HistoryTopic? = PhilippineHistoryData.getTopicById(id)

    fun searchTopics(query: String): List<HistoryTopic> = PhilippineHistoryData.searchTopics(query)

    fun getTopicsByEra(era: HistoricalEra): List<HistoryTopic> = PhilippineHistoryData.getTopicsByEra(era)

    fun getRecommendedTopics(currentTopicId: String? = null): List<HistoryTopic> =
        PhilippineHistoryData.getRecommendedTopics(currentTopicId)

    fun isBookmarked(topicId: String): Flow<Boolean> = historyDao.isBookmarked(topicId)

    suspend fun toggleBookmark(topic: HistoryTopic, isCurrentlyBookmarked: Boolean) {
        if (isCurrentlyBookmarked) {
            historyDao.deleteBookmark(topic.id)
        } else {
            historyDao.insertBookmark(
                BookmarkEntity(
                    topicId = topic.id,
                    title = topic.title,
                    eraTitle = topic.era.title,
                    dateOrYear = topic.dateOrYear,
                    category = topic.category
                )
            )
        }
    }

    suspend fun saveSearchQuery(query: String) {
        if (query.isNotBlank()) {
            historyDao.insertSearch(SearchHistoryEntity(query = query.trim()))
        }
    }

    suspend fun deleteSearch(id: Int) = historyDao.deleteSearch(id)

    suspend fun clearAllSearches() = historyDao.clearAllSearches()

    suspend fun askAiAssistant(
        question: String,
        history: List<ChatHistoryMessage> = emptyList()
    ): String {
        return GeminiHistoryService.askHistoryQuestion(question, history)
    }
}
