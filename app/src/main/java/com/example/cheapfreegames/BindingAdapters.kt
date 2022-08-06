package com.example.cheapfreegames

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.cheapfreegames.network.ListOfGamesResult
import com.example.cheapfreegames.ui.games.ListOfGamesResultGridAdapter

// top level function
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().build()

        imgView.load(imgUri)
//        imgView.load(imgUri) {
//            placeholder(R.drawable.loading_animation)
//            error(R.drawable.ic_broken_image)
//        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<ListOfGamesResult>?) {
    val adapter = recyclerView.adapter as ListOfGamesResultGridAdapter
    adapter.submitList(data)
}

class BindingAdapters {
}