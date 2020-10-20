package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.ui.BaseFragment
import kotlinx.android.synthetic.main.fragment_shopping_list_item.*

class ShoppingListFragment: BaseFragment<ShoppingListViewModel, ShoppingListFactory>(ShoppingListFactory(), ShoppingListViewModel::class.java) {

    companion object {
        const val ID_KEY ="ID"
    }

    private var id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let{
            id = it.getString(ID_KEY,"")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* shoppingItemTabLayout.
        viewModel?.getElements(id)?.observe(viewLifecycleOwner, Observer {

        })*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(getString(R.string.list_item_title))
    }

}