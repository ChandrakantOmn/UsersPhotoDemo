package com.demo.userphotoalbum.view.photo

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.local.entities.Photo
import com.demo.userphotoalbum.view.base.OnItemClickListener
import kotlinx.android.synthetic.main.item_photo.view.*

/**
 * Created by Chandra Kant on 4/1/20.
 */
class PhotosListAdapter(
    private val list: List<Photo>, private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<PhotosListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photo, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photo = list[position]
        holder.usernameTv.text = photo.title
        Glide.with(holder.itemLayout.context)
            .load(photo.url)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(holder.itemLayout)
        holder.itemLayout.setOnClickListener {
            photo.let { it1 -> onItemClickListener.onItemClick(it1) }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val usernameTv = view.titleView!!
        val itemLayout = view.itemLayout!!

    }

}
