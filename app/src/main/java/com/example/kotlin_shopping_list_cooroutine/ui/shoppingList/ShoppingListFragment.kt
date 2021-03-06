package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.ext.afterTextChanged
import com.example.kotlin_shopping_list_cooroutine.ext.disable
import com.example.kotlin_shopping_list_cooroutine.ext.enable
import com.example.kotlin_shopping_list_cooroutine.ext.showDialog
import com.example.kotlin_shopping_list_cooroutine.ui.BaseFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_shopping_list_item.*
import java.util.*


class ShoppingListFragment:
    BaseFragment<ShoppingListViewModel, ShoppingListFactory>(
        ShoppingListFactory(),
        ShoppingListViewModel::class.java
    ), ItemsListener, VegetablesListener {

    companion object {
        const val ID_KEY ="ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let{
            viewModel?.listId = it.getString(ID_KEY, "")
        }
        viewModel?.listId?.let{
            viewModel?.getElements(it)
        }
        viewModel?.getVegetables()
        return inflater.inflate(R.layout.fragment_shopping_list_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shoppingItemTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
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

        itemsNewNameBtnCreate.disable()
        itemsNewNameEdt.afterTextChanged {
            if (it.isBlank() || viewModel?.isValidName(it) == false)
                itemsNewNameBtnCreate.disable()
            else
                itemsNewNameBtnCreate.enable()
        }

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

        attachObserver()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setToolbarTitle(getString(R.string.list_item_title))
        showToolbarMenu {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, viewModel?.getListString())
                type = "text/plain"
            }
            startActivity(Intent.createChooser(sendIntent, null))
        }
    }

    private fun attachObserver() {
        viewModel?.vegetables?.observe(viewLifecycleOwner, {
            vegetablesListRcv.apply {
                adapter = VegetablesAdapter(it, requireContext(), this@ShoppingListFragment)
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
            }
        })


        viewModel?.items?.observe(viewLifecycleOwner, {
            itemsListRcv.apply {
                adapter = ItemsAdapter(it, requireContext(), this@ShoppingListFragment)
                layoutManager = LinearLayoutManager(requireContext()).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
            }
            itemsCounter.text = getString(R.string.item_count, it.size)
            if (it.isNotEmpty())
                itemsDeleteAll.enable()
            else
                itemsDeleteAll.disable()
        })
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

    override fun addVegetable(name: String) {
        viewModel?.isValidName(name)?.let{
            if(it) {
                viewModel?.addElement(name)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.element_added),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}