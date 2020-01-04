package com.demo.userphotoalbum.view.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.demo.userphotoalbum.R
import com.demo.userphotoalbum.data.DataManager
import com.demo.userphotoalbum.utils.AppConstants
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


/**
 * Created by Chandra Kant on 4/1/20.
 */
abstract class BaseActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        Log.d("ActivityName", javaClass.simpleName)
    }


    override fun startActivity(intent: Intent) {
        super.startActivity(intent)
        this.overridePendingTransition(R.anim.activity_in, R.anim.activity_out)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        this.overridePendingTransition(R.anim.activity_in_back, R.anim.activity_out_back)

    }


    fun setToolbar(
        title: String
    ) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = title
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }


    private fun showProgressDialog() {
        try {
            val prev = supportFragmentManager.findFragmentByTag(AppConstants.PROGRESS_DIALOG)
            val ft = supportFragmentManager.beginTransaction()
            if (prev != null) {
                ft.remove(prev)
            }
            ft.addToBackStack(null)
            val newFragment = ProgressDialogFragment.newInstance()
            newFragment.isCancelable = true
            newFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.ProgressDialog)
            newFragment.show(supportFragmentManager, AppConstants.PROGRESS_DIALOG)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun dismissProgressDialog() {
        try {
            supportFragmentManager.executePendingTransactions()
            val prev = supportFragmentManager.findFragmentByTag(AppConstants.PROGRESS_DIALOG)
            if (prev != null) {
                val ft = supportFragmentManager.beginTransaction()
                ft.remove(prev)
                ft.commit()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun showLoader(show: Boolean) {
        if (show) showProgressDialog() else dismissProgressDialog()
    }

}