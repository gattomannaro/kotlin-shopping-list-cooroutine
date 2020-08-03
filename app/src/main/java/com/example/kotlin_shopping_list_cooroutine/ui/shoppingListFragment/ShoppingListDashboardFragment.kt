package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_shopping_list_cooroutine.ui.BaseFragment
import org.koin.core.context.KoinContextHandler.get

class ShoppingListDashboardFragment: BaseFragment<ShoppingListDashboardViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ShoppingListDashboardViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}