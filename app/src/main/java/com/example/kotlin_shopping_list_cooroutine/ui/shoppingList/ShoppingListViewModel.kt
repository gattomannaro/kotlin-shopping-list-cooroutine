package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_shopping_list_cooroutine.data.entity.ShoppingListItem
import com.example.kotlin_shopping_list_cooroutine.data.model.ListItem
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class ShoppingListViewModel(
    private val shoppingListRepository: ShoppingListRepository
): ViewModel() {

    var listId: String? = null
    val items: MutableLiveData<MutableList<ListItem>> = MutableLiveData()

    fun getElements(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                items.postValue(shoppingListRepository.getElements(id).map { ListItem(UUID.fromString(it.id), it.name, it.listId) }.toMutableList())
            } catch (e: Exception) {
                Log.e("ShoppingListViewModel#getElements: $id", e.message ?: "")
            }
        }
    }

    fun isValidName(name: String): Boolean = items.value?.find { it.name == name.capitalize(Locale.getDefault())} == null

    fun addElement(name: String) {
        val l = items.value
        val model = ListItem(name = name, listId = listId?:"")
        l?.add(model)
        items.value = l
        viewModelScope.launch(Dispatchers.IO){
            try {
                shoppingListRepository.addElement(model.toEntity())
            }catch (e: Exception) {
                Log.e("ShoppingListViewModel#addElement", e.message?:"")
            }
        }
    }

    fun delete(id: UUID) {
        items.value?.removeIf { it.id == id }
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListRepository.delete(id)
        }
    }

    fun deleteAll() {
        items.value = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            shoppingListRepository.deleteAll(listId?:"")
        }
    }
}