package com.example.kotlin_shopping_list_cooroutine.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.kotlin_shopping_list_cooroutine.activities.IMainActivity

abstract class BaseFragment<T: ViewModel, R: ViewModelProvider.Factory>(
    private val factory: R,
    private val viewModelClass: Class<T>):Fragment() {

    lateinit var navController: NavController
    var viewModel: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navController = findNavController()
        viewModel = ViewModelProvider(this, factory).get(viewModelClass)
    }

    fun setToolbarTitle(title: String) {
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).setToolbarTitle(title)
        }
    }

    fun setToolbarVisibility(visibility: Int) {
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).setToolbarVisibility(visibility)
        }
    }

    fun hideToolbarMenu() {
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).hideToolbarMenu()
        }
    }

    fun showToolbarMenu(listener: (()-> Unit)) {
        if(requireActivity() is IMainActivity) {
            (requireActivity() as IMainActivity).showToolbarMenu(listener)
        }
    }
}