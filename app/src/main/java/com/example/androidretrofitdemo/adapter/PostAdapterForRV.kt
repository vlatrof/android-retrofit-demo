package com.example.androidretrofitdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidretrofitdemo.R
import com.example.androidretrofitdemo.model.PostModel
import kotlinx.android.synthetic.main.item_layout.view.*

class PostAdapterForRV : RecyclerView.Adapter<PostAdapterForRV.MyViewHolder>() {

    private var postList = emptyList<PostModel>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tv_id.text = postList[position].id.toString()
        holder.itemView.tv_user_id.text = postList[position].userId.toString()
        holder.itemView.tv_title.text = postList[position].title
        holder.itemView.tv_body.text = postList[position].body
    }

    fun setList(newList: List<PostModel>){
        postList = newList
        notifyDataSetChanged()
    }

}