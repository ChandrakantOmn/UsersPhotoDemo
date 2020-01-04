package com.demo.userphotoalbum.view.user

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.local.entities.User
import com.demo.userphotoalbum.view.base.OnItemClickListener
import kotlinx.android.synthetic.main.item_user.view.*


class UsersListAdapter(
    private val list: List<User>, private  val  onItemClickListener: OnItemClickListener
) :
    RecyclerView.Adapter<UsersListAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = list[position]
        holder.usernameTv.text = user.username
        holder.emailTv.text = user.email
        holder.workTv.text = user.company?.name
        holder.nameTv.text = user.name
        holder.phoneTv.text = user.phone
        holder.addressTv.text = "${user.address?.street}, ${user.address?.city}"
        holder.websiteTv.text = user.website
        holder.iconTv.text = user.username?.substring(0, 1)
        holder.itemLayout.setOnClickListener {
            onItemClickListener.onItemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {
        val usernameTv = view.usernameTv!!
        val nameTv = view.nameTv!!
        val emailTv = view.emailTv!!
        val phoneTv = view.phoneTv!!
        val workTv = view.workTv!!
        val addressTv = view.addressTv!!
        val websiteTv = view.websiteTv!!
        val iconTv = view.iconTv!!
        val itemLayout = view.itemLayout!!

    }

}
