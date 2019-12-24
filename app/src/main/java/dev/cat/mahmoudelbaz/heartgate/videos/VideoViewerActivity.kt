package dev.cat.mahmoudelbaz.heartgate.videos

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.MediaController
import android.widget.VideoView
import dev.cat.mahmoudelbaz.heartgate.R

class VideoViewerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_viewer)
        val videoView = findViewById<VideoView>(R.id.videoView)
        val uriPath = intent.getStringExtra("videoUri")
        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        // val uriPath = "https://www.demonuts.com/Demonuts/smallvideo.mp4"
        val uri = Uri.parse(uriPath)
        videoView.setMediaController(mediaController)
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()
        videoView.setOnPreparedListener {
            val progressBar = findViewById<View>(R.id.progressBar)
            progressBar.visibility = View.GONE

        }

    }

}