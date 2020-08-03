package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ShoppingListDashboardViewModel(
    private val shoppingListRepository: ShoppingListRepository
): ViewModel() {

    val shoppingLists = liveData(Dispatchers.IO){
        emit(shoppingListRepository.getAllList())
    }

    fun createList(listName: String) {
        GlobalScope.launch{
            shoppingListRepository.createList(listName)
        }
    }

}