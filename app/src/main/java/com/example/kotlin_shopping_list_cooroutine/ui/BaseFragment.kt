package com.example.kotlin_shopping_list_cooroutine.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kotlin_shopping_list_cooroutine.activities.IMainActivity

abstract class BaseFragment<T>: Fragment() {

    lateinit var navController: NavController
    var viewModel: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController()
    }

    fun setToolbarTitle(title: String) {
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).setToolbarTitle(title)
        }
    }

    fun toolbarVisibility(visibility: Int) {
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).setToolbarVisibility(visibility)
        }
    }
}