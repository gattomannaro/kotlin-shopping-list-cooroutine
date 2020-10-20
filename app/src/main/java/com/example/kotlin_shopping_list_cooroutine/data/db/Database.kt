package com.example.kotlin_shopping_list_cooroutine.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.kotlin_shopping_list_cooroutine.data.dao.ShoppingListItemDao
import com.example.kotlin_shopping_list_cooroutine.data.dao.ShoppingListsDashboardDao
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity

@Database(
    entities = [ShoppingListsEntity::class, ShoppingListItem::class],
    version = 2,
    exportSchema = true
)
abstract class Database : RoomDatabase() {

    abstract fun shoppingListsDashboardDao(): ShoppingListsDashboardDao
    abstract fun shoppingListItemDao(): ShoppingListItemDao

    companion object {
        @JvmField
        val MIGRATION_0_1: Migration = object : Migration(0, 1) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //Migration tecnica per far si che Room setti il DB pre popolato come un DB room
            }
        }

        val MIGRATION_1_2: Migration = object : Migration(1,2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //Migration tecnica per far si che Room setti il DB pre popolato come un DB room
            }
        }
    }
}