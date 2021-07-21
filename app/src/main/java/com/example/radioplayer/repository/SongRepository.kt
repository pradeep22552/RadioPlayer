package com.example.radioplayer.repository

import com.example.radioplayer.database.SongsDAO
import com.example.radioplayer.model.Nowplaying
import com.example.radioplayer.utils.Resultres
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

import javax.inject.Inject

/**
 * Repository which fetches data from Remote or Local data sources
 */


class SongRepository  @Inject constructor(

    private val songsDao: SongsDAO,


) {
    suspend fun fetchsongs(): Flow<Resultres<List<Nowplaying>>?> {
        return flow {
            emit(fetchTrendingSongsCached())
            emit(Resultres.loading())

            val result = NowplayingRepository.getServicesApiCall()


            //Cache to database if response is successful
            if (result.status == Resultres.Status.SUCCESS) {
                result.data?.let { it ->
                    songsDao.deleteAll(it)
                    songsDao.insertAll(it)
                }
            }
            emit(result)

        }.flowOn(Dispatchers.IO)
    }

    private fun fetchTrendingSongsCached(): Resultres<List<Nowplaying>>? =
        songsDao.getAll()?.let {
            Resultres.success(it)
        }


}