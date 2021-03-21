package com.vikas.fitbit.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.fitbit.R
import com.vikas.fitbit.common.Constants
import kotlinx.coroutines.launch
import java.util.logging.Handler
import javax.inject.Inject

/**
 *<h1>LoginViewModel</h1>
 * @author : Vikas Suthar
 * @since : 13 March 2021
 * @version : 1.0
 */
class LoginViewModel @Inject constructor(
) : ViewModel(){


    private val _sliderAdapter = MutableLiveData<Boolean>()
    val sliderAdapter: LiveData<Boolean> = _sliderAdapter

    private val _time = MutableLiveData<Boolean>()
    val routeEvent: LiveData<Boolean> = _time

    fun initStart() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(Constants.SLIDER_DELAY_MILLIS)
            postRouteEvent(true)
        }
    }

    private fun postRouteEvent(isTime: Boolean) {
        _time.postValue(isTime)
    }

}