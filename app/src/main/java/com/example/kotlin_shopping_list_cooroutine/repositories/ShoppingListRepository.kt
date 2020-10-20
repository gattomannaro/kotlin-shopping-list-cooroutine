package com.example.kotlin_shopping_list_cooroutine.repositories

import com.example.kotlin_shopping_list_cooroutine.data.db.Database
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem
import java.util.*

class ShoppingListRepository(
    private val db: Database
) {

    suspend fun getElements(id: String): List<ShoppingListItem> {
        return db.shoppingListItemDao().getItems(id)
    }

    suspend fun addElement(model: ShoppingListItem) {
        db.shoppingListItemDao().addElement(model)
    }

    suspend fun delete(id: UUID) {
        db.shoppingListItemDao().delete(id.toString())
    }

    suspend fun deleteAll() {
        db.shoppingListItemDao().deleteAll()
    }
}