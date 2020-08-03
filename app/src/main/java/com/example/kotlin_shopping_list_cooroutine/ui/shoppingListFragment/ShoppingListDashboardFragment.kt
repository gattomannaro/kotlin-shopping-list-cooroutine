package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.ext.disable
import com.example.kotlin_shopping_list_cooroutine.ext.enable
import com.example.kotlin_shopping_list_cooroutine.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_shopping_dashboard.*

class ShoppingListDashboardFragment :
    BaseFragment<ShoppingListDashboardViewModel, ShoppingListDashboardFactory>(
        ShoppingListDashboardFactory(),
        ShoppingListDashboardViewModel::class.java
    ) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.shoppingLists?.observe(viewLifecycleOwner, Observer {
            print(it.toString())
        })

        shoppingListDashBtnCreate.disable()
        shoppingListDashNewNameEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isBlank())
                    shoppingListDashBtnCreate.disable()
                else
                    shoppingListDashBtnCreate.enable()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // not needed
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // not needed
            }
        })

        shoppingListDashBtnCreate.setOnClickListener {
            viewModel?.createList(shoppingListDashNewNameEdt.text.toString())
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarVisibility(View.VISIBLE)
        setToolbarTitle(getString(R.string.ShoppingListDashboardFragment_title))
    }

}