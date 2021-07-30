package com.example.sampleproject_1.weightTracker2.adapter

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.sampleproject_1.R
import java.util.*
import kotlin.collections.ArrayList


class QuotesAdapter(
    context: Context,
    names: ArrayList<String>,
    imageUrls: ArrayList<String>
) :
    RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {
    //vars
    private var mNames: ArrayList<String> = ArrayList()
    private var mImageUrls: ArrayList<String> = ArrayList()
    private val mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.slide_1_wt2, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(mContext)
            .asBitmap()
            .load(mImageUrls[position])
            .into(holder.image)
        holder.name.text = mNames[position]
        /*holder.image.setOnClickListener(View.OnClickListener {
            Toast.makeText(mContext, mNames[position], Toast.LENGTH_SHORT).show()
        })*/
    }

    override fun getItemCount(): Int {
        return mImageUrls.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var name: TextView

        init {
            image = itemView.findViewById(R.id.slide_images)
            name = itemView.findViewById(R.id.slide_quotes)
        }
    }

    companion object {
        private const val TAG = "RecyclerViewAdapter"
    }

    init {
        mNames = names
        mImageUrls = imageUrls
        mContext = context
    }
}