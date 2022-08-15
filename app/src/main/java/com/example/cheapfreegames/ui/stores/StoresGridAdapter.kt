package com.example.cheapfreegames.ui.stores


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cheapfreegames.R
import com.example.cheapfreegames.databinding.StoresGridItemBinding
import com.example.cheapfreegames.network.model.ListOfDealsResult
import com.example.cheapfreegames.network.model.Store
import kotlinx.coroutines.NonDisposableHandle.parent

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class StoresGridAdapter(private val onStoreClicked: (Store) -> Unit) : ListAdapter<Store, StoresGridAdapter.StoresViewHolder>(DiffCallback) {

    /**
     * The StoresViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full [Store] information.
     */
    class StoresViewHolder(private var binding: StoresGridItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(store: Store) {
            binding.store = store
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of
     * [Store] has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.storeID == newItem.storeID
        }

        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.images?.banner == newItem.images?.banner
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoresViewHolder {
        return StoresGridAdapter.StoresViewHolder(StoresGridItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: StoresViewHolder, position: Int) {
        val store = getItem(position)

        holder.itemView.setOnClickListener {
            onStoreClicked(store)
        }

        holder.bind(store)
    }
}