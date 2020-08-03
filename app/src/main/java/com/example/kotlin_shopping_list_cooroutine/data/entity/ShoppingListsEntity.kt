package com.example.kotlin_shopping_list_cooroutine.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ShoppingListsEntity(
    @PrimaryKey val id: String,
    @ColumnInfo val listName: String
)