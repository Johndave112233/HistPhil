package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.ui.theme.PhBlue
import com.example.ui.theme.PhGold
import com.example.ui.theme.PhYellow
import com.example.ui.theme.PureWhite

@Composable
fun GeometricHeader(
    title: String = "Kasaysayan AI",
    subtitle: String = "Digital Museum",
    onActionClick: (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PhBlue)
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 14.dp)
    ) {
        // Decorative geometric circle rings overlay
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 24.dp, y = (-16).dp)
                .size(110.dp)
                .border(2.dp, PhYellow.copy(alpha = 0.25f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, PhYellow.copy(alpha = 0.2f), CircleShape)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = subtitle.uppercase(),
                    color = PhYellow,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    letterSpacing = 1.8.sp
                )
                Spacer(modifier = Modifier.height(2.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = title,
                        color = PureWhite,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.3).sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .clip(CircleShape)
                            .background(PhGold)
                    )
                }
            }

            if (onActionClick != null && actionIcon != null) {
                IconButton(
                    onClick = onActionClick,
                    modifier = Modifier
                        .testTag("header_action_button")
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(PureWhite.copy(alpha = 0.12f))
                        .border(1.dp, PureWhite.copy(alpha = 0.25f), CircleShape)
                ) {
                    actionIcon()
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(PureWhite.copy(alpha = 0.12f))
                        .border(1.dp, PureWhite.copy(alpha = 0.25f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.HistoryEdu,
                        contentDescription = "Philippine History",
                        tint = PureWhite,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
