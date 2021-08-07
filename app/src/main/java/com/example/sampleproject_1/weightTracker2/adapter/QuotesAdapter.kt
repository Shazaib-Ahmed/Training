package com.example.sampleproject_1.weightTracker2.adapter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject_1.R
import com.github.islamkhsh.CardSliderAdapter


class QuotesAdapter(private val quotes: List<Quotes>) : CardSliderAdapter<QuotesAdapter.QuotesViewHolder>() {


    override fun bindVH(holder: QuotesViewHolder, position: Int) {

        val movie = quotes[position]

        holder.image.setImageResource(movie.poster)
        holder.title.text =movie.title
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_card_content, parent, false)

        return QuotesViewHolder(view)
    }

    override fun getItemCount() = quotes.size


    inner class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.slide_images)
        var title: TextView = itemView.findViewById(R.id.slide_quotes)

    }

}