package com.example.kotlin_shopping_list_cooroutine.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {}