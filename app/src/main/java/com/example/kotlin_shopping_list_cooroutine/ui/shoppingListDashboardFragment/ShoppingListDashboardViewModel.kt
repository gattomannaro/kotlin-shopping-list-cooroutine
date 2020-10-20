package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListDashboardFragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingDashboardRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class ShoppingListDashboardViewModel(
    private val shoppingDashboardRepository: ShoppingDashboardRepository
): ViewModel() {

    var list: MutableLiveData<MutableList<ListModel>> = MutableLiveData()
    fun getLists(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val l = shoppingDashboardRepository.getAllList()
                list.postValue(l.map { item -> ListModel(UUID.fromString(item.id), item.listName) }.toMutableList())
            } catch (e: Exception) {
                Log.e("ShoppingListDashboardViewModel#getLists", e.message?:"")
            }
        }
    }

    fun createList(listName: String) {
        val l = list.value
        val model = ListModel(listName = listName)
        l?.add(model)
        list.value = l
        viewModelScope.launch(Dispatchers.IO) {
            shoppingDashboardRepository.createList(model.toEntity())
        }
    }

    fun isValidName(name: String): Boolean = list.value?.find { it.listName == name.capitalize(
        Locale.getDefault())} == null

    fun delete(id: UUID) {
        list.value?.removeIf { it.id == id }
        viewModelScope.launch(Dispatchers.IO) {
            shoppingDashboardRepository.delete(id)
        }
    }

    fun deleteAll() {
        list.value = mutableListOf()
        viewModelScope.launch(Dispatchers.IO) {
            shoppingDashboardRepository.deleteAll()
        }
    }
}