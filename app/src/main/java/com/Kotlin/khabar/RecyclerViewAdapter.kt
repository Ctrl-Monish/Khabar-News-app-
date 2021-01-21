package com.Kotlin.khabar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerViewAdapter( private val listener: NewsItemClicked) : RecyclerView.Adapter<newsViewHolder>() {

    private val items : ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cards, parent, false)
        val viewHolder = newsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.title.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updatedNews(updatedNews: ArrayList<News>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }
}
class newsViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){

    val title : TextView = itemView.findViewById(R.id.item_title)
    val image : ImageView = itemView.findViewById(R.id.item_image)
    val author : TextView = itemView.findViewById(R.id.item_text)
}

interface NewsItemClicked{
    fun onItemClicked(item: News)
}