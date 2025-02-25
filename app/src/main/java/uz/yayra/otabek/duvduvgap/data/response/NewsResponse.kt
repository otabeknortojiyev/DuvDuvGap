package uz.yayra.otabek.duvduvgap.data.response

import com.google.gson.annotations.SerializedName

data class NewsResponseEnglish(
    val status: Int,
    @SerializedName("Latest")
    val latest: List<Article>?,
    @SerializedName("Weekend reads")
    val weekendReads: List<Article>?,
    @SerializedName("UEFA Nations League")
    val uEFANationsLeague: List<Article>?,
    @SerializedName("Arts in Motion")
    val artsInMotion: List<Article>?,
    @SerializedName("Must watch")
    val mustWatch: List<Article>?,
    @SerializedName("Podcasts")
    val podcasts: List<Article>?,
    @SerializedName("Culture")
    val culture: List<Article>?,
    @SerializedName("Science and health")
    val scienceAndHealth: List<Article>?,
    @SerializedName("US election")
    val usElection: List<Article>?,
    @SerializedName("News")
    val news: List<Article>?,
    @SerializedName("Sport")
    val sport: List<Article>?,
    @SerializedName("Business")
    val business: List<Article>?,
    @SerializedName("Innovation")
    val innovation: List<Article>?,
    @SerializedName("Featured video")
    val featuredVideo: List<Article>?,
    @SerializedName("Travel")
    val travel: List<Article>?,
    @SerializedName("Earth")
    val earth: List<Article>?,
    @SerializedName("World of Wonder")
    val worldOfWonder: List<Article>?,
    @SerializedName("Latest on the US election")
    val latestOnTheUSElection: List<Article>?,
    @SerializedName("Latest on the Israel-Gaza war")
    val latestOnTheIsraelGazaWar: List<Article>?,
    @SerializedName("Latest on the Ukraine war")
    val latestOnTheUkraineWar: List<Article>?,
    @SerializedName("BBC InDepth")
    val bbcInDepth: List<Article>?,
    @SerializedName("elapsed time")
    val elapsedTime: String,
    val timestamp: Long,
)

data class Article(
    val title: String,
    val summary: String?,
    @SerializedName("image_link")
    val imageLink: String?,
    @SerializedName("news_link")
    val newsLink: String,
)