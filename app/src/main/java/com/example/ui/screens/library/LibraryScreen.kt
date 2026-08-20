package com.example.ui.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.BookmarkEntity
import com.example.data.local.SearchHistoryEntity
import com.example.data.repository.HistoryRepository
import com.example.ui.components.GeometricHeader
import com.example.ui.theme.PhBlue
import com.example.ui.theme.PhBlueContainer
import com.example.ui.theme.PhGold
import com.example.ui.theme.PhRed
import com.example.ui.theme.PhYellow
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate50
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate800
import com.example.ui.theme.Slate900
import kotlinx.coroutines.launch

@Composable
fun LibraryScreen(
    repository: HistoryRepository,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val bookmarks by repository.allBookmarks.collectAsState(initial = emptyList())
    val searchHistory by repository.recentSearches.collectAsState(initial = emptyList())
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Slate50)
    ) {
        GeometricHeader(
            title = "Saved & History",
            subtitle = "Personal Archive"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("library_scroll_list"),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Bookmarks Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = null,
                            tint = PhBlue,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "BOOKMARKED TOPICS",
                            color = Slate500,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    }

                    Text(
                        text = "${bookmarks.size} Saved",
                        color = PhBlue,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            if (bookmarks.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.BookmarkBorder,
                                contentDescription = null,
                                tint = Slate400,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "No saved bookmarks yet",
                                color = Slate700,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Text(
                                text = "Tap the bookmark icon in any topic to save it for study.",
                                color = Slate400,
                                fontSize = 11.sp,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                }
            } else {
                items(bookmarks) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(14.dp))
                            .clickable { onNavigateToDetail(item.topicId) }
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = item.eraTitle.uppercase(),
                                    color = PhBlue,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = item.title,
                                    color = Slate900,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "${item.category} • ${item.dateOrYear}",
                                    color = Slate500,
                                    fontSize = 11.sp
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.Bookmark,
                                contentDescription = "Bookmarked",
                                tint = PhYellow,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(14.dp))
                }
            }

            // Search History Section
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.History,
                            contentDescription = null,
                            tint = Slate500,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "SEARCH HISTORY",
                            color = Slate500,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    }

                    if (searchHistory.isNotEmpty()) {
                        Text(
                            text = "Clear All",
                            color = PhRed,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                coroutineScope.launch {
                                    repository.clearAllSearches()
                                }
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }

            if (searchHistory.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                            .padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No recent searches.",
                            color = Slate400,
                            fontSize = 12.sp
                        )
                    }
                }
            } else {
                items(searchHistory) { item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 6.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(12.dp))
                            .clickable { onNavigateToSearch(item.query) }
                            .padding(horizontal = 14.dp, vertical = 10.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.query,
                                color = Slate800,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.weight(1f)
                            )

                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        repository.deleteSearch(item.id)
                                    }
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Slate400,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(70.dp))
            }
        }
    }
}
