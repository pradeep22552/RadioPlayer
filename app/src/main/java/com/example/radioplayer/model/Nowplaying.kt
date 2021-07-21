package com.example.radioplayer.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity
data class Nowplaying (
    @NonNull
    @PrimaryKey
 val sid: String,
val album: String?,
  val name: String?,
 val artist: String?,
   val image_url: String?,
  val link_url: String?,
    val preview_url: String?,
 val played_at: String?

)