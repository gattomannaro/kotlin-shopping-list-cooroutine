package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListFragment

import androidx.lifecycle.ViewModel
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository

class ShoppingListDashboardViewModel(
    val shoppingListRepository: ShoppingListRepository
): ViewModel() {

}