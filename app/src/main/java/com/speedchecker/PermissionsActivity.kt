package com.speedchecker

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.speedchecker.databinding.ActivityPermissionsBinding
import com.xeoh.android.texthighlighter.TextHighlighter

class PermissionsActivity : AppCompatActivity() {

    private val REQUEST_LOCATION_PERMISSION_CODE = 101
    private val REQUEST_PHONE_STATE_PERMISSION_CODE = 102
    private val REQUEST_LOCATION_PERMISSION_BG_CODE = 103
    private lateinit var binding: ActivityPermissionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ), REQUEST_LOCATION_PERMISSION_CODE
            )
        }
        checkPermissions()
    }

    private fun checkPermissions() {
        if (isFgLocationPermissionGranted(this)) {
            finishOnBoarding()
        }
    }

    private fun initReadPhoneStatePermission() {
        if (isReadPhoneStatePermissionGranted(this)) {
            finishOnBoarding()
        }
        binding.containerStart.isVisible = false
        binding.containerPhonePermission.isVisible = true
        TextHighlighter().setForegroundColor(ContextCompat.getColor(this, R.color.colorRed))
            .setBold(true).addTarget(binding.tvCalls).highlight(
                getString(R.string.common_dont).toLowerCase(), TextHighlighter.BASE_MATCHER
            )
        binding.btnFinish.setOnClickListener {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_PHONE_STATE),
                REQUEST_PHONE_STATE_PERMISSION_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String?>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_LOCATION_PERMISSION_CODE) {
            if (isFgLocationPermissionGranted(this)) {
                if (!isBgLocationPermissionGranted(this)) {
                    BackgroundPermissionDialog.show(this) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION),
                            REQUEST_LOCATION_PERMISSION_BG_CODE
                        )
                    }
                }
            }
            initReadPhoneStatePermission()
        }
        if (requestCode == REQUEST_LOCATION_PERMISSION_BG_CODE) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
                finishOnBoarding()
            }
        }
        if (requestCode == REQUEST_PHONE_STATE_PERMISSION_CODE) {
            //TODO phone state is optional permission to detect 5g networks and additional cell info
            finishOnBoarding()
        }
    }

    private fun finishOnBoarding() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun isBgLocationPermissionGranted(context: Context): Boolean {
        var isBgLocationAllowed = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isBgLocationAllowed = ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        }
        return isBgLocationAllowed
    }

    private fun isFgLocationPermissionGranted(context: Context): Boolean {
        return (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }

    private fun isReadPhoneStatePermissionGranted(context: Context): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_PHONE_STATE
        ) == PackageManager.PERMISSION_GRANTED
    }
}