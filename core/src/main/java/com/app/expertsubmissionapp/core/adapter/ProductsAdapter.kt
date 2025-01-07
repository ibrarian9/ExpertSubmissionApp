package com.app.expertsubmissionapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.expertsubmissionapp.core.databinding.ListItemBinding
import com.app.expertsubmissionapp.core.domain.model.MyProduct
import com.bumptech.glide.Glide

class ProductsAdapter : ListAdapter<MyProduct, ProductsAdapter.ListViewHolder>(DiffCallback) {

    var onItemClick: ((MyProduct) -> Unit)? = null

    inner class ListViewHolder(private val bind: ListItemBinding): RecyclerView.ViewHolder(bind.root) {
        fun binding(dataList: MyProduct) {
            bind.apply {
                Glide.with(itemView.context).load(dataList.image).fitCenter().dontAnimate().into(image)
                laptop.text = dataList.title
                desc.text = dataList.description
                harga.text = dataList.price
            }
        }
        init {
            bind.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val bind = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dataList = getItem(position)
        holder.binding(dataList)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<MyProduct>() {
            override fun areItemsTheSame(oldItem: MyProduct, newItem: MyProduct): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MyProduct, newItem: MyProduct): Boolean {
                return oldItem == newItem
            }
        }
    }
}
