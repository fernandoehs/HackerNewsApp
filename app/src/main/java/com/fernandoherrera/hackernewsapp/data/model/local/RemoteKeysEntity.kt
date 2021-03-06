package com.fernandoherrera.hackernewsapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Remote_Keys")
data class RemoteKeysEntity(
    @PrimaryKey
    val hitObjectId: String,
    val nextKey: Int?
)
