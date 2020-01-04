package com.demo.userphotoalbum.view.album

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.view.base.OnItemClickListener
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * Created by Chandra Kant on 4/1/20.
 */
class AlbumListAdapter(
    private val list: List<Album>, private val onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<AlbumListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_album, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val album = list[position]
        holder.titleView.text = album.title
        holder.itemLayout.setOnClickListener {
            album.let { it1 -> onItemClickListener.onItemClick(it1) }
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val titleView = view.titleView!!
        val itemLayout = view.itemLayout!!

    }

}
