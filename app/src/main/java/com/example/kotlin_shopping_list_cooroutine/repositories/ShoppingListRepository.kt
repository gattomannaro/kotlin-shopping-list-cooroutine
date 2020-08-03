package com.example.kotlin_shopping_list_cooroutine.repositories

import com.example.kotlin_shopping_list_cooroutine.data.db.Database
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import java.util.*

class ShoppingListRepository(
    val db: Database
) {
    suspend fun createList(listName: String) {
        val model = ListModel(listName = listName)
        db.shoppingListsDao().insertList(model.toEntity())
    }

    suspend fun getAllList(): List<ListModel> {
        return db.shoppingListsDao().getAllList().map { ListModel(UUID.fromString(it.id), it.listName) }
    }
}