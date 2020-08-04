package com.example.kotlin_shopping_list_cooroutine.ui.shoppingListFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.data.model.ListModel
import kotlinx.android.synthetic.main.adapter_dashboard_item.view.*

class ShoppingDashboardAdapter(
    private val items: List<ListModel>,
    private val context: Context,
    private val listener: ShoppingDashboardAdapterListener
): RecyclerView.Adapter<ShoppingDashboardAdapter.ShoppingDashboardVIewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingDashboardVIewHolder {
        return ShoppingDashboardVIewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_dashboard_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingDashboardVIewHolder, position: Int) {
        val item = items[position]
        holder.itemView.dashboardAdapterName.text = item.listName
        holder.itemView.dashboardAdapterBtn.setOnClickListener {
            listener.delete(item.id)
        }
    }


    inner class ShoppingDashboardVIewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}