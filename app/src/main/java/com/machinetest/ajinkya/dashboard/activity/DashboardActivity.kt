package com.machinetest.ajinkya.dashboard.activity

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.machinetest.ajinkya.R
import com.machinetest.ajinkya.dashboard.home.fragment.CityFragment
import com.machinetest.ajinkya.databinding.ActivityDashboardBinding
import com.machinetest.ajinkya.other.interfaces.ConnectivityReceiverListener
import com.machinetest.ajinkya.other.reciver.ConnectivityReceiver
import com.machinetest.ajinkya.other.utils.AppUtility

class DashboardActivity : AppCompatActivity(), ConnectivityReceiverListener {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard)
        binding.lifecycleOwner = this
        addFragment(CityFragment())
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    fun addFragment(fragment: Fragment) {

        if (!AppUtility.isConnectedToInternet(baseContext)) {
            AppUtility.showSimpleSnackbarShort(this@DashboardActivity,"No internet connection!")
        } else {
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
                .addToBackStack(null).commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (!isConnected) {
            AppUtility.showSimpleSnackbarShort(this@DashboardActivity,"Internet connection disable!")
        }else{
            AppUtility.showSimpleSnackbarShort(this@DashboardActivity,"Internet connection enable!")
        }
    }
}