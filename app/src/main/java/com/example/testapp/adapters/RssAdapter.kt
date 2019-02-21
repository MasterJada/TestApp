package com.example.testapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.testapp.Article
import com.example.testapp.R
import com.example.testapp.RxBus

class RssAdapter : RecyclerView.Adapter<RssAdapter.RssVH>() {
    var items: List<Article> = ArrayList()
        set(value) {
            val r = value.filterNot { field.contains(it) }
            if(r.isNotEmpty()) {
                field = value
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RssVH {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.rss_item, p0, false)
        return RssVH(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(vh: RssVH, pos: Int) {
        items[pos].let {article ->
            vh.title.text = article.title
            vh.descrition.text = article.description
            vh.itemView.setOnClickListener {
                RxBus.publish(article)
            }
        }
    }

    class RssVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.tv_title)
        val descrition: TextView = itemView.findViewById(R.id.tv_link)
    }
}