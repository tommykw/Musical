package com.github.tommykw.musical.data.entity

import com.google.android.exoplayer2.C
import java.io.Serializable

data class VideoPlayerState(
    var playWhenReady: Boolean = true,
    var currentWindow: Int = C.INDEX_UNSET,
    var playBackPosition: Long = 0,
    var videoUrl: String? = null
) : Serializable {

    fun setVideo(url: String): VideoPlayerState {
        return if (videoUrl == null) {
            this
        } else {
            this.apply {
                videoUrl = url
                playBackPosition = 0
            }
        }
    }
}