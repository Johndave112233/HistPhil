package com.example.data.model

import androidx.compose.ui.graphics.Color
import com.example.ui.theme.PhBlue
import com.example.ui.theme.PhGold
import com.example.ui.theme.PhRed
import com.example.ui.theme.PhYellow

enum class HistoricalEra(
    val title: String,
    val periodSpan: String,
    val description: String,
    val accentColor: Long,
    val iconCode: String
) {
    PRE_COLONIAL(
        title = "Pre-colonial Era",
        periodSpan = "Prior to 1521",
        description = "Flourishing indigenous barangays, Baybayin script, Laguna Copperplate, Sultanates, and Asian maritime trade.",
        accentColor = 0xFFE5B800,
        iconCode = "PC"
    ),
    SPANISH_ERA(
        title = "Spanish Colonial Period",
        periodSpan = "1521 – 1898",
        description = "Three centuries of Spanish rule, Christianization, Manila Galleon trade, and the rise of the Propaganda Movement.",
        accentColor = 0xFF0038A8,
        iconCode = "SP"
    ),
    PHILIPPINE_REVOLUTION(
        title = "Philippine Revolution & 1st Republic",
        periodSpan = "1896 – 1902",
        description = "The Katipunan uprising, Rizal's martyrdom, Kawit independence declaration, and Malolos Constitution.",
        accentColor = 0xFFCE1126,
        iconCode = "RP"
    ),
    AMERICAN_PERIOD(
        title = "American Period & Commonwealth",
        periodSpan = "1898 – 1946",
        description = "Philippine-American War, public education system, Jones Act, and Commonwealth governance under Quezon.",
        accentColor = 0xFF1E5BCC,
        iconCode = "AM"
    ),
    JAPANESE_OCCUPATION(
        title = "Japanese Occupation & WWII",
        periodSpan = "1941 – 1945",
        description = "Bataan Death March, Corregidor defense, nationwide guerrilla resistance, and the Liberation of Manila.",
        accentColor = 0xFF9E0B1A,
        iconCode = "JP"
    ),
    INDEPENDENCE_ERA(
        title = "Post-War Independence Era",
        periodSpan = "1946 – 1986",
        description = "Third Republic reconstruction, Filipino First economic policy, Magsaysay's presidency, and Martial Law period.",
        accentColor = 0xFF002266,
        iconCode = "IN"
    ),
    MODERN_ERA(
        title = "Modern Philippines & EDSA",
        periodSpan = "1986 – Present",
        description = "1986 EDSA People Power Revolution, 1987 Constitution, democratic restoration, and contemporary nation-building.",
        accentColor = 0xFFFCD116,
        iconCode = "MD"
    )
}
