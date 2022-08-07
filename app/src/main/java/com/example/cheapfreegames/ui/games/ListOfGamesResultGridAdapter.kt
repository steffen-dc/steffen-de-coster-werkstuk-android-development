package com.example.cheapfreegames.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cheapfreegames.databinding.ListOfGamesResultGridItemBinding
import com.example.cheapfreegames.network.ListOfGamesResult

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class ListOfGamesResultGridAdapter : ListAdapter<ListOfGamesResult, ListOfGamesResultGridAdapter.ListOfGamesResultsViewHolder>(DiffCallback) {

    /**
     * The ListOfGamesResultsViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [ListOfGamesResult] information.
     */
    class ListOfGamesResultsViewHolder(private var binding: ListOfGamesResultGridItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listOfGamesResult: ListOfGamesResult) {
            binding.listOfGamesResult = listOfGamesResult
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ListOfGamesResult] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ListOfGamesResult>() {
        override fun areItemsTheSame(oldItem: ListOfGamesResult, newItem: ListOfGamesResult): Boolean {
            return oldItem.gameID == newItem.gameID
        }

        override fun areContentsTheSame(oldItem: ListOfGamesResult, newItem: ListOfGamesResult): Boolean {
            return oldItem.thumb == newItem.thumb
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfGamesResultsViewHolder {
        return ListOfGamesResultsViewHolder(ListOfGamesResultGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ListOfGamesResultsViewHolder, position: Int) {
        val listOfGamesResult = getItem(position)
        holder.bind(listOfGamesResult)
    }
}