package com.vikas.fitbit.ui.login

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vikas.fitbit.R
import com.vikas.fitbit.databinding.ActivityLoginBinding
import com.vikas.fitbit.ui.login.model.SliderAdapter
import com.vikas.fitbit.ui.loginScreen.LoginScreenActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 *<h1>LoginActivity</h1>
 *<p>this is activity for login screen</p>
 * @author : Vikas Suthar
 * @since : 13 March 2021
 * @version : 1.0
 */
class LoginActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var context:Context

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    lateinit var sliderAdapter: SliderAdapter

    private var currentPage = 0
    private var pageSize = 4


    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    private lateinit var mBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        init()
        this.window.statusBarColor =  getColor(R.color.slider_status_one)
        viewModel.initStart()
        setUpNotifyAdapterObserver()
        setupObserver()

    }

    private fun init() {

        var sliderImgList = ArrayList<Int>()
        sliderImgList.add(R.drawable.ic_fitbit_icon)
        sliderImgList.add(R.drawable.ic_running)
        sliderImgList.add(R.drawable.ic_cutlery)
        sliderImgList.add(R.drawable.ic_weight)
        sliderImgList.add(R.drawable.ic_moon)

        var sliderTextList = ArrayList<String>()
        sliderTextList.add(context.resources.getString(R.string.track_fitness))
        sliderTextList.add(context.resources.getString(R.string.landing_title1))
        sliderTextList.add(context.resources.getString(R.string.landing_title2))
        sliderTextList.add(context.resources.getString(R.string.landing_title3))
        sliderTextList.add(context.resources.getString(R.string.landing_title4))

        sliderAdapter = SliderAdapter(sliderTextList, sliderImgList)
        mBinding.sliderViewPager.adapter = sliderAdapter

        mBinding.btnLogin.setOnClickListener(this)


    }

    /**
     * <h2>setupObserver</h2>
     * <p>this is the method for observing the duration</p>
     */

    private fun setupObserver() {
        viewModel.routeEvent.observe(this, Observer {
            val window: Window = this.window

            if (it) {
                if (currentPage == pageSize) {
                    currentPage = 0
                }
                when(currentPage) {
                    0 -> {
                        window.statusBarColor = getColor(R.color.slider_status_one)
                    }
                    1 -> {
                        window.statusBarColor = getColor(R.color.slider_status_two)
                    }
                    2 -> {
                        window.statusBarColor = getColor(R.color.slider_status_three)
                    }
                    3 -> {
                        window.statusBarColor = getColor(R.color.slider_status_four)
                    }
                    4 -> {
                        window.statusBarColor = getColor(R.color.slider_status_five)
                    }
                }
                mBinding.sliderViewPager.setCurrentItem(currentPage++, true)
            }

            notifyAdapter()
            viewModel.initStart()
        })
    }

    private fun notifyAdapter() {
        sliderAdapter.notifyDataSetChanged()
    }

    private fun setUpNotifyAdapterObserver() {
        viewModel.sliderAdapter.observe(this, Observer {
            notifyAdapter()
        }
        )
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btnLogin -> {
                val intent = Intent(this, LoginScreenActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
            }
        }
    }
}