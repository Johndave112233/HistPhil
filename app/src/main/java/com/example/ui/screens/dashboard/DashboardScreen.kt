package com.example.ui.screens.dashboard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HistoricalEra
import com.example.data.model.HistoryTopic
import com.example.data.repository.HistoryRepository
import com.example.ui.components.GeometricHeader
import com.example.ui.components.HistoryTopicCard
import com.example.ui.components.SearchBarWithGlow
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
fun DashboardScreen(
    repository: HistoryRepository,
    initialQuery: String = "",
    onNavigateToDetail: (String) -> Unit,
    onNavigateToAssistantWithPrompt: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf(initialQuery) }
    var selectedEra by remember { mutableStateOf<HistoricalEra?>(null) }
    var selectedCategory by remember { mutableStateOf("All") }
    val coroutineScope = rememberCoroutineScope()

    val categories = listOf("All", "Hero", "Revolution", "Battle", "Document", "Ancient Civilization", "Movement", "Treaty")

    LaunchedEffect(initialQuery) {
        if (initialQuery.isNotEmpty()) {
            searchQuery = initialQuery
            repository.saveSearchQuery(initialQuery)
        }
    }

    val filteredTopics = remember(searchQuery, selectedEra, selectedCategory) {
        val baseList = if (searchQuery.isNotBlank()) {
            repository.searchTopics(searchQuery)
        } else if (selectedEra != null) {
            repository.getTopicsByEra(selectedEra!!)
        } else {
            repository.getAllTopics()
        }

        if (selectedCategory == "All") {
            baseList
        } else {
            baseList.filter { it.category.equals(selectedCategory, ignoreCase = true) }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Slate50)
    ) {
        GeometricHeader(
            title = "History Explorer",
            subtitle = "Dashboard & Archive",
            onActionClick = {
                onNavigateToAssistantWithPrompt("Can you summarize the major turning points in Philippine history?")
            },
            actionIcon = {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "Ask AI",
                    tint = PhYellow,
                    modifier = Modifier.size(20.dp)
                )
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("dashboard_scroll_list"),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Search Bar
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    SearchBarWithGlow(
                        query = searchQuery,
                        onQueryChange = {
                            searchQuery = it
                        },
                        onSearch = { query ->
                            if (query.isNotBlank()) {
                                coroutineScope.launch {
                                    repository.saveSearchQuery(query)
                                }
                            }
                        },
                        placeholder = "Search events, people, places, dates...",
                        onClear = {
                            searchQuery = ""
                        }
                    )
                }
            }

            // Historical Eras Timeline Horizontal Scroll
            item {
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timeline,
                                contentDescription = null,
                                tint = PhBlue,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "HISTORICAL ERAS",
                                color = Slate500,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp
                            )
                        }

                        if (selectedEra != null) {
                            Text(
                                text = "Reset Era",
                                color = PhRed,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.clickable { selectedEra = null }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(HistoricalEra.values()) { era ->
                            val isSelected = selectedEra == era
                            val eraColor = Color(era.accentColor)

                            Box(
                                modifier = Modifier
                                    .testTag("era_chip_${era.name}")
                                    .width(140.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(if (isSelected) PhBlue else PureWhite)
                                    .border(
                                        1.dp,
                                        if (isSelected) PhBlue else Slate200,
                                        RoundedCornerShape(14.dp)
                                    )
                                    .clickable {
                                        selectedEra = if (isSelected) null else era
                                    }
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(24.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    if (isSelected) PureWhite.copy(alpha = 0.2f)
                                                    else eraColor.copy(alpha = 0.12f)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = era.iconCode,
                                                color = if (isSelected) PureWhite else eraColor,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }

                                        if (isSelected) {
                                            Box(
                                                modifier = Modifier
                                                    .size(6.dp)
                                                    .clip(CircleShape)
                                                    .background(PhYellow)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = era.title,
                                        color = if (isSelected) PureWhite else Slate900,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        maxLines = 1
                                    )
                                    Text(
                                        text = era.periodSpan,
                                        color = if (isSelected) PureWhite.copy(alpha = 0.8f) else Slate400,
                                        fontSize = 10.sp,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Categories Filter Pills
            item {
                Column(modifier = Modifier.padding(vertical = 12.dp)) {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(categories) { cat ->
                            val isSelected = selectedCategory == cat
                            Box(
                                modifier = Modifier
                                    .testTag("category_chip_$cat")
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(if (isSelected) Slate900 else PureWhite)
                                    .border(
                                        1.dp,
                                        if (isSelected) Slate900 else Slate200,
                                        RoundedCornerShape(20.dp)
                                    )
                                    .clickable { selectedCategory = cat }
                                    .padding(horizontal = 14.dp, vertical = 7.dp)
                            ) {
                                Text(
                                    text = cat,
                                    color = if (isSelected) PureWhite else Slate700,
                                    fontSize = 12.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // Search / Filter Results Header & Counter
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (searchQuery.isNotBlank()) "RESULTS FOR \"$searchQuery\""
                        else if (selectedEra != null) selectedEra!!.title.uppercase()
                        else "POPULAR HISTORICAL TOPICS",
                        color = Slate400,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )

                    Text(
                        text = "${filteredTopics.size} entries",
                        color = PhBlue,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Empty state when search yields no direct keyword match
            if (filteredTopics.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(PhBlueContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = PhBlue,
                                modifier = Modifier.size(28.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "No direct match in local archives",
                            color = Slate800,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = "Ask Kasaysayan AI to generate a verified, fact-checked historical dossier about \"$searchQuery\"",
                            color = Slate500,
                            fontSize = 12.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 20.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(12.dp))
                                .background(PhBlue)
                                .clickable {
                                    onNavigateToAssistantWithPrompt("Tell me accurate and detailed facts about: $searchQuery")
                                }
                                .padding(horizontal = 18.dp, vertical = 10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    tint = PhYellow,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Ask Kasaysayan AI",
                                    color = PureWhite,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            } else {
                items(filteredTopics) { topic ->
                    Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp)) {
                        HistoryTopicCard(
                            topic = topic,
                            onClick = { onNavigateToDetail(topic.id) }
                        )
                    }
                }
            }
        }
    }
}
