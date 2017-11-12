package com.example.beretta.activitytransitionsimple.source.adapter

import android.support.v4.view.ViewCompat
import android.support.v7.widget.AppCompatImageView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.beretta.activitytransitionsimple.R

/**
 * Created by beretta on 12.11.2017.
 */
class ImageRecyclerAdapter(private val urls: List<String>,
                           private val onItemClickListener: OnImageClicked) : RecyclerView.Adapter<ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ctx = holder.image.context
        val url = urls[position]
        ViewCompat.setTransitionName(holder.image, ctx.getString(R.string.recycler_transition, position))
        Glide.with(ctx)
                .load(url)
                .into(holder.image)
        holder.image.setOnClickListener { view -> onItemClickListener.onItemClicked(view as AppCompatImageView, url, position) }
    }

    override fun getItemCount(): Int = urls.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false) as AppCompatImageView))


    interface OnImageClicked {
        fun onItemClicked(image: AppCompatImageView, url: String, position: Int)
    }
}

class ViewHolder(val image: AppCompatImageView) : RecyclerView.ViewHolder(image) {

}