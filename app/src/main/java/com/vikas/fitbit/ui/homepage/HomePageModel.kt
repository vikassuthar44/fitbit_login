package com.vikas.fitbit.ui.homepage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vikas.fitbit.common.Constants
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePageModel @Inject constructor():ViewModel() {

    private val _time = MutableLiveData<Boolean>()
    val routeEvent: LiveData<Boolean> = _time

    fun initStart() {
        viewModelScope.launch {
            kotlinx.coroutines.delay(Constants.HOME_PAGE_REFERESH)
            postRouteEvent(true)
        }
    }

    private fun postRouteEvent(isTime: Boolean) {
        _time.postValue(isTime)
    }
}