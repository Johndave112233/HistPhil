package com.example.data.api

import com.example.BuildConfig
import com.example.data.repository.PhilippineHistoryData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit

data class ChatHistoryMessage(
    val role: String, // "user" or "model"
    val text: String
)

object GeminiHistoryService {

    private const val MODEL_NAME = "gemini-3.5-flash"
    private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models"

    private val client = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    private const val SYSTEM_INSTRUCTION = """
You are Kasaysayan AI, an expert, objective, and deeply knowledgeable digital museum curator and educational assistant specialized in Philippine History.

Your core mission is to answer ANY question about Philippine history with accuracy, academic rigor, and engaging educational explanations.

Guidelines for your answers:
1. ACCURACY & VERIFICATION: Provide fact-checked dates, names, locations, and historical background. Differentiate clearly between established historical facts, primary sources (e.g. Pigafetta's chronicle, Katipunan documents, Rizal's letters, Boxer Codex), and popular folklore or modern hoaxes (e.g. debunking the Code of Kalantiaw or the Princess Urduja myth when relevant).
2. STRUCTURE: For detailed questions, organize clearly with:
   - Direct, concise summary answering the prompt
   - Historical Context & Key Figures involved
   - Chronological events & details
   - Lasting Significance to Philippine nationhood & sovereignty
3. ENGAGEMENT: Use friendly, respectful, and educational tone. You can use English or Filipino / Tagalog depending on what the user asks.
4. CONNECTED DISCOVERY: At the end of major answers, provide 2-3 "🔗 Connected Topics / Related Questions" for the user to ask next.
"""

    suspend fun askHistoryQuestion(
        question: String,
        history: List<ChatHistoryMessage> = emptyList()
    ): String = withContext(Dispatchers.IO) {
        val apiKey = try {
            BuildConfig.GEMINI_API_KEY
        } catch (e: Exception) {
            ""
        }

        if (apiKey.isNullOrBlank() || apiKey == "MY_GEMINI_API_KEY") {
            return@withContext generateSmartOfflineAnswer(question)
        }

        try {
            val url = "$BASE_URL/$MODEL_NAME:generateContent?key=$apiKey"

            val rootJson = JSONObject()

            // System instruction
            val systemObj = JSONObject()
            val systemParts = JSONArray()
            systemParts.put(JSONObject().put("text", SYSTEM_INSTRUCTION))
            systemObj.put("parts", systemParts)
            rootJson.put("systemInstruction", systemObj)

            // Contents with conversation history
            val contentsArray = JSONArray()

            // Take the last 8 conversation turns to maintain context within budget
            val recentHistory = history.takeLast(8)
            for (msg in recentHistory) {
                val contentObj = JSONObject()
                contentObj.put("role", if (msg.role == "user") "user" else "model")
                val partsArray = JSONArray()
                partsArray.put(JSONObject().put("text", msg.text))
                contentObj.put("parts", partsArray)
                contentsArray.put(contentObj)
            }

            // Current user question
            val currentContentObj = JSONObject()
            currentContentObj.put("role", "user")
            val currentParts = JSONArray()
            currentParts.put(JSONObject().put("text", question))
            currentContentObj.put("parts", currentParts)
            contentsArray.put(currentContentObj)

            rootJson.put("contents", contentsArray)

            // Generation config
            val genConfig = JSONObject()
            genConfig.put("temperature", 0.35)
            genConfig.put("topP", 0.9)
            rootJson.put("generationConfig", genConfig)

            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = rootJson.toString().toRequestBody(mediaType)

            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string() ?: ""

            if (!response.isSuccessful) {
                return@withContext generateSmartOfflineAnswer(question)
            }

            val jsonResponse = JSONObject(responseBody)
            val candidates = jsonResponse.optJSONArray("candidates")
            val firstCandidate = candidates?.optJSONObject(0)
            val content = firstCandidate?.optJSONObject("content")
            val parts = content?.optJSONArray("parts")
            val text = parts?.optJSONObject(0)?.optString("text")

            if (!text.isNullOrBlank()) {
                text
            } else {
                generateSmartOfflineAnswer(question)
            }
        } catch (e: Exception) {
            generateSmartOfflineAnswer(question)
        }
    }

