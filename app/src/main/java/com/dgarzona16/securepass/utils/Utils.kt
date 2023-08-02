package com.dgarzona16.securepass.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.navigation.NavController

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun showTopBar(destination: String?): Boolean {
    return when (destination) {
        AppRoute.SETUP_INITIAL -> false
        AppRoute.AUTHENTICATION -> false
        "${AppRoute.ERROR}/{title}/{message}/{btnText}/{errorType}" -> false
        else -> true
    }
}