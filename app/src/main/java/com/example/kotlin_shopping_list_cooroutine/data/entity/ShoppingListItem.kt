package com.example.kotlin_shopping_list_cooroutine.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
class ShoppingListItem(
    @PrimaryKey val id: String,
    @ColumnInfo val name: String,
    @ColumnInfo val listId: String
)