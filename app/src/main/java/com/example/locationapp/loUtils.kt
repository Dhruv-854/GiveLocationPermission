package com.example.locationapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class loUtils(val context: Context) {
    fun hasLocationPermission(context: Context): Boolean {
        return (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
                &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED


    }

    @Composable
    fun MYYY() {
        val context = LocalContext.current
        val loUtils = loUtils(context)
        LocationDisplay(context = context, loUtils = loUtils)
        
    }

    @Composable
    fun LocationDisplay (
        context: Context,
        loUtils: loUtils
    ) {
        val requestPermissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                permissions->
                if (permissions[Manifest.permission.ACCESS_FINE_LOCATION]==true
                    && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true){

                }else{
                    val rationalRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                        context as MainActivity,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )|| ActivityCompat.shouldShowRequestPermissionRationale(
                        context,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                    if (rationalRequired){
                        Toast.makeText(
                            context,
                            "Give the Permission",
                            Toast.LENGTH_LONG
                        ).show()
                    }else{
                        Toast.makeText(
                            context,
                            "go to setting and give the permission",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        )

        Column (
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(text = "Get your Location")
            Button(onClick = {
                if (loUtils.hasLocationPermission(context)) else{
                    requestPermissionLauncher.launch(arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ))
                }
            }) {
                Text(text = "Location")
            }
        }
    }


}

