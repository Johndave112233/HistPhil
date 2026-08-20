package com.example.data.repository

import com.example.data.model.ConnectedLink
import com.example.data.model.HistoricalEra
import com.example.data.model.HistoryTopic

object PhilippineHistoryData {

    val topics: List<HistoryTopic> = listOf(
        HistoryTopic(
            id = "battle-of-mactan",
            title = "Battle of Mactan",
            subtitle = "The first major armed resistance of indigenous Filipinos against European colonization",
            era = HistoricalEra.SPANISH_ERA,
            dateOrYear = "April 27, 1521",
            location = "Mactan Island, Cebu",
            keyPeople = listOf("Lapu-Lapu", "Ferdinand Magellan", "Rajah Humabon", "Antonio Pigafetta"),
            category = "Battle",
            shortSummary = "Datu Lapu-Lapu and his warriors successfully repelled and defeated the Spanish landing force led by Portuguese explorer Ferdinand Magellan.",
            historicalBackground = "Ferdinand Magellan arrived in the archipelago in March 1521 under the Spanish crown. After forming an alliance with Rajah Humabon of Cebu and converting locals to Christianity, Magellan demanded that neighboring chiefs submit to the Spanish King and pay tribute. Datu Lapu-Lapu of Mactan firmly refused.",
            keyEventsAndDetails = "On the morning of April 27, 1521, Magellan sailed with 60 armored Spanish troops and hundreds of Cebuano warriors. Due to shallow coral reefs, the Spanish galleons could not anchor close to shore. Magellan ordered his men to wade through waist-deep water. Lapu-Lapu's force of approximately 1,500 fighters engaged them with poisoned arrows, kampilan swords, and fire-hardened bamboo spears, targeting the unarmored legs of the Spaniards. Magellan was wounded and killed in the surf, forcing the Spanish retreat.",
            importanceAndImpact = "The battle delayed Spanish colonization of the Philippines for 44 years (until Miguel López de Legazpi arrived in 1565). Lapu-Lapu became celebrated as the first national hero of the Philippines for defending indigenous sovereignty.",
            primarySourcesAndFacts = listOf(
                "Antonio Pigafetta's 'Primo Viaggio Intorno Al Mondo' (First Voyage Around the World) is the primary eyewitness chronicle.",
                "Magellan's remaining expedition ship 'Victoria', commanded by Juan Sebastián Elcano, went on to complete the first recorded circumnavigation of the globe in 1522.",
                "Lapu-Lapu was not an old man with a huge sword as often portrayed in folklore, but an active middle-aged datu defending his territory's autonomy."
            ),
            connectedLinks = listOf(
                ConnectedLink("magellan-expedition", "Magellan's Global Expedition", "Cause & Exploration Context", HistoricalEra.SPANISH_ERA, "ME"),
                ConnectedLink("lapu-lapu", "Lapu-Lapu", "Key Leader & Chieftain", HistoricalEra.SPANISH_ERA, "LL"),
                ConnectedLink("spanish-colonization-legazpi", "Legazpi's Colonization (1565)", "Downstream Consequence", HistoricalEra.SPANISH_ERA, "ML"),
                ConnectedLink("precolonial-barangay", "Pre-colonial Barangay System", "Societal Context", HistoricalEra.PRE_COLONIAL, "PC")
            ),
            badgeCode = "BM",
            badgeColorHex = 0xFF0038A8,
            isWeeklyHighlight = true
        ),

        HistoryTopic(
            id = "jose-rizal",
            title = "Dr. José Rizal",
            subtitle = "Polymath, patriot, author, and National Hero of the Philippines",
            era = HistoricalEra.SPANISH_ERA,
            dateOrYear = "June 19, 1861 – December 30, 1896",
            location = "Calamba, Laguna / Bagumbayan (Luneta), Manila",
            keyPeople = listOf("José Rizal", "Marcelo H. del Pilar", "Graciano López Jaena", "Paciano Rizal", "Josephine Bracken"),
            category = "Hero",
            shortSummary = "Through his seminal novels 'Noli Me Tángere' and 'El Filibusterismo' and essays in La Solidaridad, Rizal awakened national consciousness and exposed the abuses of the Spanish colonial regime.",
            historicalBackground = "Born to an educated mestizo family in Calamba, Rizal studied medicine and ophthalmology in Manila and Europe. Witnessing the injustice of friar estates in Laguna and the execution of the Gomburza priests in 1872, he dedicated his life to reforming Philippine colonial society through peaceful advocacy and education.",
            keyEventsAndDetails = "In 1887, Rizal published 'Noli Me Tángere' in Berlin, followed by 'El Filibusterismo' in Ghent (1891). In 1892, he returned to Manila and founded 'La Liga Filipina', a civic reform association. Spanish authorities swiftly arrested and exiled him to Dapitan, Zamboanga for four years. When the Katipunan revolution erupted in 1896, Rizal was accused of instigating rebellion, subjected to a military kangaroo court, and executed by firing squad at Bagumbayan on December 30, 1896.",
            importanceAndImpact = "Rizal's martyrdom galvanized the entire nation. It transformed localized unrest into a unified national revolution. His intellectual legacy established the foundation of Philippine nationalism and identity.",
            primarySourcesAndFacts = listOf(
                "His final farewell poem, 'Mi último adiós', hidden inside an alcohol cooking stove, was delivered to his family on the eve of his execution.",
                "Rizal spoke over 20 languages and was an accomplished sculptor, painter, opthalmic surgeon, ethnologist, and essayist.",
                "Rizal advocated for equality, assimilation as a Spanish province, and education, believing moral readiness was essential before armed self-rule."
            ),
            connectedLinks = listOf(
                ConnectedLink("propaganda-movement", "The Propaganda Movement", "Reformist Platform", HistoricalEra.SPANISH_ERA, "PM"),
                ConnectedLink("noli-me-tangere", "Noli Me Tángere & El Fili", "Literary Masterpieces", HistoricalEra.SPANISH_ERA, "NL"),
                ConnectedLink("gomburza-1872", "Execution of Gomburza", "Formative Childhood Catalyst", HistoricalEra.SPANISH_ERA, "GB"),
                ConnectedLink("philippine-revolution", "Philippine Revolution of 1896", "Resulting National Uprising", HistoricalEra.PHILIPPINE_REVOLUTION, "RP"),
                ConnectedLink("katipunan", "The Katipunan (KKK)", "Secret Society Inspired by Rizal", HistoricalEra.PHILIPPINE_REVOLUTION, "KK")
            ),
            badgeCode = "JR",
            badgeColorHex = 0xFFCE1126
        ),

        HistoryTopic(
            id = "philippine-revolution",
            title = "The Philippine Revolution",
            subtitle = "The first anti-colonial national revolution and democratic republic in Asia",
            era = HistoricalEra.PHILIPPINE_REVOLUTION,
            dateOrYear = "August 1896 – June 1898",
            location = "Manila, Cavite, Bulacan, Morong, Central Luzon",
            keyPeople = listOf("Andres Bonifacio", "Emilio Aguinaldo", "Emilio Jacinto", "Apolinario Mabini", "Melchora Aquino"),
            category = "Revolution",
            shortSummary = "An armed nationwide revolution initiated by the Katipunan to end 333 years of Spanish colonial hegemony and establish an independent sovereign nation.",
            historicalBackground = "After decades of oppressive colonial tribute, forced labor (polo y servicios), racial discrimination, and the failure of peaceful reform movements in Spain, the masses mobilized under Andres Bonifacio's revolutionary secret society, the Katipunan (KKK).",
            keyEventsAndDetails = "The discovery of the Katipunan in August 1896 triggered the 'Cry of Pugad Lawin', where revolutionaries tore their tax certificates (cédulas). Fighting erupted across 8 provinces. Leadership shifted to Emilio Aguinaldo following the controversial Tejeros Convention (1897). Despite the Pact of Biak-na-Bato truce in December 1897, fighting resumed in 1898 during the Spanish-American War, culminating in the proclamation of independence in Kawit on June 12, 1898.",
            importanceAndImpact = "Led to the establishment of the Malolos Congress and the First Philippine Republic (January 1899), the first constitutional republic in Asia.",
            primarySourcesAndFacts = listOf(
                "The 8 rays of the sun in the Philippine flag symbolize the 8 initial provinces placed under martial law by Spanish Governor-General Blanco: Manila, Cavite, Bulacan, Pampanga, Nueva Ecija, Tarlac, Laguna, and Batangas.",
                "Emilio Jacinto served as the 'Brain of the Katipunan' and author of the 'Kartilya ng Katipunan', setting moral and ethical codes for members."
            ),
            connectedLinks = listOf(
                ConnectedLink("katipunan", "The Katipunan (KKK)", "Foundational Secret Society", HistoricalEra.PHILIPPINE_REVOLUTION, "KK"),
                ConnectedLink("andres-bonifacio", "Andres Bonifacio", "Supremo & Catalyst", HistoricalEra.PHILIPPINE_REVOLUTION, "AB"),
                ConnectedLink("declaration-of-independence", "Kawit Declaration of Independence", "Triumphant Milestone", HistoricalEra.PHILIPPINE_REVOLUTION, "DI"),
                ConnectedLink("jose-rizal", "Dr. José Rizal", "Ideological Inspiration", HistoricalEra.SPANISH_ERA, "JR"),
                ConnectedLink("treaty-of-paris", "Treaty of Paris (1898)", "International Transfer of Power", HistoricalEra.AMERICAN_PERIOD, "TP")
            ),
            badgeCode = "RP",
            badgeColorHex = 0xFFCE1126
        ),

        HistoryTopic(
            id = "katipunan",
            title = "Kataas-taasang, Kagalang-galangang Katipunan (KKK)",
            subtitle = "The clandestine revolutionary society founded by Andres Bonifacio",
            era = HistoricalEra.PHILIPPINE_REVOLUTION,
            dateOrYear = "Founded July 7, 1892",
            location = "Tondo, Manila (72 Azcárraga St.)",
            keyPeople = listOf("Andres Bonifacio", "Emilio Jacinto", "Ladislao Diwa", "Teodoro Plata", "Deodato Arellano", "Gregoria de Jesus"),
            category = "Movement",
            shortSummary = "A secret society dedicated to securing complete Philippine independence from Spain through armed revolution and fostering mutual aid, moral uprightness, and brotherhood.",
            historicalBackground = "Formed on the very night news of José Rizal's arrest and deportation to Dapitan was published, marking the definitive realization that peaceful petitions to the Spanish crown were futile.",
            keyEventsAndDetails = "The Katipunan used Masonic-style initiation rituals, secret codes, and blood compacts (sandugo). Members signed their oaths with their own blood. Gregoria de Jesus ('Lakambini ng Katipunan') guarded vital secret documents and firearms. By 1896, membership had expanded to tens of thousands across Luzon and the Visayas.",
            importanceAndImpact = "Built the clandestine military and social machinery that launched the 1896 revolution, demonstrating the organizational power of the Filipino working and middle classes.",
            primarySourcesAndFacts = listOf(
                "The 'Kartilya' contains 14 ethical teachings emphasizing equality of all persons regardless of skin color, defense of the oppressed, and honor in one's word.",
                "The Katipunan had three grades of membership: Katipon (member), Kawal (soldier), and Bayani (patriot)."
            ),
            connectedLinks = listOf(
                ConnectedLink("andres-bonifacio", "Andres Bonifacio", "Founder & Supremo", HistoricalEra.PHILIPPINE_REVOLUTION, "AB"),
                ConnectedLink("philippine-revolution", "The Philippine Revolution", "Direct Military Manifestation", HistoricalEra.PHILIPPINE_REVOLUTION, "RP"),
                ConnectedLink("jose-rizal", "Dr. José Rizal", "Honorary Password & Idol", HistoricalEra.SPANISH_ERA, "JR")
            ),
            badgeCode = "KK",
            badgeColorHex = 0xFFCE1126
        ),

        HistoryTopic(
            id = "andres-bonifacio",
            title = "Andres Bonifacio",
            subtitle = "The 'Father of the Philippine Revolution' and Supremo of the Katipunan",
            era = HistoricalEra.PHILIPPINE_REVOLUTION,
            dateOrYear = "November 30, 1863 – May 10, 1897",
            location = "Tondo, Manila / Mount Buntis, Maragondon, Cavite",
            keyPeople = listOf("Andres Bonifacio", "Gregoria de Jesus", "Procopio Bonifacio", "Emilio Jacinto", "Emilio Aguinaldo"),
            category = "Hero",
            shortSummary = "Self-educated working-class leader who organized the Katipunan and rallied the Filipino people to take up arms for national liberation.",
            historicalBackground = "Orphaned at a young age, Bonifacio worked as a craftsman, warehouse clerk, and theater actor to support his siblings. He voraciously read books on the French Revolution, international law, the American presidency, and Rizal's novels.",
            keyEventsAndDetails = "Bonifacio established the KKK in 1892, drafted the poignant nationalist poem 'Pag-ibig sa Tinubuang Lupa', and directed the initial revolutionary offensives in Manila and San Juan del Monte. Internal political friction with the Magdalo faction in Cavite culminated in the Tejeros Convention, where his leadership was disputed by Daniel Tirona. Bonifacio was subsequently tried for treason by a Cavite military tribunal and executed in Maragondon on May 10, 1897.",
            importanceAndImpact = "Bonifacio symbolizes unyielding grassroots patriotism, egalitarianism, and the uncompromising pursuit of complete national independence.",
            primarySourcesAndFacts = listOf(
                "His poem 'Pag-ibig sa Tinubuang Lupa' ('Love for One's Native Land') is recognized as one of the most powerful patriotic works in Philippine literature.",
                "Contrary to colonial-era caricatures of being illiterate, Bonifacio was well-read, wrote fluent Tagalog and Spanish, and managed international business logistics for British and German trading firms."
            ),
            connectedLinks = listOf(
                ConnectedLink("katipunan", "The Katipunan (KKK)", "Society He Founded", HistoricalEra.PHILIPPINE_REVOLUTION, "KK"),
                ConnectedLink("philippine-revolution", "The Philippine Revolution", "Uprising He Commanded", HistoricalEra.PHILIPPINE_REVOLUTION, "RP"),
                ConnectedLink("declaration-of-independence", "Kawit Declaration of Independence", "Resulting Republic", HistoricalEra.PHILIPPINE_REVOLUTION, "DI")
            ),
            badgeCode = "AB",
            badgeColorHex = 0xFFCE1126
        ),

        HistoryTopic(
            id = "declaration-of-independence",
            title = "Declaration of Philippine Independence",
            subtitle = "The historic proclamation of Philippine national sovereignty in Kawit, Cavite",
            era = HistoricalEra.PHILIPPINE_REVOLUTION,
            dateOrYear = "June 12, 1898 (4:20 PM)",
            location = "Ancestral House of Emilio Aguinaldo, Kawit, Cavite",
            keyPeople = listOf("Emilio Aguinaldo", "Ambrosio Rianzares Bautista", "Julian Felipe", "Marcela Agoncillo"),
            category = "Document",
            shortSummary = "Filipino revolutionary forces proclaimed the sovereign independence of the Philippine Islands from Spain and unveiled the national flag and anthem.",
            historicalBackground = "With revolutionary forces having liberated virtually all provinces of Luzon from Spanish control, General Emilio Aguinaldo recognized the urgent necessity of proclaiming sovereignty before the international community as the Spanish-American War escalated.",
            keyEventsAndDetails = "At Aguinaldo's balcony in Kawit, Ambrosio Rianzares Bautista read the 21-page 'Acta de la Proclamación de la Independencia del Pueblo Filipino'. The Philippine National Flag, sewn in Hong Kong by Marcela Agoncillo, Lorenza Agoncillo, and Delfina Herbosa de Natividad, was formally unfurled. The San Francisco de Malabon band performed the 'Marcha Nacional Filipina' composed by Julian Felipe.",
            importanceAndImpact = "Marked the birth of the Filipino nation-state and established June 12 as Philippine National Independence Day (Araw ng Kalayaan).",
            primarySourcesAndFacts = listOf(
                "The original Act of Independence was signed by 98 persons, including one American army officer, Colonel L.M. Johnson.",
                "Jose Palma later wrote the Spanish lyrics 'Filipinas' in 1899, which became the words for the national anthem 'Lupang Hinirang'."
            ),
            connectedLinks = listOf(
                ConnectedLink("philippine-revolution", "The Philippine Revolution", "Revolutionary Struggle", HistoricalEra.PHILIPPINE_REVOLUTION, "RP"),
                ConnectedLink("treaty-of-paris", "Treaty of Paris (1898)", "Imperial Subversion by USA", HistoricalEra.AMERICAN_PERIOD, "TP"),
                ConnectedLink("malolos-congress", "Malolos Congress & Constitution", "Constitutional Ratification", HistoricalEra.PHILIPPINE_REVOLUTION, "MC")
            ),
            badgeCode = "DI",
            badgeColorHex = 0xFFFCD116
        ),

        HistoryTopic(
            id = "edsa-revolution",
            title = "EDSA People Power Revolution",
            subtitle = "A world-renowned peaceful uprising that restored Philippine democracy",
            era = HistoricalEra.MODERN_ERA,
            dateOrYear = "February 22 – 25, 1986",
            location = "Epifanio de los Santos Avenue (EDSA), Metro Manila",
            keyPeople = listOf("Corazon C. Aquino", "Jaime Cardinal Sin", "Juan Ponce Enrile", "Fidel V. Ramos", "Ferdinand E. Marcos"),
            category = "Revolution",
            shortSummary = "Millions of unarmed Filipino citizens gathered along EDSA to protect rebel military factions, peacefully ousting the 20-year authoritarian regime of Ferdinand Marcos.",
            historicalBackground = "Following the 1983 assassination of opposition leader Benigno 'Ninoy' Aquino Jr., economic turmoil, human rights abuses during Martial Law (proclaimed 1972), and widespread fraud in the February 1986 snap elections, public resentment reached a tipping point.",
            keyEventsAndDetails = "On February 22, Defense Minister Juan Ponce Enrile and AFP Vice Chief of Staff Fidel Ramos defected from Marcos and fortified Camp Aguinaldo and Camp Crame. Archbishop of Manila Jaime Cardinal Sin broadcasted an urgent plea over Radio Veritas calling citizens to support them. Over two million men, women, nuns, and students formed human barricades along EDSA, offering rosaries and flowers to tank crews. On February 25, Corazon Aquino was inaugurated President at Club Filipino, and the Marcos family departed Malacañang for Hawaii.",
            importanceAndImpact = "Restored democratic institutions, freedom of the press, and led to the drafting of the 1987 Philippine Constitution. Inspired peaceful anti-dictatorship movements worldwide, including in Eastern Europe.",
            primarySourcesAndFacts = listOf(
                "Radio Veritas played a pivotal communication lifeline until government forces jammed and bombed its transmitters, after which DZRJ (dubbed 'Radyo Bandido') led by June Keithley continued the broadcasts.",
                "Not a single shot was fired between the massive civilian crowds and military tanks at EDSA."
            ),
            connectedLinks = listOf(
                ConnectedLink("martial-law-1972", "Martial Law Era (1972-1981)", "Preceding Authoritarian Period", HistoricalEra.INDEPENDENCE_ERA, "ML"),
                ConnectedLink("constitution-1987", "1987 Philippine Constitution", "Restored Democratic Framework", HistoricalEra.MODERN_ERA, "CN"),
                ConnectedLink("philippine-revolution", "1896 Philippine Revolution", "Historical Lineage of Freedom", HistoricalEra.PHILIPPINE_REVOLUTION, "RP")
            ),
            badgeCode = "ED",
            badgeColorHex = 0xFFFCD116
        ),

        HistoryTopic(
            id = "laguna-copperplate",
            title = "Laguna Copperplate Inscription",
            subtitle = "The earliest known written document found in Philippine history (900 AD)",
            era = HistoricalEra.PRE_COLONIAL,
            dateOrYear = "April 21, 900 AD (Saka year 822)",
            location = "Lumban River, Laguna Lake, Luzon",
            keyPeople = listOf("Namwaran (beneficiary)", "Lord Minister of Tondo (Jayadewa)", "Antongis", "Pailah authorities"),
            category = "Artifact",
            shortSummary = "An ancient inscribed copper sheet that documents the official legal acquittal of a hereditary debt in the Kingdom of Tondo, proving complex pre-colonial Philippine civilization.",
            historicalBackground = "Found in 1989 by a sand dredger in the Lumban River of Laguna, this thin copper plate measuring 20 x 30 cm overturned the outdated colonial myth that ancient Filipinos were illiterate or lacked formalized legal institutions before 1521.",
            keyEventsAndDetails = "Deciphered by Dutch anthropologist Antoon Postma in 1992, the inscription is written in the Early Kawi script with a mix of Old Malay, Old Tagalog, Old Javanese, and Sanskrit loanwords. The text records that the Chief of Tondo acquitted Namwaran and his descendants of a gold debt of 1 kati and 8 suwarnas (approximately 865 grams of gold).",
            importanceAndImpact = "Pushed verified Philippine written history back by over 600 years (from 1521 to 900 AD). Demonstrates that pre-colonial Luzon was an integrated participant in the sophisticated Indianized trading spheres of Southeast Asia (such as the Srivijaya and Mataram realms).",
            primarySourcesAndFacts = listOf(
                "The artifact is designated a National Cultural Treasure and preserved at the National Museum of the Philippines in Manila.",
                "It references known geographic toponyms that still exist today: Tondo (Tundun), Puliran (Pulilan), Pailah (Pila, Laguna), and Binuangan (Obando, Bulacan)."
            ),
            connectedLinks = listOf(
                ConnectedLink("precolonial-barangay", "Pre-colonial Barangay Society", "Social & Political Context", HistoricalEra.PRE_COLONIAL, "PC"),
                ConnectedLink("sultanate-of-sulu", "The Sultanate of Sulu", "Maritime Trade & Diplomacy", HistoricalEra.PRE_COLONIAL, "SS"),
                ConnectedLink("battle-of-mactan", "Battle of Mactan", "Transition to Spanish Contact", HistoricalEra.SPANISH_ERA, "BM")
            ),
            badgeCode = "LC",
            badgeColorHex = 0xFFE5B800
        ),

        HistoryTopic(
            id = "gomburza-1872",
            title = "Execution of Gomburza",
            subtitle = "The martyrdom of three Filipino secular priests that ignited Philippine nationalism",
            era = HistoricalEra.SPANISH_ERA,
            dateOrYear = "February 17, 1872",
            location = "Bagumbayan Field, Manila",
            keyPeople = listOf("Mariano Gomez", "Jose Burgos", "Jacinto Zamora", "Gov. Gen. Rafael de Izquierdo"),
            category = "Event",
            shortSummary = "Secular priests Fathers Mariano Gomez, Jose Burgos, and Jacinto Zamora were unjustly executed by garrote, awakening national consciousness among young Filipinos.",
            historicalBackground = "During the 19th century, native Filipino secular clergy campaigned for the secularization of Philippine parishes, demanding equal rights to administer parishes held predominantly by Spanish regular friar orders (Dominicans, Augustinians, Franciscans). Fathers Burgos and Gomez were leading intellectual champions of secularization.",
            keyEventsAndDetails = "Following the localized 1872 Cavite Mutiny at the San Felipe fort, Spanish colonial authorities falsely implicated Gomburza as ringleaders of a grand treason conspiracy. After a swift mock trial, the three priests were executed publicly by garrote vil in Bagumbayan on February 17, 1872, in front of a weeping Filipino crowd.",
            importanceAndImpact = "Dr. José Rizal dedicated his second masterpiece 'El Filibusterismo' to their memory, stating that without 1872, he would have become a Jesuit instead of writing the novels that stirred the nation.",
            primarySourcesAndFacts = listOf(
                "Father Mariano Gomez was 72 years old, Father Jose Burgos was 35, and Father Jacinto Zamora was 36 at their execution.",
                "The word 'Filipino', previously reserved exclusively for Spaniards born in the Philippines (insulares), began to be embraced by native indios as a shared national identity following Gomburza."
            ),
            connectedLinks = listOf(
                ConnectedLink("jose-rizal", "Dr. José Rizal", "Dedication of El Fili", HistoricalEra.SPANISH_ERA, "JR"),
                ConnectedLink("propaganda-movement", "The Propaganda Movement", "Subsequent Reform Generation", HistoricalEra.SPANISH_ERA, "PM"),
                ConnectedLink("philippine-revolution", "The Philippine Revolution", "Eventual Armed Culmination", HistoricalEra.PHILIPPINE_REVOLUTION, "RP")
            ),
            badgeCode = "GB",
            badgeColorHex = 0xFF0038A8
        ),

        HistoryTopic(
            id = "treaty-of-paris",
            title = "The Treaty of Paris (1898)",
            subtitle = "The accord ending the Spanish-American War and ceding the Philippines to the USA",
            era = HistoricalEra.AMERICAN_PERIOD,
            dateOrYear = "December 10, 1898",
            location = "Paris, France",
            keyPeople = listOf("Felipe Agoncillo (Diplomat)", "William McKinley", "William R. Day", "Don Eugenio Montero Ríos"),
            category = "Treaty",
            shortSummary = "Spain ceded sovereignty of the Philippines, Guam, and Puerto Rico to the United States for $20 million, ignoring the declared independence of the First Philippine Republic.",
            historicalBackground = "Following the destruction of the Spanish fleet in Manila Bay by Commodore George Dewey, Filipino revolutionaries controlled the archipelago except for intramuros Manila. The US and Spanish commissioners met in Paris to negotiate peace terms.",
            keyEventsAndDetails = "Philippine diplomatic representative Felipe Agoncillo traveled to Washington D.C. and Paris to plead for international recognition of the Philippine Republic. He was barred from participating in the negotiations. Under Article III of the treaty, Spain sold the archipelago to the United States for $20,000,000.",
            importanceAndImpact = "Set the stage directly for the outbreak of the bloody Philippine-American War in February 1899, as Filipinos refused to substitute one colonial master for another.",
            primarySourcesAndFacts = listOf(
                "Felipe Agoncillo published his official 'Memorial to the Senate of the United States' protesting the moral and legal invalidity of selling a self-governing people.",
                "The US Senate ratified the treaty by a single vote margin on February 6, 1899, two days after hostilities erupted in Manila."
            ),
            connectedLinks = listOf(
                ConnectedLink("declaration-of-independence", "Kawit Declaration of Independence", "Suppressed Sovereign State", HistoricalEra.PHILIPPINE_REVOLUTION, "DI"),
                ConnectedLink("philippine-american-war", "Philippine-American War", "Direct Armed Conflict", HistoricalEra.AMERICAN_PERIOD, "PA"),
                ConnectedLink("battle-of-tirad-pass", "Battle of Tirad Pass", "Tragic Resistance Episode", HistoricalEra.AMERICAN_PERIOD, "TP")
            ),
            badgeCode = "TP",
            badgeColorHex = 0xFF1E5BCC
        ),

        HistoryTopic(
            id = "battle-of-tirad-pass",
            title = "Battle of Tirad Pass",
            subtitle = "The heroic rearguard defense by General Gregorio del Pilar",
            era = HistoricalEra.AMERICAN_PERIOD,
            dateOrYear = "December 2, 1899",
            location = "Mount Tirad, Cervantes, Ilocos Sur",
            keyPeople = listOf("Gen. Gregorio del Pilar", "President Emilio Aguinaldo", "Major Peyton C. March", "Januario Galut"),
            category = "Battle",
            shortSummary = "The 'Boy General' Gregorio del Pilar and 60 revolutionary soldiers mounted a desperate rearguard stand at a narrow 4,500-foot mountain pass to cover the retreat of President Aguinaldo.",
            historicalBackground = "As the American military advanced north through Luzon with superior firepower and cavalry, President Emilio Aguinaldo moved his government into the rugged mountains of the Cordilleras. Del Pilar volunteered to fortify Tirad Pass and halt the American 33rd Volunteer Infantry.",
            keyEventsAndDetails = "Positioned behind stone barricades overlooking the only narrow trail, Del Pilar's 60 riflemen held off 300 American troops for over five hours. The standoff broke when a local guide named Januario Galut showed the Americans a hidden cliff trail that outflanked the Filipino position from behind. Del Pilar was shot in the neck and killed, and 52 of his 60 defenders perished.",
            importanceAndImpact = "Del Pilar's sacrifice gave Aguinaldo crucial days to evade capture and prolong the resistance in northern Luzon. He became celebrated as one of the youngest and most romanticized military heroes in Philippine history.",
            primarySourcesAndFacts = listOf(
                "Del Pilar was only 24 years old at the time of his death.",
                "In his pocket diary, found by American officers, his last written entry read: 'I am surrounded by fearful odds that will overcome me and my gallant boys, but I am pleased to die fighting for my beloved country.'"
            ),
            connectedLinks = listOf(
                ConnectedLink("treaty-of-paris", "Treaty of Paris (1898)", "Originating Imperial Conflict", HistoricalEra.AMERICAN_PERIOD, "TP"),
                ConnectedLink("philippine-american-war", "Philippine-American War", "Broader Campaign", HistoricalEra.AMERICAN_PERIOD, "PA"),
                ConnectedLink("declaration-of-independence", "Kawit Declaration of Independence", "Republic He Defended", HistoricalEra.PHILIPPINE_REVOLUTION, "DI")
            ),
            badgeCode = "TP",
            badgeColorHex = 0xFF1E5BCC
        ),

        HistoryTopic(
            id = "fall-of-bataan",
            title = "Fall of Bataan & Death March",
            subtitle = "The grueling World War II defense of Bataan and the catastrophic 65-mile prisoner march",
            era = HistoricalEra.JAPANESE_OCCUPATION,
            dateOrYear = "April 9, 1942",
            location = "Bataan Peninsula to Camp O'Donnell, Capas, Tarlac",
            keyPeople = listOf("Major Gen. Edward P. King Jr.", "Gen. Douglas MacArthur", "Gen. Masaharu Homma", "Vicente Lim"),
            category = "Battle",
            shortSummary = "After four months of desperate resistance against invading Imperial Japanese forces with no food or medical supplies, Allied Filipino-American defenders surrendered, enduring the infamous Bataan Death March.",
            historicalBackground = "Following the bombing of Pearl Harbor and Clark Air Base in December 1941, Japanese 14th Army troops swept through Luzon. General Douglas MacArthur executed War Plan Orange-3, withdrawing combined USAFFE forces into the fortified Bataan peninsula to await reinforcements that never arrived.",
            keyEventsAndDetails = "Besieged on half-rations and ravaged by malaria, dysentery, and beriberi, 76,000 troops (66,000 Filipinos, 10,000 Americans) held out far longer than expected. On April 9, 1942, Major General Edward King surrendered. The Japanese army forced the exhausted captives to march 65 miles (105 km) under scorching sun with minimal water. An estimated 10,000 Filipinos and 650 Americans died from beatings, dehydration, bayonet executions, and exhaustion along the route.",
            importanceAndImpact = "The four-month defense of Bataan upset the Japanese military conquest timetable across the entire Pacific theater, allowing Australia and the US to organize counter-offensives. April 9 is commemorated annually in the Philippines as 'Araw ng Kagitingan' (Day of Valor).",
            primarySourcesAndFacts = listOf(
                "Third Lieutenant Norman Reyes delivered the historic broadcast from the Voice of Freedom: 'Bataan has fallen. The Philippine-American troops on this war-ravaged and bloodstained peninsula have laid down their arms... but the spirit that made Bataan stand endures.'",
                "The Capas National Shrine in Tarlac stands on the grounds of Camp O'Donnell where thousands of prisoners subsequently died."
            ),
            connectedLinks = listOf(
                ConnectedLink("japanese-occupation", "Japanese Occupation & Resistance", "Subsequent 3-Year Guerilla War", HistoricalEra.JAPANESE_OCCUPATION, "JP"),
                ConnectedLink("battle-of-leyte-gulf", "Battle of Leyte Gulf & Liberation", "Allied Return in 1944", HistoricalEra.JAPANESE_OCCUPATION, "LG"),
                ConnectedLink("commonwealth-era", "Philippine Commonwealth Era", "Pre-War Government", HistoricalEra.AMERICAN_PERIOD, "CW")
            ),
            badgeCode = "FB",
            badgeColorHex = 0xFF9E0B1A
        ),

        HistoryTopic(
            id = "propaganda-movement",
            title = "The Propaganda Movement",
            subtitle = "The peaceful cultural and political campaign for Philippine reforms in Madrid and Barcelona",
            era = HistoricalEra.SPANISH_ERA,
            dateOrYear = "1880 – 1895",
            location = "Spain (Madrid, Barcelona) & Philippines",
            keyPeople = listOf("Marcelo H. del Pilar", "Graciano López Jaena", "Dr. José Rizal", "Mariano Ponce", "Juan Luna", "Felix Resurrección Hidalgo"),
            category = "Movement",
            shortSummary = "A literary, journalistic, and intellectual movement led by Filipino expatriate students ('Ilustrados') demanding political representation, freedom of speech, and civil equality under Spanish law.",
            historicalBackground = "The opening of the Suez Canal in 1869 enabled wealthier Filipino families to send their sons to European universities. Shocked by the contrast between progressive European liberty and backward feudal abuses in their homeland, these young intellectuals launched a concerted campaign.",
            keyEventsAndDetails = "In 1889, Graciano López Jaena founded the fortnightly newspaper 'La Solidaridad' in Barcelona, later edited by Marcelo H. del Pilar (Plaridel). They advocated for: Philippine representation in the Spanish Cortes, secularization of parishes, freedom of assembly, and an end to arbitrary deportations. Concurrently, painters Juan Luna and Felix Resurrección Hidalgo won top gold and silver medals at the 1884 Madrid National Exposition of Fine Arts for the 'Spoliarium' and 'Las Virgenes Cristianas Expuestas al Populacho'.",
            importanceAndImpact = "Though Spain ignored their reform demands, the movement created the intellectual and ideological foundation for a shared national consciousness that birthed the Katipunan and 1896 Revolution.",
            primarySourcesAndFacts = listOf(
                "Rizal's banquet speech celebrating Luna and Hidalgo in 1884 proclaimed: 'Genius knows no country; genius sprouts everywhere; genius is like light and air, the patrimony of all.'",
                "Del Pilar died of tuberculosis in extreme poverty in Barcelona in 1896, having sacrificed his personal fortune for La Solidaridad."
            ),
            connectedLinks = listOf(
                ConnectedLink("jose-rizal", "Dr. José Rizal", "Prominent Intellectual Leader", HistoricalEra.SPANISH_ERA, "JR"),
                ConnectedLink("gomburza-1872", "Execution of Gomburza", "Original Catalyzing Outrage", HistoricalEra.SPANISH_ERA, "GB"),
                ConnectedLink("katipunan", "The Katipunan (KKK)", "Transition from Words to Arms", HistoricalEra.PHILIPPINE_REVOLUTION, "KK")
            ),
            badgeCode = "PM",
            badgeColorHex = 0xFF0038A8
        ),

        HistoryTopic(
            id = "precolonial-barangay",
            title = "Pre-colonial Barangay & Culture",
            subtitle = "The rich indigenous social, political, and economic systems of ancient Philippines",
            era = HistoricalEra.PRE_COLONIAL,
            dateOrYear = "Before 1521",
            location = "Luzon, Visayas, Mindanao archipelagic settlements",
            keyPeople = listOf("Datu / Rajah (political rulers)", "Babaylan / Katalonan (spiritual leaders)", "Maharlika / Timawa (freemen)", "Alipin (dependents)"),
            category = "Ancient Civilization",
            shortSummary = "Autonomous coastal and riverine settlements governed by custom laws, flourishing seafaring trade with China, Siam, and Arabia, and indigenous spirituality led by female Babaylans.",
            historicalBackground = "The name 'barangay' derives from 'balangay', the wooden ocean-going outrigger boats used by Austronesian voyagers who populated the archipelago over thousands of years.",
            keyEventsAndDetails = "Barangays ranged in size from 30 to over a thousand households. Leaders were guided by council elders and customary oral/written laws. Women enjoyed equal property rights, could divorce, head barangays, and acted as spiritual mediators (Babaylan). Metallurgy, goldsmithing, weaving (Inabel, T'nalak), and shipbuilding (karakoa war vessels) were highly developed.",
            importanceAndImpact = "Demonstrates the sophisticated, egalitarian, and self-sufficient civilization that existed long before foreign contact, forming the deep cultural bedrock of Filipino identity.",
            primarySourcesAndFacts = listOf(
                "The Boxer Codex (circa 1590) contains 75 colored illustrations depicting pre-colonial Filipinos adorned in elaborate gold jewelry and fine silks.",
                "Ancient Filipinos used the Baybayin script, an alphasyllabary consisting of 14 consonants and 3 vowels, inscribed on bamboo and palm leaves."
            ),
            connectedLinks = listOf(
                ConnectedLink("laguna-copperplate", "Laguna Copperplate (900 AD)", "Oldest Legal Inscription", HistoricalEra.PRE_COLONIAL, "LC"),
                ConnectedLink("battle-of-mactan", "Battle of Mactan (1521)", "Defense of Sovereign Autonomy", HistoricalEra.SPANISH_ERA, "BM"),
                ConnectedLink("sultanate-of-sulu", "The Sultanate of Sulu", "Centralized Islamic State", HistoricalEra.PRE_COLONIAL, "SS")
            ),
            badgeCode = "PC",
            badgeColorHex = 0xFFE5B800
        )
    )

