package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_shopping_list_cooroutine.data.model.ListItem
import com.example.kotlin_shopping_list_cooroutine.data.model.Vegetable
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListViewModel(
    private val shoppingListRepository: ShoppingListRepository
) : ViewModel() {

    var listId: String? = null
    val items: MutableLiveData<MutableList<ListItem>> = MutableLiveData()
    val vegetables: MutableLiveData<List<Vegetable>> = MutableLiveData()

    fun getElements(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                items.postValue(
                    shoppingListRepository.getElements(id)
                        .map { ListItem(UUID.fromString(it.id), it.name, it.listId) }
                        .toMutableList()
                )
            } catch (e: Exception) {
                Log.e("ShoppingListViewModel#getElements: $id", e.message ?: "")
            }
        }
    }

    fun isValidName(name: String): Boolean =
        items.value?.find { it.name == name.capitalize(Locale.getDefault()) } == null

    fun addElement(name: String) {
        val l = items.value
        val model = ListItem(name = name, listId = listId ?: "")
        l?.add(model)
        items.value = l
        viewModelScope.launch(Dispatchers.IO) {
            try {
                shoppingListRepository.addElement(model.toEntity())
            } catch (e: Exception) {
                Log.e("ShoppingListViewModel#addElement", e.message ?: "")
            }
        }
    }

    fun delete(id: UUID) {
        items.value?.removeIf { it.id == id }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                shoppingListRepository.delete(id)
            } catch (e: Exception) {
                Log.e("ShoppingListViewModel#deleteAll", e.message ?: "")
            }
        }
    }

    fun deleteAll() {
        items.value = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                shoppingListRepository.deleteAll(listId ?: "")
            } catch (e: Exception) {
                Log.e("ShoppingListViewModel#deleteAll", e.message ?: "")
            }
        }
    }

    fun getVegetables() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentMonth = SimpleDateFormat("MMMM", Locale.getDefault()).format(Date())
                vegetables.postValue(shoppingListRepository.getVegetables("%$currentMonth%"))
            } catch (e: Exception) {
                Log.e("ShoppingListViewModel#getVegetables", e.message ?: "")
            }
        }
    }

    fun getListString(): String {
        return items.value?.let{
            it.fold("", { acc, listItem ->
            acc + "${listItem.name}\n"
        })
        }?:run{
            ""
        }
    }
}