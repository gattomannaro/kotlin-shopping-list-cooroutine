package com.example.kotlin_shopping_list_cooroutine.data.model

import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem
import java.util.*

data class ListItem(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val listId: String
) {
    fun toEntity(): ShoppingListItem = ShoppingListItem(id.toString(), name, listId)
}