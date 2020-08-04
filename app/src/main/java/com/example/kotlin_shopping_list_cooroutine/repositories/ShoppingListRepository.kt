package com.example.kotlin_shopping_list_cooroutine.repositories

import androidx.lifecycle.LiveData
import com.example.kotlin_shopping_list_cooroutine.data.db.Database
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import java.util.*

class ShoppingListRepository(
    private val db: Database
) {
    suspend fun createList(listName: String) {
        val model = ListModel(listName = listName)
        db.shoppingListsDao().insertList(model.toEntity())
    }

    fun getAllList(): LiveData<List<ShoppingListsEntity>> {
        return db.shoppingListsDao().getAllList()
    }

    suspend fun delete(id: UUID) {
        return db.shoppingListsDao().delete(id.toString())
    }

    fun deleteAll() {
        return db.clearAllTables()
    }
}