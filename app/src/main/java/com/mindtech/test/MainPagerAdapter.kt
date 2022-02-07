package com.mindtech.test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_images.view.*


class MainPagerAdapter(private val context: Context, private val images: ArrayList<String>) : RecyclerView.Adapter<MainPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_images, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(images[position],context)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: String, context: Context) {
            itemView.imageView.setImageResource(context.resources.getIdentifier(image, "drawable", context.getPackageName()))
        }
    }

}