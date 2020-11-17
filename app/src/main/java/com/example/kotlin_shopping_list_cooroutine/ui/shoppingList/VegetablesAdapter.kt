package com.example.kotlin_shopping_list_cooroutine.ui.shoppingList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_shopping_list_cooroutine.R
import com.example.kotlin_shopping_list_cooroutine.data.model.Vegetable
import kotlinx.android.synthetic.main.adapter_vegetable.view.*

class VegetablesAdapter(
private val items: List<Vegetable>,
private val context: Context,
private val listener: VegetablesListener?
): RecyclerView.Adapter<VegetablesAdapter.VegetablesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VegetablesViewHolder {
        return VegetablesViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_vegetable, parent, false))
    }

    override fun onBindViewHolder(holder: VegetablesViewHolder, position: Int) {
        holder.itemView.vegetablesAdapterName.text = items[position].italianName
        holder.itemView.vegetablesAdapterBtn.setOnClickListener {
            listener?.addVegetable(items[position].italianName)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class VegetablesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}

interface VegetablesListener{
    fun addVegetable(name: String)
}