    fun getTopicById(id: String): HistoryTopic? {
        return topics.find { it.id.equals(id, ignoreCase = true) }
    }

    fun searchTopics(query: String): List<HistoryTopic> {
        val cleanQuery = query.trim().lowercase()
        if (cleanQuery.isEmpty()) return topics
        return topics.filter { topic ->
            topic.title.lowercase().contains(cleanQuery) ||
            topic.subtitle.lowercase().contains(cleanQuery) ||
            topic.shortSummary.lowercase().contains(cleanQuery) ||
            topic.keyPeople.any { it.lowercase().contains(cleanQuery) } ||
            topic.location.lowercase().contains(cleanQuery) ||
            topic.era.title.lowercase().contains(cleanQuery) ||
            topic.category.lowercase().contains(cleanQuery)
        }
    }

    fun getTopicsByEra(era: HistoricalEra): List<HistoryTopic> {
        return topics.filter { it.era == era }
    }

    fun getRecommendedTopics(currentTopicId: String?): List<HistoryTopic> {
        if (currentTopicId == null) {
            return topics.take(4)
        }
        val current = getTopicById(currentTopicId) ?: return topics.take(4)
        val linkedIds = current.connectedLinks.map { it.targetId }
        val linkedTopics = topics.filter { it.id in linkedIds }
        val remaining = topics.filter { it.id != currentTopicId && it.id !in linkedIds }
        return (linkedTopics + remaining).take(5)
    }
}
