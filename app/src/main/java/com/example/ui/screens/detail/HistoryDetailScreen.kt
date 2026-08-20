package com.example.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.HistoryRepository
import com.example.ui.components.ConnectedHistoryNetwork
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HistoryDetailScreen(
    topicId: String,
    repository: HistoryRepository,
    onBack: () -> Unit,
    onNavigateToDetail: (String) -> Unit,
    onNavigateToAssistantWithPrompt: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val topic = remember(topicId) { repository.getTopicById(topicId) }
    val isBookmarked by repository.isBookmarked(topicId).collectAsState(initial = false)
    val coroutineScope = rememberCoroutineScope()

    if (topic == null) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Slate50),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Historical topic not found", color = Slate700, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(12.dp))
                IconButton(onClick = onBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PhBlue)
                }
            }
        }
        return
    }

    val eraColor = Color(topic.badgeColorHex)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Slate50)
    ) {
        // Detail Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PhBlue)
                .statusBarsPadding()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .testTag("detail_back_button")
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(PureWhite.copy(alpha = 0.15f))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = PureWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = topic.era.title.uppercase(),
                        color = PhYellow,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.2.sp
                    )
                    Text(
                        text = topic.title,
                        color = PureWhite,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )
                }

                // Bookmark toggle button
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            repository.toggleBookmark(topic, isBookmarked)
                        }
                    },
                    modifier = Modifier
                        .testTag("detail_bookmark_button")
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(PureWhite.copy(alpha = 0.15f))
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) PhYellow else PureWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("history_detail_scroll"),
            contentPadding = PaddingValues(bottom = 90.dp)
        ) {
            // Main Hero Info Card with Geometric Balance
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(20.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .height(4.dp)
                                .background(eraColor)
                        )

                        Column(modifier = Modifier.padding(20.dp)) {
                            // Fact-checked and category badges
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Verified,
                                        contentDescription = "Fact Checked",
                                        tint = PhBlue,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Fact-Checked Archive",
                                        color = PhBlue,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(eraColor.copy(alpha = 0.1f))
                                        .padding(horizontal = 10.dp, vertical = 4.dp)
                                ) {
                                    Text(
                                        text = topic.category.uppercase(),
                                        color = eraColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(14.dp))

                            Text(
                                text = topic.title,
                                color = Slate900,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 28.sp
                            )

                            Spacer(modifier = Modifier.height(6.dp))

                            Text(
                                text = topic.subtitle,
                                color = Slate600,
                                fontSize = 13.sp,
                                lineHeight = 19.sp
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            // Key Metrics: Date & Location Chips
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Slate100)
                                        .padding(10.dp)
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.CalendarToday,
                                                contentDescription = null,
                                                tint = Slate500,
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "DATE / PERIOD",
                                                color = Slate400,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = topic.dateOrYear,
                                            color = Slate900,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    }
                                }

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Slate100)
                                        .padding(10.dp)
                                ) {
                                    Column {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Icon(
                                                imageVector = Icons.Default.LocationOn,
                                                contentDescription = null,
                                                tint = Slate500,
                                                modifier = Modifier.size(12.dp)
                                            )
                                            Spacer(modifier = Modifier.width(4.dp))
                                            Text(
                                                text = "LOCATION",
                                                color = Slate400,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Text(
                                            text = topic.location,
                                            color = Slate900,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            maxLines = 1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Key People Involved
            item {
                Column(modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Groups,
                            contentDescription = null,
                            tint = PhBlue,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "KEY PEOPLE INVOLVED",
                            color = Slate500,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.2.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        topic.keyPeople.forEach { person ->
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(PureWhite)
                                    .border(1.dp, Slate200, RoundedCornerShape(20.dp))
                                    .padding(horizontal = 12.dp, vertical = 6.dp)
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Box(
                                        modifier = Modifier
                                            .size(8.dp)
                                            .clip(CircleShape)
                                            .background(eraColor)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = person,
                                        color = Slate900,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Interactive Connected History Network
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 14.dp)
                ) {
                    ConnectedHistoryNetwork(
                        links = topic.connectedLinks,
                        onLinkClick = { targetId ->
                            onNavigateToDetail(targetId)
                        }
                    )
                }
            }

            // Historical Background Section
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                            .padding(18.dp)
                    ) {
                        Column {
                            Text(
                                text = "HISTORICAL BACKGROUND",
                                color = Slate400,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.3.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = topic.historicalBackground,
                                color = Slate700,
                                fontSize = 13.sp,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }
            }

            // Key Events & Timeline Details
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                            .padding(18.dp)
                    ) {
                        Column {
                            Text(
                                text = "CHRONICLE & KEY EVENTS",
                                color = Slate400,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.3.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = topic.keyEventsAndDetails,
                                color = Slate700,
                                fontSize = 13.sp,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }
            }

            // Importance & Lasting Impact
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PhBlueContainer.copy(alpha = 0.5f))
                            .border(1.dp, PhBlue.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                            .padding(18.dp)
                    ) {
                        Column {
                            Text(
                                text = "IMPORTANCE & LASTING IMPACT",
                                color = PhBlue,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.3.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = topic.importanceAndImpact,
                                color = Slate900,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 21.sp
                            )
                        }
                    }
                }
            }

            // Primary Sources & Verified Historical Facts
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PureWhite)
                            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                            .padding(18.dp)
                    ) {
                        Column {
                            Text(
                                text = "PRIMARY SOURCES & VERIFIED FACTS",
                                color = Slate400,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.3.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            topic.primarySourcesAndFacts.forEach { fact ->
                                Row(
                                    modifier = Modifier.padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = PhBlue,
                                        modifier = Modifier
                                            .size(16.dp)
                                            .padding(top = 2.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = fact,
                                        color = Slate700,
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Ask AI Assistant about this event
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .testTag("ask_ai_about_topic_button")
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(PhBlue)
                            .clickable {
                                onNavigateToAssistantWithPrompt(
                                    "Explain the full historical significance and verified timeline of ${topic.title} in Philippine history."
                                )
                            }
                            .padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(PhYellow),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = PhBlue,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(12.dp))
                                Column {
                                    Text(
                                        text = "Ask AI Assistant",
                                        color = PureWhite,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "Deep dive into ${topic.title}",
                                        color = PureWhite.copy(alpha = 0.75f),
                                        fontSize = 11.sp
                                    )
                                }
                            }

                            Text(
                                text = "→",
                                color = PhYellow,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}
