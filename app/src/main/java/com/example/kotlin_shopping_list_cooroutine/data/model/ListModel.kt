package com.example.kotlin_shopping_list_cooroutine.data.model

import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity
import java.util.*

data class ListModel(
    val id: UUID = UUID.randomUUID(),
    val listName: String
) {
    fun toEntity(): ShoppingListsEntity = ShoppingListsEntity(id.toString(), listName)
}