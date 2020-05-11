package com.example.workmanagerexample

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    override fun doWork(): Result {
        val appContext = applicationContext

        makeStatusNotification("Worker started work.", appContext)
        sleep(10)
        makeStatusNotification("Worker finshed work.", appContext)

        return Result.success()
    }

}