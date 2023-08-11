package com.example.artbook.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artbook.data.model.roomdb.Art

@Database(entities = [Art::class], version = 1)
abstract class ArtDatabase : RoomDatabase() {

    abstract fun artDao(): ArtDao
}
