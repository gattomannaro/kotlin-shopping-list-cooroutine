package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.ext.disable
import com.example.kotlin_shopping_list_cooroutine.ext.enable
import com.example.kotlin_shopping_list_cooroutine.ext.showDialog
import com.example.kotlin_shopping_list_cooroutine.ui.BaseFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_shopping_dashboard.*
import kotlinx.android.synthetic.main.fragment_shopping_list_item.*
import java.util.*

class ShoppingListFragment:
    BaseFragment<ShoppingListViewModel, ShoppingListFactory>(ShoppingListFactory(), ShoppingListViewModel::class.java), ItemsListener {

    companion object {
        const val ID_KEY ="ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let{
            viewModel?.listId = it.getString(ID_KEY,"")
        }
        viewModel?.listId?.let{
            viewModel?.getElements(it)
        }
        return inflater.inflate(R.layout.fragment_shopping_list_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoppingItemTabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position) {
                    0 -> setItemsVisible()
                    1 -> setFruitsAndVegetablesVisible()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
               // not needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // not needed
            }

        })

        viewModel?.items?.observe(viewLifecycleOwner, {
            itemsListRcv.apply {
                adapter = ItemsAdapter(it, requireContext(), this@ShoppingListFragment)
                layoutManager = LinearLayoutManager(requireContext()).apply{ orientation = LinearLayoutManager.VERTICAL }
            }
        })

        itemsNewNameBtnCreate.disable()
        itemsNewNameEdt.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().isBlank() || viewModel?.isValidName(p0.toString())  == false )
                    itemsNewNameBtnCreate.disable()
                else
                    itemsNewNameBtnCreate.enable()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // not needed
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // not needed
            }
        })

        itemsNewNameBtnCreate.setOnClickListener {
            viewModel?.addElement(itemsNewNameEdt.text.toString().capitalize(Locale.ROOT))
            itemsNewNameEdt.setText("")
        }

        itemsDeleteAll.setOnClickListener {
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
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(getString(R.string.list_item_title))
    }

    private fun setItemsVisible() {
        itemsListContainer.visibility = View.VISIBLE
        fruitsAndVegetablesRcv.visibility = View.GONE
    }

    private fun setFruitsAndVegetablesVisible() {
        itemsListContainer.visibility = View.GONE
        fruitsAndVegetablesRcv.visibility = View.VISIBLE
    }

    override fun onDeleteItem(id: UUID) {
        viewModel?.delete(id)
    }

}