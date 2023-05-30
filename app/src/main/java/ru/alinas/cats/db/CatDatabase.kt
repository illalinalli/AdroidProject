package ru.alinas.cats.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CatEntity::class], version = 1)
abstract class CatDatabase : RoomDatabase() {
    abstract fun catDao(): CatDao
}