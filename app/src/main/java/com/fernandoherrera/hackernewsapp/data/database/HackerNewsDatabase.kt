package com.fernandoherrera.hackernewsapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.fernandoherrera.hackernewsapp.data.database.converter.DateTypeConverter
import com.fernandoherrera.hackernewsapp.data.model.local.HitEntity
import com.fernandoherrera.hackernewsapp.data.model.local.RemoteKeysEntity
import com.fernandoherrera.hackernewsapp.data.model.local.RemovedHitEntity
import com.fernandoherrera.hackernewsapp.utils.DATABASE_NAME

@Database(
    entities = [HitEntity::class, RemoteKeysEntity::class, RemovedHitEntity::class], version = 1, exportSchema = false
)
@TypeConverters(DateTypeConverter::class)
abstract class HackerNewsDatabase : RoomDatabase() {
    abstract fun hackerNewsDao(): HackerNewsDao
    abstract fun remoteKeysDao(): RemoteKeysDao
    abstract fun removedHitDao(): RemovedHitDao

    companion object {
        @Volatile
        private var INSTANCE: HackerNewsDatabase? = null

        fun getInstance(context: Context): HackerNewsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HackerNewsDatabase::class.java, DATABASE_NAME
            ).build()
    }
}
