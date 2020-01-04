package com.demo.userphotoalbum.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.demo.userphotoalbum.R

/**
 * Created by Chandra Kant on 4/01/20.
 */
class ProgressDialogFragment : DialogFragment() {
    companion object {
        fun newInstance(): ProgressDialogFragment =
            ProgressDialogFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_progress, container, false)
    }
}