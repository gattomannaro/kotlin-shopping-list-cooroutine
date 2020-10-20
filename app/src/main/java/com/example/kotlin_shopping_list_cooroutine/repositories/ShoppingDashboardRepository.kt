package com.example.kotlin_shopping_list_cooroutine.repositories

import androidx.lifecycle.LiveData
import com.example.kotlin_shopping_list_cooroutine.data.db.Database
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import java.util.*

class ShoppingDashboardRepository(
    private val db: Database
) {
    suspend fun createList(model: ShoppingListsEntity) {

        db.shoppingListsDashboardDao().insertList(model)
    }

    suspend fun getAllList(): List<ShoppingListsEntity> {
        return db.shoppingListsDashboardDao().getAllList()
    }

    suspend fun delete(id: UUID) {
        return db.shoppingListsDashboardDao().delete(id.toString())
    }

    suspend fun deleteAll() {
        return db.clearAllTables()
    }
}