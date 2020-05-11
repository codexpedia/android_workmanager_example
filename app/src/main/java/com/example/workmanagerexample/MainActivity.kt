package com.example.workmanagerexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_worker.setOnClickListener {
            startWork()
        }
    }

    fun createConstraints() = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .build()

    fun createWorkRequest(data: Data) = PeriodicWorkRequestBuilder<MyWorker>(15, TimeUnit.MINUTES)
            .setInputData(data)
            .setConstraints(createConstraints())
            .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
            .build()

    fun startWork() {
        val work = createWorkRequest(Data.EMPTY)
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork("Sleep work", ExistingPeriodicWorkPolicy.REPLACE, work)
    }

}
