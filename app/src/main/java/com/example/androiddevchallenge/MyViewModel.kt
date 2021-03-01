package com.example.androiddevchallenge

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
//import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.ui.data.Data

class MyViewModel: ViewModel() {

    var openModule:Boolean by mutableStateOf(false)
    var currentData: Data? by mutableStateOf(null)
        private set

    fun start(data: Data) {
        currentData = data
        openModule = true
    }

    fun end() {
        openModule = false
    }
}