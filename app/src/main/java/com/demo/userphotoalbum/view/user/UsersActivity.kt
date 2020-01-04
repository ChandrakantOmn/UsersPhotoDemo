package com.demo.userphotoalbum.view.user

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.local.entities.User
import com.demo.userphotoalbum.utils.AppConstants
import com.demo.userphotoalbum.utils.StateData
import com.demo.userphotoalbum.view.album.AlbumActivity
import com.demo.userphotoalbum.view.base.BaseActivity
import com.demo.userphotoalbum.view.base.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

class UsersActivity : BaseActivity() {
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(UserViewModel::class.java)

        viewModel.getUsers()
        viewModel.stateLiveData?.observe(this, Observer {
            handleResponse(it)
        })

    }

    private fun handleResponse(it: StateData<List<User>>?) {
        when (it?.status) {
            StateData.DataStatus.SUCCESS -> displayData(it.data)
            StateData.DataStatus.ERROR -> noDetails()
            StateData.DataStatus.LOADING -> showLoader(true)
            StateData.DataStatus.COMPLETE -> showLoader(false)
            else -> showLoader(false)
        }

    }

    private fun displayData(data: List<User>?) {
        showLoader(false)
        data.let { list ->
            if (list?.isNotEmpty()!!) {
                recycler_view?.apply {
                    layoutManager = LinearLayoutManager(this@UsersActivity)
                    visibility = View.VISIBLE
                    adapter = UsersListAdapter(list, object : OnItemClickListener {
                        override fun onItemClick(any: Any) {
                            val user = any as User
                            startActivity(
                                Intent(this@UsersActivity, AlbumActivity::class.java)
                                    .putExtra(AppConstants.INTENT_DATA, user)
                            )
                        }
                    })
                    adapter?.notifyDataSetChanged()
                    layoutAnimation = AnimationUtils.loadLayoutAnimation(
                        this@UsersActivity,
                        R.anim.layout_animation_fall_down
                    )
                    scheduleLayoutAnimation()
                }
                no_data_tv.visibility = View.GONE
            } else {
                noDetails()
            }
        }
    }

    private fun noDetails() {
        no_data_tv.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }

}
