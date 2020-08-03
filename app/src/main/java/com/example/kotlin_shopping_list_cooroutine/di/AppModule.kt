package com.example.kotlin_shopping_list_cooroutine.di

import androidx.room.Room
import com.example.kotlin_shopping_list_cooroutine.data.db.Database
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val AppModule =  module{
    single {
        val dbName = "shoppingdb.db"

        Room.databaseBuilder(
            androidContext().applicationContext,
            Database::class.java,
            dbName
        )
            .createFromAsset(dbName)
            .build()
    }
}