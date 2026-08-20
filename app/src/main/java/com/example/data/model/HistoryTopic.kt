package com.example.data.model

data class ConnectedLink(
    val targetId: String,
    val title: String,
    val relationship: String,
    val era: HistoricalEra,
    val badgeCode: String
)

data class HistoryTopic(
    val id: String,
    val title: String,
    val subtitle: String,
    val era: HistoricalEra,
    val dateOrYear: String,
    val location: String,
    val keyPeople: List<String>,
    val category: String, // Hero, Battle, Document, Movement, Artifact, Treaty, Revolution
    val shortSummary: String,
    val historicalBackground: String,
    val keyEventsAndDetails: String,
    val importanceAndImpact: String,
    val primarySourcesAndFacts: List<String>,
    val connectedLinks: List<ConnectedLink>,
    val badgeCode: String,
    val badgeColorHex: Long = 0xFF0038A8,
    val isWeeklyHighlight: Boolean = false
)
