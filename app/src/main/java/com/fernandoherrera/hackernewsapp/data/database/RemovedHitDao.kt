package com.fernandoherrera.hackernewsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernandoherrera.hackernewsapp.data.model.local.RemovedHitEntity


@Dao
interface RemovedHitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(removedHit: RemovedHitEntity)

    @Query("SELECT * FROM Removed_Hit")
    suspend fun removedHits(): List<RemovedHitEntity>
}
