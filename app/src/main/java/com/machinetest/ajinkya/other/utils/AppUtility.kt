package com.machinetest.ajinkya.other.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import com.google.android.material.snackbar.Snackbar

object AppUtility {

   fun isConnectedToInternet(activity: Context): Boolean {
        val cm = activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }

    fun showSimpleSnackbarShort(activity: Activity, message: String) {
        Snackbar.make(activity.findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }
}

