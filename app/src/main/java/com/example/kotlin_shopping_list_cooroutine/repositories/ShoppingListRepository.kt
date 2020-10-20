package com.example.kotlin_shopping_list_cooroutine.repositories

import androidx.lifecycle.LiveData
import com.example.kotlin_shopping_list_cooroutine.data.db.Database
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem

class ShoppingListRepository(
    private val db: Database
) {

    fun getElements(id: String): LiveData<List<ShoppingListItem>> {
        return db.shoppingListItemDao().getItems(id)
    }
}