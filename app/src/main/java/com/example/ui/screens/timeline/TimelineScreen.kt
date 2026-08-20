package com.example.ui.screens.timeline

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HistoricalEra
import com.example.data.repository.HistoryRepository
import com.example.ui.components.GeometricHeader
import com.example.ui.theme.PhBlue
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
import com.example.ui.theme.Slate900

@Composable
fun TimelineScreen(
    repository: HistoryRepository,
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val allTopics = remember { repository.getAllTopics() }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Slate50)
    ) {
        GeometricHeader(
            title = "Philippine Timeline",
            subtitle = "Chronological Milestones"
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("timeline_scroll_list"),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(PureWhite)
                        .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Timeline,
                                contentDescription = null,
                                tint = PhBlue,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "NATIONHOOD ROADMAP",
                                color = Slate500,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.2.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Explore the major epochs from pre-colonial inscriptions to the modern democratic era.",
                            color = Slate600,
                            fontSize = 12.sp,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            HistoricalEra.values().forEach { era ->
                val eraTopics = allTopics.filter { it.era == era }
                val eraColor = Color(era.accentColor)

                // Era Header Section
                item {
                    Column(modifier = Modifier.padding(top = 10.dp, bottom = 8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(eraColor),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = era.iconCode,
                                    color = PureWhite,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                Text(
                                    text = era.title,
                                    color = Slate900,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = era.periodSpan,
                                    color = eraColor,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Text(
                            text = era.description,
                            color = Slate500,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(start = 42.dp, top = 2.dp, bottom = 6.dp)
                        )
                    }
                }

                // Era Topics Timeline Nodes
                items(eraTopics) { topic ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 15.dp)
                    ) {
                        // Timeline vertical spine line and node dot
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.width(16.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(10.dp)
                                    .clip(CircleShape)
                                    .background(eraColor)
                            )
                            Box(
                                modifier = Modifier
                                    .width(2.dp)
                                    .height(90.dp)
                                    .background(Slate200)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Topic Card Node
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(bottom = 12.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(PureWhite)
                                .border(1.dp, Slate200, RoundedCornerShape(14.dp))
                                .clickable { onNavigateToDetail(topic.id) }
                                .padding(12.dp)
                        ) {
                            Column {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = topic.dateOrYear,
                                        color = eraColor,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = topic.category,
                                        color = Slate400,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = topic.title,
                                    color = Slate900,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = topic.shortSummary,
                                    color = Slate600,
                                    fontSize = 11.sp,
                                    maxLines = 2,
                                    lineHeight = 16.sp
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
