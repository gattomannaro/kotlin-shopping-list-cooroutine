package com.example.kotlin_shopping_list_cooroutine.di

import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingDashboardRepository
import com.example.kotlin_shopping_list_cooroutine.repositories.ShoppingListRepository
import org.koin.dsl.module

val RepoModule = module {
    single{
        ShoppingDashboardRepository(get())
    }

    single{
        ShoppingListRepository(get())
    }
}