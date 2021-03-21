package com.vikas.fitbit.ui.loginScreen

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.vikas.fitbit.R
import com.vikas.fitbit.databinding.ActivityLoginScreenBinding
import com.vikas.fitbit.ui.homepage.HomePageActivity
import com.vikas.fitbit.ui.splash.SplashViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class LoginScreenActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: ActivityLoginScreenBinding

    private val viewModel: LoginScreenModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LoginScreenModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen)

        this.window.statusBarColor = resources.getColor(R.color.status_login_screen)

        init()

    }


    private fun init() {
        mBinding.btnLogin.setOnClickListener {
            checkEmailPassword()
        }
    }


     fun checkEmailPassword():String {
        when {
            mBinding.etEmailId.text.toString().isEmpty() -> {
                Toast.makeText(applicationContext, "Enter email address", Toast.LENGTH_SHORT).show()
                return "Enter email address"
            }

            mBinding.etPassword.text.toString().isEmpty() -> {
                Toast.makeText(applicationContext, "Enter password", Toast.LENGTH_SHORT).show()
                return "Enter password"
            }

            viewModel.checkEmailValid(mBinding.etEmailId.text.toString()) -> {
                val intent = Intent(this, HomePageActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                startActivity(intent)
                return "Login success"
            }
            else -> {
                Toast.makeText(applicationContext, "Invalid email address", Toast.LENGTH_SHORT).show()
                return "Invalid email address"
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mBinding.etEmailId.requestFocus()
    }

}