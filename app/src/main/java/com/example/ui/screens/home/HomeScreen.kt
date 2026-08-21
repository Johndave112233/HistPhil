package com.example.ui.screens.home

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
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.SearchHistoryEntity
import com.example.data.model.HistoryTopic
import com.example.data.repository.HistoryRepository
import com.example.ui.components.GeometricHeader
import com.example.ui.components.HistoryTopicCard
import com.example.ui.components.SearchBarWithGlow
import com.example.ui.components.WeeklyHighlightCard
import com.example.ui.theme.PhBlue
import com.example.ui.theme.PhBlueContainer
import com.example.ui.theme.PhGold
import com.example.ui.theme.PhRed
import com.example.ui.theme.PhYellow
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate100
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900

@Composable
fun HomeScreen(
    repository: HistoryRepository,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToSearch: (String) -> Unit,
    onNavigateToAssistantWithPrompt: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    val recentSearches by repository.recentSearches.collectAsState(initial = emptyList())
    val allTopics = remember { repository.getAllTopics() }
    val featuredTopic = remember {
    allTopics.find { it.isWeeklyHighlight } ?: allTopics.firstOrNull()
}
    val recommendedTopics = remember { repository.getRecommendedTopics() }

    val quickQuestions = listOf(
        "Battle of Mactan",
        "José Rizal",
        "The Katipunan (KKK)",
        "EDSA People Power",
        "Laguna Copperplate",
        "Declaration of Independence"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(com.example.ui.theme.Slate50)
    ) {
        // Geometric Top Header
        GeometricHeader(
            title = "Kasaysayan AI",
            subtitle = "Digital Museum",
            onActionClick = { onNavigateToAssistantWithPrompt("What are the most significant milestones in Philippine history?") },
            actionIcon = {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = "AI Assistant",
                    tint = PhYellow,
                    modifier = Modifier.size(20.dp)
                )
            }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("home_scroll_list"),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Hero Title & ChatGPT-style Search Bar
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Badge: Philippine History Explorer
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(PureWhite)
                            .border(1.dp, Slate200, CircleShape)
                            .padding(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(PhRed)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "PHILIPPINE HISTORY EXPLORER",
                                color = Slate600,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                letterSpacing = 1.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Title with geometric balance & gold underline
                    Text(
                        text = buildAnnotatedString {
                            append("Discover the events that\n")
                            withStyle(
                                style = SpanStyle(
                                    color = PhBlue,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            ) {
                                append("shaped our nation")
                            }
                        },
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Light,
                        lineHeight = 32.sp,
                        color = Slate900,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // Search Bar with glow
                    SearchBarWithGlow(
                        query = searchQuery,
                        onQueryChange = { searchQuery = it },
                        onSearch = { query ->
                            if (query.isNotBlank()) {
                                onNavigateToAssistantWithPrompt(query)
                            }
                        },
                        placeholder = "Ask AI anything about Philippine history..."
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Quick suggested chips
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        quickQuestions.forEach { prompt ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(PureWhite)
                                    .border(1.dp, Slate200, RoundedCornerShape(20.dp))
                                    .clickable { onNavigateToAssistantWithPrompt(prompt) }
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Text(
                                    text = prompt,
                                    color = Slate700,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }

            // AI History Assistant Feature Card
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)) {
                    Box(
                        modifier = Modifier
                            .testTag("ai_assistant_home_banner")
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(18.dp))
                            .background(PhBlue)
                            .clickable {
                                onNavigateToAssistantWithPrompt("What were the most pivotal events in Philippine history and why?")
                            }
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .clip(CircleShape)
                                    .background(PhYellow),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = "AI Assistant",
                                    tint = PhBlue,
                                    modifier = Modifier.size(24.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "ASK KASAYSAYAN AI",
                                    color = PhYellow,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.4.sp
                                )
                                Text(
                                    text = "Ask anything about Philippine History",
                                    color = PureWhite,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Real-time answers, facts, dates & hero profiles",
                                    color = PureWhite.copy(alpha = 0.8f),
                                    fontSize = 11.sp
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(PureWhite.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "→",
                                    color = PureWhite,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            // Weekly Highlight Card
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    WeeklyHighlightCard(
                        topic = featuredTopic,
                        onClick = { onNavigateToDetail(featuredTopic.id) }
                    )
                }
            }

            // Current Connected Network Preview (Geometric 2-Column Cards)
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "CURRENT NETWORK",
                            color = Slate400,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                        Text(
                            text = "Explore Connections",
                            color = PhBlue,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { onNavigateToDetail("philippine-revolution") }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Card 1: Revolutionary Period
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(PureWhite)
                                .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                                .clickable { onNavigateToDetail("philippine-revolution") }
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .background(PhGold)
                            )
                            Column(modifier = Modifier.padding(14.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(PhBlue.copy(alpha = 0.1f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "RP",
                                        color = PhBlue,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Revolutionary Period",
                                    color = Slate900,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 18.sp
                                )
                                Text(
                                    text = "1896 – 1898",
                                    color = Slate500,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        // Card 2: José Rizal
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .clip(RoundedCornerShape(16.dp))
                                .background(PureWhite)
                                .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                                .clickable { onNavigateToDetail("jose-rizal") }
                        ) {
                            Box(
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .fillMaxWidth()
                                    .height(4.dp)
                                    .background(PhRed)
                            )
                            Column(modifier = Modifier.padding(14.dp)) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(PhRed.copy(alpha = 0.1f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "JR",
                                        color = PhRed,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))
                                Text(
                                    text = "Dr. José Rizal",
                                    color = Slate900,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    lineHeight = 18.sp
                                )
                                Text(
                                    text = "National Hero",
                                    color = Slate500,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            // Recent Searches Section (if any)
            if (recentSearches.isNotEmpty()) {
                item {
                    Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.History,
                                    contentDescription = null,
                                    tint = Slate400,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "RECENT SEARCHES",
                                    color = Slate400,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.2.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(recentSearches.take(5)) { searchItem ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(PureWhite)
                                        .border(1.dp, Slate200, RoundedCornerShape(12.dp))
                                        .clickable { onNavigateToSearch(searchItem.query) }
                                        .padding(horizontal = 12.dp, vertical = 7.dp)
                                ) {
                                    Text(
                                        text = searchItem.query,
                                        color = Slate700,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Recommended Historical Topics
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "RECOMMENDED TOPICS",
                            color = Slate400,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "See All",
                            color = PhBlue,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { onNavigateToSearch("") }
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            items(recommendedTopics) { topic ->
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
