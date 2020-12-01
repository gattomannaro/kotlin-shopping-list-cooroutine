package com.example.kotlin_shopping_list_cooroutine.activities

import android.view.View

interface IMainActivity {
    fun setToolbarTitle(text: String)
    fun setToolbarVisibility(visibility: Int)
    fun showToolbarMenu(listener: (()->Unit))
    fun hideToolbarMenu()
}