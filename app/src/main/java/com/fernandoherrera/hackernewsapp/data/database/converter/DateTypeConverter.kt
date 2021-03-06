package com.fernandoherrera.hackernewsapp.data.database.converter

import androidx.room.TypeConverter
import org.joda.time.DateTime

class DateTypeConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): DateTime? {
        return value?.let { DateTime(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: DateTime?): Long? {
        return date?.millis
    }
}
