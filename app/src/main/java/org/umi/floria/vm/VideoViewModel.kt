package org.umi.floria.vm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.umi.floria.models.VideoData
import org.umi.floria.models.YouTubeVideoData

class VideoViewModel : ViewModel() {

    suspend fun fetchVideoData(links: List<String>): List<VideoData> {
        return withContext(Dispatchers.Default) {
            val videoDataList = mutableListOf<VideoData>()

            for (link in links) {
                val youTubeVideoData = YouTubeVideoData(link)

                val videoModel = VideoData(
                    youTubeVideoData.getTitle(),
                    youTubeVideoData.getThumbnails(),
                    youTubeVideoData.getChannelName()
                )
                videoDataList.add(videoModel)
            }

            return@withContext videoDataList
        }
    }
}



