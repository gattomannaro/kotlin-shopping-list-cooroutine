package com.example.kotlin_shopping_list_cooroutine.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem

@Dao
interface ShoppingListItemDao {
    @Query("SELECT * FROM ShoppingListItem WHERE listId = :listId")
    suspend fun getItems(listId: String): List<ShoppingListItem>

    @Insert
    suspend fun addElement(list: ShoppingListItem)

    @Query("DELETE FROM ShoppingListItem WHERE id = :id")
    suspend fun delete(id: String)

    @Query("DELETE FROM ShoppingListItem")
    suspend fun deleteAll()
}