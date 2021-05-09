package com.asam.retrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asam.retrofit.R
import com.asam.retrofit.models.Article
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_users.view.*

class NewsAdapter (private val news: List<Article>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(parent?.context).inflate(R.layout.item_users, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.itemView.tvTitle.text = news[position].title
        holder?.itemView.tvUrl.text = news[position].url
        holder?.itemView.apply {
            Glide.with(this).load(news[position].urlToImage).into(ivNews)
        }

    }

}