package com.example.radioplayer.database

import androidx.room.*
import com.example.radioplayer.model.Nowplaying

import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDAO {

    @Query("SELECT * FROM Nowplaying")
    fun getAll(): List<Nowplaying>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<Nowplaying>)

    @Delete
    fun delete(movie: Nowplaying)

    @Delete
    fun deleteAll(movie: List<Nowplaying>)
}
