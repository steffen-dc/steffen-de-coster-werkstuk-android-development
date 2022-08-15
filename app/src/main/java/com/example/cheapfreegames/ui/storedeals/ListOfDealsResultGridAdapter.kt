package com.example.cheapfreegames.ui.storedeals

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cheapfreegames.R
import com.example.cheapfreegames.databinding.ListOfDealsResultGridItemBinding
import com.example.cheapfreegames.databinding.ListOfGamesResultGridItemBinding
import com.example.cheapfreegames.network.model.ListOfDealsResult
import com.example.cheapfreegames.network.model.ListOfGamesResult
import com.example.cheapfreegames.ui.game.GameActivity

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class ListOfDealsResultGridAdapter : ListAdapter<ListOfDealsResult, ListOfDealsResultGridAdapter.ListOfDealsResultsViewHolder>(DiffCallback) {

    /**
     * The ListOfDealsResultsViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [ListOfDealsResult] information.
     */
    class ListOfDealsResultsViewHolder(private var binding: ListOfDealsResultGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val cardView: CardView = binding.root.findViewById(R.id.card)

        fun bind(listOfDealsResult: ListOfDealsResult) {
            binding.listOfDealsResult = listOfDealsResult
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [ListOfDealsResult] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<ListOfDealsResult>() {
        override fun areItemsTheSame(oldItem: ListOfDealsResult, newItem: ListOfDealsResult): Boolean {
            return oldItem.gameID == newItem.gameID
        }

        override fun areContentsTheSame(oldItem: ListOfDealsResult, newItem: ListOfDealsResult): Boolean {
            return oldItem.thumb == newItem.thumb
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListOfDealsResultsViewHolder {
        return ListOfDealsResultsViewHolder(ListOfDealsResultGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ListOfDealsResultsViewHolder, position: Int) {
        val listOfDealsResult = getItem(position)
        holder.bind(listOfDealsResult)

        // create explicit intent to pass gameId to game activity
        holder.cardView.setOnClickListener {
            val context = holder.cardView.context
            val intent = Intent(context, GameActivity::class.java)
            intent.putExtra("gameId", holder.cardView.tag.toString())
            context.startActivity(intent)
        }
    }
}