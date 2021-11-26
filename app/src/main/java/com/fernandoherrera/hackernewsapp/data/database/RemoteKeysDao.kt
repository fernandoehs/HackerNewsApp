package com.fernandoherrera.hackernewsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fernandoherrera.hackernewsapp.data.model.local.RemoteKeysEntity

@Dao
interface RemoteKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysEntity>)

    @Query("SELECT * FROM Remote_Keys WHERE hitObjectId = :hitObjectId")
    suspend fun remoteKeysRepoId(hitObjectId: String): RemoteKeysEntity?

    @Query("DELETE FROM Remote_Keys")
    suspend fun clearRemoteKeys()

    @Query("DELETE FROM Remote_Keys WHERE hitObjectId = :hitObjectId")
    suspend fun deleteRemoteKeyById(hitObjectId: String): Int
}
