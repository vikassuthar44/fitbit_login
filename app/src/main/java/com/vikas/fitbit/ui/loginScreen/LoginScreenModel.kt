package com.vikas.fitbit.ui.loginScreen

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class LoginScreenModel @Inject constructor(): ViewModel() {

    fun checkEmailValid(emailId:String):Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()


        return if(emailId.isEmpty()) {
            false
        }else {
            emailId.trim().matches(emailPattern)
        }
    }
}