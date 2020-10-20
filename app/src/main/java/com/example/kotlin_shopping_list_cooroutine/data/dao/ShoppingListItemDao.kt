package com.example.kotlin_shopping_list_cooroutine.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem

@Dao
interface ShoppingListItemDao {
    @Query("SELECT * FROM ShoppingListItem WHERE listId = :listId")
    fun getItems(listId: String): LiveData<List<ShoppingListItem>>
}