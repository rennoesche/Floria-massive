package org.umi.floria.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request

class YouTubeVideoData(private val link: String) {
    private val client = OkHttpClient()

    suspend fun getTitle(): String? {
        return try {
            val videoDetails = getVideoDetails()
            videoDetails?.title
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getThumbnails(): String? {
        return try {
            val videoDetails = getVideoDetails()
            videoDetails?.thumbnails?.url
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getChannelName(): String? {
        return try {
            val videoDetails = getVideoDetails()
            videoDetails?.channelName
        } catch (e: Exception) {
            null
        }
    }

    private suspend fun getVideoDetails(): VideoDetails? {
        val request = Request.Builder()
            .url(link)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64)")
            .build()

        val response = client.newCall(request).execute()
        val html = response.body?.string()

        val videoDetails = try {
            val json = Json { ignoreUnknownKeys = true }
            json.decodeFromString<VideoDetails>(html!!)
        } catch (e: Exception) {
            null
        }

        return videoDetails
    }

    @Serializable
    private data class Thumbnails(val url: String)

    @Serializable
    private data class VideoDetails(val title: String, val thumbnails: Thumbnails, val channelName: String)
}
