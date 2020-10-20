package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.koin.core.KoinComponent
import org.koin.core.get

@Suppress("UNCHECKED_CAST")
class ShoppingListFactory: ViewModelProvider.Factory, KoinComponent {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(get()) as T
    }
}