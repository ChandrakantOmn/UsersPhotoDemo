package com.demo.userphotoalbum.view.photo

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.local.entities.Album
import com.demo.userphotoalbum.data.local.entities.Photo
import com.demo.userphotoalbum.utils.AppConstants
import com.demo.userphotoalbum.utils.StateData
import com.demo.userphotoalbum.view.base.BaseActivity
import com.demo.userphotoalbum.view.base.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Chandra Kant on 4/1/20.
 */
class PhotosActivity : BaseActivity() {
    private lateinit var viewModel: PhotosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            viewModelFactory
        ).get(PhotosViewModel::class.java)

        val album: Album? = intent.getParcelableExtra(AppConstants.INTENT_DATA)
        album?.let {
            viewModel.getPhotos(album.id)
        }
        album?.title?.let { setToolbar(it) }

        viewModel.stateLiveData?.observe(this, Observer {
            handleResponse(it)
        })

    }

    private fun handleResponse(it: StateData<List<Photo>>) {
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

    private fun displayData(data: List<Photo>?) {
        showLoader(false)
        data.let { list ->
            if (list?.isNotEmpty()!!) {
                recycler_view?.apply {
                    layoutManager = LinearLayoutManager(this@PhotosActivity)
                    visibility = View.VISIBLE
                    adapter = PhotosListAdapter(list, object : OnItemClickListener {
                        override fun onItemClick(any: Any) {

                        }
                    })
                    adapter?.notifyDataSetChanged()
                }
                no_data_tv.visibility = View.GONE
            } else {
                noDetails()
            }

        }

    }
}
