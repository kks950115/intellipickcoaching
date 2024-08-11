package com.example.intellipickcoaching

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(
    val id: String,
    var pw: String,
    var name: String,
    var phone: String,
    var email:String
):Parcelable