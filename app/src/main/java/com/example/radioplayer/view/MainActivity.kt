package com.example.radioplayer.view

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.radioplayer.R
import com.example.radioplayer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer
    var playstop:Boolean=false
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.fragNavHost)

        //Setting the navigation controller to Bottom Nav
        binding.bottomNav.setupWithNavController(navController)


        //Setting up the action bar
        NavigationUI.setupActionBarWithNavController(this, navController)

        binding.btnPlaystop.setOnClickListener(View.OnClickListener {
            if (playstop) {
                playstop = false
                binding.btnPlaystop.text = "play"
                mediaPlayer.stop()
                mediaPlayer.reset()
                mediaPlayer.release()

            } else {
                playstop = true
                binding.btnPlaystop.text = "stop"
                val audioUrl = "https://rfcmedia.streamguys1.com/70hits.aac"
                mediaPlayer = MediaPlayer()
                mediaPlayer.setAudioAttributes(
                        AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .build())
                try {
                    mediaPlayer.setDataSource(audioUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }


        })
    }


    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

}