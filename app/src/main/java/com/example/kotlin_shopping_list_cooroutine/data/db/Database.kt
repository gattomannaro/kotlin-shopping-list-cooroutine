package com.example.kotlin_shopping_list_cooroutine.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlin_shopping_list_cooroutine.data.dao.ShoppingListsDao
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity

@Database(
    entities = [ShoppingListsEntity::class],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun shoppingListsDao(): ShoppingListsDao
}