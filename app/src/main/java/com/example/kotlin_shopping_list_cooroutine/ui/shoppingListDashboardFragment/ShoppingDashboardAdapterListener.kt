package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListDashboardFragment

import java.util.*

interface ShoppingDashboardAdapterListener{
    fun delete(id: UUID)

    fun onItemClick(id: UUID)
}