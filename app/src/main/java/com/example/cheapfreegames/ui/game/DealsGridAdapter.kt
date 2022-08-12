package com.example.cheapfreegames.ui.game

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cheapfreegames.R
import com.example.cheapfreegames.databinding.DealsGridItemBinding
import com.example.cheapfreegames.model.StoreDeal

const val SEARCH_PREFIX = "https://www.cheapshark.com/redirect?dealID="

class DealsGridAdapter : ListAdapter<StoreDeal, DealsGridAdapter.DealsViewHolder>(DiffCallback) {

    class DealsViewHolder(private var binding: DealsGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val cardView: CardView = binding.root.findViewById(R.id.card)

        fun bind(storeDeal: StoreDeal) {
            binding.storeDeal = storeDeal
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<StoreDeal>() {
        override fun areItemsTheSame(oldItem: StoreDeal, newItem: StoreDeal): Boolean {
            return oldItem.dealID == newItem.dealID
        }

        override fun areContentsTheSame(oldItem: StoreDeal, newItem: StoreDeal): Boolean {
            return oldItem.storeID == newItem.storeID
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealsViewHolder {
        return DealsViewHolder(
            DealsGridItemBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: DealsViewHolder, position: Int) {
        val storeDeal = getItem(position)
        holder.bind(storeDeal)

        // create implicit intent to go to store website
        holder.cardView.setOnClickListener {
            val context = holder.cardView.context
            val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${storeDeal.dealID}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            context.startActivity(intent)
        }
    }
}