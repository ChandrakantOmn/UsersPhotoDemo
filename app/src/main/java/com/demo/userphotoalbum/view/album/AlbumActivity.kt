package com.demo.userphotoalbum.view.album

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.data.local.entities.User
import com.demo.userphotoalbum.utils.AppConstants
import com.demo.userphotoalbum.utils.StateData
import com.demo.userphotoalbum.view.base.BaseActivity
import com.demo.userphotoalbum.view.base.OnItemClickListener
import com.demo.userphotoalbum.view.photo.PhotosActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Chandra Kant on 4/1/20.
 */
class AlbumActivity : BaseActivity() {
    private lateinit var viewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(AlbumViewModel::class.java)

        val user: User? = intent.getParcelableExtra(AppConstants.INTENT_DATA)
        user?.username?.let { setToolbar("$it's Albums") }
        user?.let {
            viewModel.getAlbums(user.id)
        }
        viewModel.stateLiveData?.observe(this, Observer {
            handleResponse(it)
        })

    }

    private fun handleResponse(it: StateData<List<Album>>) {
        when (it.status) {
            StateData.DataStatus.SUCCESS -> displayData(it.data)
            StateData.DataStatus.ERROR -> noDetails()
            StateData.DataStatus.LOADING -> showLoader(true)
            StateData.DataStatus.COMPLETE -> showLoader(false)
            else -> showLoader(false)
        }

    }

    private fun noDetails() {
        no_data_tv.visibility = View.VISIBLE
        recycler_view.visibility = View.GONE
    }

    private fun displayData(data: List<Album>?) {
        showLoader(false)
        data.let { list ->
            if (list?.isNotEmpty()!!) {
                recycler_view?.apply {
                    layoutManager = LinearLayoutManager(this@AlbumActivity)
                    visibility = View.VISIBLE
                    adapter = AlbumListAdapter(list, object : OnItemClickListener {
                        override fun onItemClick(any: Any) {
                            val user = any as Album
                            startActivity(
                                Intent(this@AlbumActivity, PhotosActivity::class.java)
                                    .putExtra(AppConstants.INTENT_DATA, user)
                            )
                        }
                    })
                    adapter?.notifyDataSetChanged()
                    layoutAnimation = AnimationUtils.loadLayoutAnimation(
                        this@AlbumActivity,
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
}
