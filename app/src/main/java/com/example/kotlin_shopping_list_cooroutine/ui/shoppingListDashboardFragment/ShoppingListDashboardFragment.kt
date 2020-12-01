package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListDashboardFragment

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.ext.afterTextChanged
import com.example.kotlin_shopping_list_cooroutine.ext.disable
import com.example.kotlin_shopping_list_cooroutine.ext.enable
import com.example.kotlin_shopping_list_cooroutine.ext.showDialog
import com.example.kotlin_shopping_list_cooroutine.ui.BaseFragment
import com.example.kotlin_shopping_list_cooroutine.ui.shoppingList.ShoppingListFragment.Companion.ID_KEY
import kotlinx.android.synthetic.main.fragment_shopping_dashboard.*
import java.util.*

@ExperimentalStdlibApi
class ShoppingListDashboardFragment :
    BaseFragment<ShoppingListDashboardViewModel, ShoppingListDashboardFactory>(
        ShoppingListDashboardFactory(),
        ShoppingListDashboardViewModel::class.java
    ), ShoppingDashboardAdapterListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel?.getLists()

        shoppingListDashBtnCreate.disable()
        shoppingListDashNewNameEdt.afterTextChanged {
            if (it.isBlank() || viewModel?.isValidName(it) == false)
                shoppingListDashBtnCreate.disable()
            else
                shoppingListDashBtnCreate.enable()
        }

        shoppingListDashBtnCreate.setOnClickListener {
            viewModel?.createList(
                shoppingListDashNewNameEdt.text.toString().capitalize(Locale.ROOT)
            )
            shoppingListDashNewNameEdt.setText("")
        }

        shoppingListDashDeleteAll.setOnClickListener {
            showDialog(
                getString(R.string.delete_all_title),
                getString(R.string.delete_all_message),
                getString(R.string.confirm),
                { _, _ ->
                    viewModel?.deleteAll()
                },
                getString(R.string.cancel),
                { dialogInterface: DialogInterface, _: Int ->
                    dialogInterface.dismiss()
                }
            )
        }

        attachObserver()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarVisibility(View.VISIBLE)
        setToolbarTitle(getString(R.string.ShoppingListDashboardFragment_title))
        hideToolbarMenu()
    }

    override fun delete(id: UUID) {
        viewModel?.delete(id)
    }

    override fun onItemClick(id: UUID) {
        val b = bundleOf()
        b.putString(ID_KEY, id.toString())
        navController.navigate(R.id.shoppingListDashboardFragment_to_shoppingListFragment, b)
    }

    private fun attachObserver() {
        viewModel?.list?.observe(viewLifecycleOwner, {
            shoppingListDashRcv.apply {
                adapter = ShoppingDashboardAdapter(
                    it.toMutableList(),
                    requireContext(),
                    this@ShoppingListDashboardFragment
                )
                layoutManager = LinearLayoutManager(requireContext())
            }
        })
    }
}