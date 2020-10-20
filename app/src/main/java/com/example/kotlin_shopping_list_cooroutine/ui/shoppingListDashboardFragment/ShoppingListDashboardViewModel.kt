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

    var list: MutableLiveData<List<ListModel>> = MutableLiveData()
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
        viewModelScope.launch(Dispatchers.IO) {
            val l = list.value?.toMutableList()
            val model = ListModel(listName = listName)
            shoppingDashboardRepository.createList(model.toEntity())
            l?.add(model)
            list.postValue(l)
        }
    }

    fun isValidName(name: String): Boolean = list.value?.find { it.listName == name.capitalize(
        Locale.getDefault())} == null

    fun delete(id: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            val l = list.value?.toMutableList()
            shoppingDashboardRepository.delete(id)
            l?.removeIf { it.id == id }
            list.postValue(l)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            list.postValue(listOf())
            shoppingDashboardRepository.deleteAll()
        }
    }
}