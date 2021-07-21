package com.example.radioplayer.database


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.radioplayer.model.Nowplaying


@Database(entities = [Nowplaying::class], version = 1)
/*@TypeConverters(GenreConverters::class)*/
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): SongsDAO
}