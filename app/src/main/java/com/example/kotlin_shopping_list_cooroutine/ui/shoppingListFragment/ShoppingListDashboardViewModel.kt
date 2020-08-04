package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListsEntity
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ShoppingListDashboardViewModel(
    private val shoppingListRepository: ShoppingListRepository
): ViewModel() {

    var list: List<ListModel> = listOf()

/*    val shoppingLists = liveData(Dispatchers.IO){
        list = shoppingListRepository.getAllList()
        emit(list)
    }*/

    fun getLists(): LiveData<List<ShoppingListsEntity>> {
        return shoppingListRepository.getAllList()
    }

    fun createList(listName: String) {
        GlobalScope.launch{
            shoppingListRepository.createList(listName)
        }
    }

    fun isValidName(name: String): Boolean = list.find { it.listName == name } == null

    fun delete(id: UUID) {
        GlobalScope.launch{
            shoppingListRepository.delete(id)
        }
    }

    fun deleteAll() {
        GlobalScope.launch {
            shoppingListRepository.deleteAll()
        }
    }
}