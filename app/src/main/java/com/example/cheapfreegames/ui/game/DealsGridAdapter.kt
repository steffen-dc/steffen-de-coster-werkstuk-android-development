package com.example.cheapfreegames.ui.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cheapfreegames.R
import com.example.cheapfreegames.databinding.DealsGridItemBinding
import com.example.cheapfreegames.network.Deal

class DealsGridAdapter : ListAdapter<Deal, DealsGridAdapter.DealsViewHolder>(DiffCallback) {

    class DealsViewHolder(private var binding: DealsGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val cardView: CardView = binding.root.findViewById(R.id.card)

        fun bind(deal: Deal) {
            binding.deal = deal
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Deal>() {
        override fun areItemsTheSame(oldItem: Deal, newItem: Deal): Boolean {
            return oldItem.dealID == newItem.dealID
        }

        override fun areContentsTheSame(oldItem: Deal, newItem: Deal): Boolean {
            return oldItem.storeID == newItem.storeID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        return DealsViewHolder(
            DealsGridItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: DealsGridAdapter.DealsViewHolder, position: Int) {
        val deal = getItem(position)
        holder.bind(deal)

        // create implicit intent to go to store website
    }
}