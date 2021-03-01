package com.example.androiddevchallenge.ui.data

import androidx.annotation.DrawableRes

data class Data(
    val name: String,
    @DrawableRes val avatar: Int,
    val message: String,
    val age: Number = 10,
    val area: String = "Earth",
    val phoneNumber: String = "110110110"
)