    fun generateSmartOfflineAnswer(query: String): String {
        val clean = query.trim().lowercase()

        // Check for specific comparisons or topics
        if (clean.contains("rizal") && clean.contains("bonifacio")) {
            return """
⚖️ **Comparison: Dr. José Rizal vs. Andres Bonifacio**

Both José Rizal and Andres Bonifacio are revered national heroes who fought for Filipino emancipation, but they differed fundamentally in strategy, social background, and philosophical approach:

**1. Strategy & Philosophy:**
• **Dr. José Rizal (The Reformist & Visionary):**
  - Advocated for peaceful assimilation, education, and moral upliftment.
  - Believed Filipinos must prove moral and intellectual maturity before armed self-governance.
  - Formed *La Liga Filipina* (1892) as a civic reform movement.
  - Authored *Noli Me Tángere* and *El Filibusterismo* to awaken the collective Filipino soul.

• **Andres Bonifacio (The Revolutionary & Organizer):**
  - Concluded that Spain would never grant genuine equality through peaceful appeals.
  - Founded the secret revolutionary society *Katipunan (KKK)* on July 7, 1892, immediately after Rizal's arrest.
  - Believed in direct armed revolution to overthrow colonial tyranny and achieve absolute sovereign independence.

**2. Mutual Respect:**
• Contrary to myths that they were rivals, Bonifacio held immense respect for Rizal, making "Rizal" the password for high-ranking Katipuneros and attempting to rescue Rizal from exile in Dapitan.

**3. Lasting Impact:**
• Rizal provided the intellectual conscience and national consciousness; Bonifacio provided the organizational courage and sword to enact it.

🔗 **Connected Topics to explore:**
• The Katipunan (KKK) & Andres Bonifacio
• Dr. José Rizal & The Propaganda Movement
• The Philippine Revolution of 1896
""".trimIndent()
        }

        if (clean.contains("kalantiaw") || clean.contains("code of kalantiaw")) {
            return """
🔍 **Historical Fact-Check: The Code of Kalantiaw**

• **Verdict:** **HISTORICAL HOAX / FABRICATION**

**The Historical Reality:**
• For decades in the 20th century, Philippine textbooks taught that Datu Kalantiaw wrote an ancient penal code in 1433 containing harsh, bizarre punishments.
• In 1968, renowned American historian **William Henry Scott** published his doctoral dissertation at the University of Santo Tomas (*Prehispanic Source Materials for the Study of Philippine History*).
• Scott conclusively proved that the Code of Kalantiaw was forged around 1913 by **Jose E. Marco** of Pontevedra, Negros Occidental, who sold fraudulent manuscripts to the Philippine National Library.

**Official Resolution:**
• In 1998, the National Historical Institute (now the National Historical Commission of the Philippines - NHCP) officially declared the Code of Kalantiaw a historical fabrication with no basis in pre-colonial history.

🔗 **Verified Pre-Colonial Artifacts:**
• Laguna Copperplate Inscription (900 AD)
• Boxer Codex (c. 1590)
• Butuan Golden Tara
""".trimIndent()
        }

        val matched = PhilippineHistoryData.searchTopics(query)
        if (matched.isNotEmpty()) {
            val topic = matched.first()
            val builder = StringBuilder()
            builder.append("🏛 **${topic.title}** (${topic.era.title})\n\n")
            builder.append("📅 **Date / Period:** ${topic.dateOrYear}\n")
            builder.append("📍 **Location:** ${topic.location}\n")
            builder.append("👥 **Key Figures:** ${topic.keyPeople.joinToString(", ")}\n\n")
            builder.append("📖 **Historical Background:**\n${topic.historicalBackground}\n\n")
            builder.append("⚡ **Key Events:**\n${topic.keyEventsAndDetails}\n\n")
            builder.append("🌟 **Lasting Impact:**\n${topic.importanceAndImpact}\n\n")
            builder.append("📚 **Verified Historical Facts & Primary Sources:**\n")
            topic.primarySourcesAndFacts.forEach { fact ->
                builder.append("• $fact\n")
            }
            builder.append("\n🔗 **Connected Historical Topics:**\n")
            topic.connectedLinks.forEach { link ->
                builder.append("• **${link.title}** (${link.relationship})\n")
            }
            return builder.toString()
        }

        // Generic comprehensive response
        return """
🏛 **Kasaysayan AI Historical Guide**

Regarding your question about "$query":

Philippine history is a rich, multifaceted narrative across seven key eras:
1. **Pre-colonial Era (Prior to 1521)** — Ancient seafaring, Baybayin script, Laguna Copperplate (900 AD), and indigenous barangays.
2. **Spanish Colonial Era (1521–1898)** — From the Battle of Mactan to 333 years of colonial administration, Christianization, and the Propaganda Movement.
3. **Philippine Revolution & 1st Republic (1896–1902)** — The Katipunan under Andres Bonifacio, Kawit Independence Proclamation (June 12, 1898), and Malolos Congress.
4. **American Period & Commonwealth (1898–1946)** — Treaty of Paris, Philippine-American War, public education, and Commonwealth Era under Quezon.
5. **Japanese Occupation (1941–1945)** — Fall of Bataan, Death March, heroic guerrilla resistance, and Allied liberation.
6. **Post-War Independence (1946–1986)** — Third Republic reconstruction, Cold War diplomacy, and Martial Law.
7. **Modern Philippines (1986–Present)** — The peaceful EDSA People Power Revolution and the 1987 Democratic Constitution.

💡 *Try asking specific questions such as:*
• "Why did Lapu-Lapu refuse to pay tribute to Magellan?"
• "What was written on the Laguna Copperplate Inscription?"
• "Explain the significance of the Gomburza execution in 1872."
• "How did the Katipunan discover the revolution was exposed?"
""".trimIndent()
    }
}
