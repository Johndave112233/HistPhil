package com.example.ui.screens.assistant

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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.api.ChatHistoryMessage
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

data class UiChatMessage(
    val id: String = java.util.UUID.randomUUID().toString(),
    val text: String,
    val isUser: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)

data class PromptCategory(
    val title: String,
    val prompts: List<String>
)

@Composable
fun AiAssistantScreen(
    repository: HistoryRepository,
    initialPrompt: String = "",
    onNavigateToDetail: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val messages = remember {
        mutableStateListOf(
            UiChatMessage(
                text = "Mabuhay! I am **Kasaysayan AI**, your intelligent Philippine History guide and digital museum curator.\n\nAsk me **ANYTHING** about Philippine history—from ancient pre-colonial kingdoms and heroes like Rizal & Bonifacio, to revolutions, battles, constitutions, and verified historical fact-checks. What would you like to explore today?",
                isUser = false
            )
        )
    }

    var inputText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var selectedCategoryIndex by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()
    val clipboardManager = LocalClipboardManager.current

    val categories = remember {
        listOf(
            PromptCategory(
                title = "🔥 Popular",
                prompts = listOf(
                    "Compare Rizal vs. Bonifacio's approach to independence",
                    "Why did Lapu-Lapu refuse to submit to Magellan in 1521?",
                    "What was the significance of the 1872 Cavite Mutiny and Gomburza?",
                    "How was the Katipunan secret society discovered in 1896?"
                )
            ),
            PromptCategory(
                title = "👑 Heroes",
                prompts = listOf(
                    "What were José Rizal's last words and what poem did he leave behind?",
                    "Who was Tandang Sora (Melchora Aquino) and how did she help the Katipunan?",
                    "Tell me about General Antonio Luna's military tactics and death",
                    "Who was Apolinario Mabini and why was he called the Sublime Paralytic?"
                )
            ),
            PromptCategory(
                title = "⚔️ Battles & Revolutions",
                prompts = listOf(
                    "What happened during the Battle of Tirad Pass and Gregorio del Pilar's stand?",
                    "Explain the Fall of Bataan and the 1942 Bataan Death March",
                    "Timeline of the 1896 Philippine Revolution up to Kawit Proclamation",
                    "What was the significance of the Battle of Leyte Gulf in WWII?"
                )
            ),
            PromptCategory(
                title = "🔍 Fact Checks",
                prompts = listOf(
                    "Was the Code of Kalantiaw authentic or a 20th-century hoax?",
                    "Where was the true site of the first Catholic Mass in the Philippines?",
                    "Did Andres Bonifacio have formal education?",
                    "What is the story behind the Laguna Copperplate Inscription (900 AD)?"
                )
            ),
            PromptCategory(
                title = "🇵🇭 Nationhood & EDSA",
                prompts = listOf(
                    "How did the peaceful 1986 EDSA People Power Revolution unfold?",
                    "Who designed and sewed the first Philippine National Flag?",
                    "What was the Pact of Biak-na-Bato and why did it collapse?",
                    "Explain the importance of the 1899 Malolos Constitution in Asia"
                )
            )
        )
    }

    fun sendMessage(query: String) {
        if (query.isBlank() || isLoading) return
        val userMsg = UiChatMessage(text = query.trim(), isUser = true)
        messages.add(userMsg)
        val currentInput = query.trim()
        inputText = ""
        isLoading = true

        coroutineScope.launch {
            listState.animateScrollToItem(messages.size - 1)

            // Convert message history
            val historyList = messages.dropLast(1).map {
                ChatHistoryMessage(role = if (it.isUser) "user" else "model", text = it.text)
            }

            val aiResponse = repository.askAiAssistant(currentInput, historyList)
            messages.add(UiChatMessage(text = aiResponse, isUser = false))
            isLoading = false
            listState.animateScrollToItem(messages.size - 1)
        }
    }

    LaunchedEffect(initialPrompt) {
        if (initialPrompt.isNotBlank()) {
            sendMessage(initialPrompt)
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Slate50)
    ) {
        GeometricHeader(
            title = "Kasaysayan AI",
            subtitle = "Ask Anything • Philippine History",
            onActionClick = {
                messages.clear()
                messages.add(
                    UiChatMessage(
                        text = "History archives refreshed. Ask me any question about Philippine history, leaders, battles, culture, or fact-checks.",
                        isUser = false
                    )
                )
            },
            actionIcon = {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Clear Chat",
                    tint = PureWhite,
                    modifier = Modifier.size(18.dp)
                )
            }
        )

        // Chat Message List
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(messages) { msg ->
                if (msg.isUser) {
                    // User Message
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .clip(RoundedCornerShape(18.dp, 18.dp, 4.dp, 18.dp))
                                .background(PhBlue)
                                .padding(14.dp)
                        ) {
                            Text(
                                text = msg.text,
                                color = PureWhite,
                                fontSize = 14.sp,
                                lineHeight = 20.sp
                            )
                        }
                    }
                } else {
                    // AI Response Message
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Column(modifier = Modifier.fillMaxWidth(0.95f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.padding(bottom = 6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                        .background(PhBlue),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.AutoAwesome,
                                        contentDescription = null,
                                        tint = PhYellow,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Kasaysayan AI",
                                    color = PhBlue,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(PhGold.copy(alpha = 0.15f))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "Fact-Checked",
                                        color = PhGold,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(
                                    onClick = {
                                        clipboardManager.setText(AnnotatedString(msg.text))
                                    },
                                    modifier = Modifier.size(24.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ContentCopy,
                                        contentDescription = "Copy Text",
                                        tint = Slate400,
                                        modifier = Modifier.size(14.dp)
                                    )
                                }
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(4.dp, 18.dp, 18.dp, 18.dp))
                                    .background(PureWhite)
                                    .border(1.dp, Slate200, RoundedCornerShape(4.dp, 18.dp, 18.dp, 18.dp))
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = msg.text,
                                    color = Slate800,
                                    fontSize = 13.sp,
                                    lineHeight = 22.sp
                                )
                            }
                        }
                    }
                }
            }

            if (isLoading) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(14.dp))
                                .background(PureWhite)
                                .border(1.dp, Slate200, RoundedCornerShape(14.dp))
                                .padding(horizontal = 16.dp, vertical = 10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(16.dp),
                                    color = PhBlue,
                                    strokeWidth = 2.dp
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "Searching Philippine historical archives & generating answer...",
                                    color = Slate600,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
            }
        }

        // Category Selection Tabs
        LazyRow(
            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Slate100)
        ) {
            items(categories.indices.toList()) { index ->
                val category = categories[index]
                val isSelected = selectedCategoryIndex == index
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) PhBlue else PureWhite)
                        .border(1.dp, if (isSelected) PhBlue else Slate200, RoundedCornerShape(12.dp))
                        .clickable { selectedCategoryIndex = index }
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(
                        text = category.title,
                        color = if (isSelected) PureWhite else Slate700,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                }
            }
        }

        // Preset Prompts for Selected Category
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Slate100)
                .padding(bottom = 8.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.width(12.dp))
            categories[selectedCategoryIndex].prompts.forEach { prompt ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .background(PureWhite)
                        .border(1.dp, Slate200, RoundedCornerShape(16.dp))
                        .clickable { sendMessage(prompt) }
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = prompt,
                        color = Slate800,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
        }

        // Chat Input Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(PureWhite)
                .border(1.dp, Slate200)
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier
                        .weight(1f)
                        .testTag("ai_assistant_input_field"),
                    textStyle = TextStyle(
                        color = Slate900,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    ),
                    cursorBrush = SolidColor(PhBlue),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (inputText.isNotBlank()) sendMessage(inputText)
                    }),
                    decorationBox = { innerTextField ->
                        if (inputText.isEmpty()) {
                            Text(
                                text = "Ask any question about Philippine history...",
                                color = Slate400,
                                fontSize = 13.sp
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .testTag("ai_assistant_send_button")
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(if (inputText.isNotBlank()) PhBlue else Slate200)
                        .clickable(enabled = inputText.isNotBlank()) {
                            sendMessage(inputText)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = if (inputText.isNotBlank()) PureWhite else Slate400,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
