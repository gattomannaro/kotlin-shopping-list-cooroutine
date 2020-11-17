package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.data.model.ListItem
import kotlinx.android.synthetic.main.items_adapter.view.*
import java.util.*

class ItemsAdapter(
    private val items: MutableList<ListItem>,
    private val context: Context,
    private val listener: ItemsListener?
): RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(LayoutInflater.from(context).inflate(R.layout.items_adapter, parent, false))
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.vegetablesAdapterName.text = item.name
        holder.itemView.vegetablesAdapterBtn.setOnClickListener {
            items.remove(item)
            listener?.onDeleteItem(item.id)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

interface ItemsListener {
    fun onDeleteItem(id: UUID)
}