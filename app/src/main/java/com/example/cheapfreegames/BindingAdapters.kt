package com.example.cheapfreegames

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cheapfreegames.network.model.ApiStatus
import com.example.cheapfreegames.network.model.Deal
import com.example.cheapfreegames.network.model.ListOfGamesResult
import com.example.cheapfreegames.ui.game.DealsGridAdapter
import com.example.cheapfreegames.ui.searchgames.ListOfGamesResultGridAdapter

@BindingAdapter("price")
fun bindPrice(textView: TextView, price: String?){
    price?.let {
        val priceText = "$ $price"
        textView.text = priceText
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().build()

        imgView.load(imgUri)
        imgView.load(imgUri) {
            placeholder(R.drawable.loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listOfGamesResultGridData")
fun bindListOfGamesResultRecyclerView(recyclerView: RecyclerView, data: List<ListOfGamesResult>?) {
    val adapter = recyclerView.adapter as ListOfGamesResultGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("dealsGridData")
fun bindDealsRecyclerView(recyclerView: RecyclerView, data: List<Deal>?) {
    val adapter = recyclerView.adapter as DealsGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("apiStatus")
fun bindApiStatus(imageView: ImageView, apiStatus: ApiStatus?) {

    if (apiStatus == null) return

    when (apiStatus) {
        ApiStatus.LOADING -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.loading_animation)
        }

        ApiStatus.DONE -> {
            imageView.visibility = View.GONE
        }

        else -> {
            imageView.visibility = View.VISIBLE
            imageView.setImageResource(R.drawable.ic_connection_error)
        }
    }
}