package com.mindtech.test

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_view.view.*
import java.util.*


@SuppressLint("NotifyDataSetChanged")
class RecyclerAdapter (private val context: Context, private var images: ArrayList<String>) : RecyclerView.Adapter<RecyclerAdapter.ViewPagerHolder>() {

    private var imagesFilter = ArrayList<String>()

    init{
        imagesFilter = images
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bind(imagesFilter[position],context)
    }

    class ViewPagerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(image: String, mContext: Context) {
            itemView.item_imageView.setImageResource(mContext.resources.getIdentifier(image, "drawable", mContext.getPackageName()))
            itemView.item_textView.text = image
        }
    }

    override fun getItemCount(): Int {
        return imagesFilter.size
    }

    fun updateList(modelArrayList : ArrayList<String>){
        imagesFilter = modelArrayList
        notifyDataSetChanged()
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    imagesFilter = images
                } else {
                    val resultList = ArrayList<String>()
                    for (row in imagesFilter) {
                        if (row.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    imagesFilter = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = imagesFilter
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                imagesFilter = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }

        }
    }

}