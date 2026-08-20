package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.data.local.AppDatabase
import com.example.data.repository.HistoryRepository
import com.example.ui.screens.assistant.AiAssistantScreen
import com.example.ui.screens.dashboard.DashboardScreen
import com.example.ui.screens.detail.HistoryDetailScreen
import com.example.ui.screens.home.HomeScreen
import com.example.ui.screens.library.LibraryScreen
import com.example.ui.screens.timeline.TimelineScreen
import com.example.ui.theme.KasaysayanTheme
import com.example.ui.theme.PhBlue
import com.example.ui.theme.PhGold
import com.example.ui.theme.PhYellow
import com.example.ui.theme.PureWhite
import com.example.ui.theme.Slate200
import com.example.ui.theme.Slate400
import com.example.ui.theme.Slate500
import com.example.ui.theme.Slate900
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Home", Icons.Default.Home)
    object Dashboard : Screen("dashboard?query={query}", "Explore", Icons.Default.Explore) {
        fun createRoute(query: String = ""): String {
            val encoded = if (query.isNotBlank()) URLEncoder.encode(query, StandardCharsets.UTF_8.toString()) else ""
            return "dashboard?query=$encoded"
        }
    }
    object Timeline : Screen("timeline", "Timeline", Icons.Default.Timeline)
    object Assistant : Screen("assistant?prompt={prompt}", "AI Guide", Icons.Default.AutoAwesome) {
        fun createRoute(prompt: String = ""): String {
            val encoded = if (prompt.isNotBlank()) URLEncoder.encode(prompt, StandardCharsets.UTF_8.toString()) else ""
            return "assistant?prompt=$encoded"
        }
    }
    object Library : Screen("library", "Saved", Icons.Default.Bookmark)
    object Detail : Screen("detail/{topicId}", "Detail", Icons.Default.Explore) {
        fun createRoute(topicId: String): String = "detail/$topicId"
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KasaysayanTheme {
                KasaysayanApp()
            }
        }
    }
}

@Composable
fun KasaysayanApp() {
    val context = LocalContext.current
    val database = remember { AppDatabase.getDatabase(context) }
    val repository = remember { HistoryRepository(database.historyDao()) }
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isDetailScreen = currentRoute?.startsWith("detail/") == true

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (!isDetailScreen) {
                KasaysayanBottomNavigation(
                    currentRoute = currentRoute,
                    onNavigateToTab = { screen ->
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.fillMaxSize()
        ) {
            // Home Screen
            composable(Screen.Home.route) {
                HomeScreen(
                    repository = repository,
                    onNavigateToDetail = { topicId ->
                        navController.navigate(Screen.Detail.createRoute(topicId))
                    },
                    onNavigateToSearch = { query ->
                        navController.navigate(Screen.Dashboard.createRoute(query))
                    },
                    onNavigateToAssistantWithPrompt = { prompt ->
                        navController.navigate(Screen.Assistant.createRoute(prompt))
                    }
                )
            }

            // Dashboard & Search Screen
            composable(
                route = Screen.Dashboard.route,
                arguments = listOf(navArgument("query") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                val rawQuery = backStackEntry.arguments?.getString("query") ?: ""
                val decodedQuery = try {
                    URLDecoder.decode(rawQuery, StandardCharsets.UTF_8.toString())
                } catch (e: Exception) {
                    rawQuery
                }
                DashboardScreen(
                    repository = repository,
                    initialQuery = decodedQuery,
                    onNavigateToDetail = { topicId ->
                        navController.navigate(Screen.Detail.createRoute(topicId))
                    },
                    onNavigateToAssistantWithPrompt = { prompt ->
                        navController.navigate(Screen.Assistant.createRoute(prompt))
                    }
                )
            }

            // Timeline Screen
            composable(Screen.Timeline.route) {
                TimelineScreen(
                    repository = repository,
                    onNavigateToDetail = { topicId ->
                        navController.navigate(Screen.Detail.createRoute(topicId))
                    }
                )
            }

            // AI Assistant Screen
            composable(
                route = Screen.Assistant.route,
                arguments = listOf(navArgument("prompt") {
                    type = NavType.StringType
                    defaultValue = ""
                })
            ) { backStackEntry ->
                val rawPrompt = backStackEntry.arguments?.getString("prompt") ?: ""
                val decodedPrompt = try {
                    URLDecoder.decode(rawPrompt, StandardCharsets.UTF_8.toString())
                } catch (e: Exception) {
                    rawPrompt
                }
                AiAssistantScreen(
                    repository = repository,
                    initialPrompt = decodedPrompt,
                    onNavigateToDetail = { topicId ->
                        navController.navigate(Screen.Detail.createRoute(topicId))
                    }
                )
            }

            // Library & Bookmarks Screen
            composable(Screen.Library.route) {
                LibraryScreen(
                    repository = repository,
                    onNavigateToDetail = { topicId ->
                        navController.navigate(Screen.Detail.createRoute(topicId))
                    },
                    onNavigateToSearch = { query ->
                        navController.navigate(Screen.Dashboard.createRoute(query))
                    }
                )
            }

            // History Detail Screen
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("topicId") { type = NavType.StringType })
            ) { backStackEntry ->
                val topicId = backStackEntry.arguments?.getString("topicId") ?: ""
                HistoryDetailScreen(
                    topicId = topicId,
                    repository = repository,
                    onBack = { navController.popBackStack() },
                    onNavigateToDetail = { targetTopicId ->
                        navController.navigate(Screen.Detail.createRoute(targetTopicId))
                    },
                    onNavigateToAssistantWithPrompt = { prompt ->
                        navController.navigate(Screen.Assistant.createRoute(prompt))
                    }
                )
            }
        }
    }
}

@Composable
fun KasaysayanBottomNavigation(
    currentRoute: String?,
    onNavigateToTab: (Screen) -> Unit,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        Screen.Home,
        Screen.Dashboard,
        Screen.Timeline,
        Screen.Assistant,
        Screen.Library
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(PureWhite)
            .border(1.dp, Slate200)
            .navigationBarsPadding()
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { screen ->
                val isSelected = when (screen) {
                    is Screen.Home -> currentRoute == Screen.Home.route
                    is Screen.Dashboard -> currentRoute?.startsWith("dashboard") == true
                    is Screen.Timeline -> currentRoute == Screen.Timeline.route
                    is Screen.Assistant -> currentRoute?.startsWith("assistant") == true
                    is Screen.Library -> currentRoute == Screen.Library.route
                    else -> false
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .testTag("nav_tab_${screen.title.lowercase()}")
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { onNavigateToTab(screen) }
                        .padding(horizontal = 8.dp, vertical = 6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) PhBlue.copy(alpha = 0.12f) else Color.Transparent),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = if (isSelected) PhBlue else Slate400,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = screen.title,
                        color = if (isSelected) PhBlue else Slate500,
                        fontSize = 10.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )

                    // Small indicator dot for active item
                    if (isSelected) {
                        Box(
                            modifier = Modifier
                                .padding(top = 2.dp)
                                .size(4.dp)
                                .clip(CircleShape)
                                .background(PhYellow)
                        )
                    } else {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
        }
    }
}
