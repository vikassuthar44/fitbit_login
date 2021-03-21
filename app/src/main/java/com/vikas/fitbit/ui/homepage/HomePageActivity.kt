package com.vikas.fitbit.ui.homepage

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vikas.fitbit.R
import com.vikas.fitbit.databinding.ActivityHomePageBinding
import com.vikas.fitbit.utils.Utils
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class HomePageActivity:DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityHomePageBinding

    private var position:Int = 0

    private val viewModel: HomePageModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomePageModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home_page)

        this.window.statusBarColor =  resources.getColor(R.color.status_login_screen)

        setupObserver()
        init()



    }

    private fun init() {
        mBinding.backArrow.setOnClickListener {
            viewModel.initStart()
            position--
            mBinding.tvDate.text = Utils.getDate(position)
            if(position < 0) {
                mBinding.nextArrow.visibility = View.VISIBLE
                mBinding.btnEdit.visibility = View.GONE
            }

            mBinding.progressView.visibility = View.VISIBLE
            mBinding.centerView.visibility = View.GONE

        }

        mBinding.nextArrow.setOnClickListener {
            viewModel.initStart()
            position++
            mBinding.tvDate.text = Utils.getDate(position)

            if(position == 0) {
                mBinding.nextArrow.visibility = View.GONE
                mBinding.btnEdit.visibility = View.VISIBLE
            }
            mBinding.progressView.visibility = View.VISIBLE
            mBinding.centerView.visibility = View.GONE
        }
    }

    private fun setupObserver() {
        viewModel.routeEvent.observe(this, Observer {
            if (it) {
                mBinding.progressView.visibility = View.GONE
                mBinding.centerView.visibility = View.VISIBLE
            }
        })
    }

    override fun onBackPressed() {
        exitConfirmation()
    }

    private fun exitConfirmation() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.exit_msg))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.yes)) { _, _ -> super.onBackPressed() }
            .setNegativeButton(getString(R.string.no), null)
            .show()
    }

}