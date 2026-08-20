package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.HistoryTopic
import com.example.ui.theme.PhBlue
import com.example.ui.theme.PhGold
import com.example.ui.theme.PhRed
import com.example.ui.theme.PhYellow
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate600
import com.example.ui.theme.Slate700
import com.example.ui.theme.Slate900

@Composable
fun HistoryTopicCard(
    topic: HistoryTopic,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    borderAccentColor: Color? = null
) {
    val accent = borderAccentColor ?: Color(topic.badgeColorHex)

    Box(
        modifier = modifier
            .testTag("history_topic_card_${topic.id}")
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(PureWhite)
            .border(1.dp, Slate200, RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        // Bottom border-b-4 accent bar
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(4.dp)
                .background(accent)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Code badge
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(accent.copy(alpha = 0.12f)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = topic.badgeCode,
                        color = accent,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = topic.category.uppercase(),
                        color = Slate400,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = topic.title,
                        color = Slate900,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp,
                        maxLines = 1
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = topic.shortSummary,
                color = Slate600,
                fontSize = 12.sp,
                lineHeight = 17.sp,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarToday,
                    contentDescription = null,
                    tint = Slate400,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = topic.dateOrYear,
                    color = Slate500,
                    fontSize = 11.sp,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Slate400,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = topic.location,
                    color = Slate500,
                    fontSize = 11.sp,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun WeeklyHighlightCard(
    topic: HistoryTopic,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .testTag("weekly_highlight_card")
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(PhBlue)
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        // Geometric circle rings in background
        Box(
            modifier = Modifier
                .offset(x = (-30).dp, y = 20.dp)
                .size(90.dp)
                .border(3.dp, PureWhite.copy(alpha = 0.08f), CircleShape)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = "FEATURED DISCOVERY",
                    color = PhYellow,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.5.sp
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = topic.title,
                    color = PureWhite,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "${topic.dateOrYear} • ${topic.location}",
                    color = PureWhite.copy(alpha = 0.75f),
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Gold circular arrow action button
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(PhYellow),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "→",
                    color = PhBlue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
