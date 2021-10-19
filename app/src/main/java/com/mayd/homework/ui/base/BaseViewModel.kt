package com.mayd.homework.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(){
    var needCloseApp = false // check if need to close app

    /**
     * the timer of exit when press back
     */
    fun startBackExitAppTimer() {
        needCloseApp = true
        viewModelScope.launch {
            delay(3000)
            needCloseApp = false
        }
    }
}