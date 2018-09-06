package com.ch4k4uw.omdbapp.adapter

import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ch4k4uw.application.dto.result.Movie
import com.ch4k4uw.omdbapp.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.view.SimpleDraweeView

class MovieCatalogAdapter(items: List<Movie>, private val itemClick: (Movie) -> Unit): RecyclerView.Adapter<MovieCatalogAdapter.ViewHolder>() {
    private var mItems: ArrayList<Movie> = ArrayList(items)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_catalog_list_item, parent, false)
        return ViewHolder(view, internalItemClick)
    }

    override fun getItemCount(): Int
            = mItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]

        holder.title.text = item.title
        holder.type.text = item.type.name
        holder.year.text = item.year

        val frescoListener = object: BaseControllerListener<Any>() {

        }

        val uri = Uri.parse(item.poster)
        val dc = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setControllerListener(frescoListener)
                .setOldController(holder.image.controller)
                .build()

        holder.image.controller = dc

    }

    fun addItem(item: Movie) {
        mItems.add(item)
        notifyItemInserted(mItems.size - 1)
    }

    fun clear() {
        mItems.clear()
        notifyDataSetChanged()
    }

    private val internalItemClick: (Int) -> Unit = {
        itemClick(mItems[it])
    }

    class ViewHolder(itemView: View, itemClick: (Int) -> Unit): RecyclerView.ViewHolder(itemView) {
        val image: SimpleDraweeView = itemView.findViewById(R.id.image)
        val title: TextView = itemView.findViewById(R.id.title)
        val year: TextView = itemView.findViewById(R.id.year)
        val type: TextView = itemView.findViewById(R.id.type)

        init {
            itemView.setOnClickListener {
                itemClick(layoutPosition)
            }
        }

    }
}