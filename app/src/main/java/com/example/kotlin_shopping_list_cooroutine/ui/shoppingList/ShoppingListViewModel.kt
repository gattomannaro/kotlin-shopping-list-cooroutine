package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository

class ShoppingListViewModel(
    private val shoppingListRepository: ShoppingListRepository
): ViewModel() {

    fun getElements(id: String): LiveData<List<ShoppingListItem>> {
        return shoppingListRepository.getElements(id)
    }
}