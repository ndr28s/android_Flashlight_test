package com.example.flashlight

import android.app.Service
import android.content.Intent
import android.os.IBinder

class TorchService : Service() {
    private val torch: Torch by lazy {
        Torch(this)
    }

    private var isRunning = true
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            "on" -> {
                torch.flashOn()
                isRunning = false
            }
            "off" -> {
                torch.flashOff()
                isRunning = true
            } else -> {
            if(isRunning) {
                torch.flashOn()
            } else {
                torch.flashOff()
            }
            isRunning = !isRunning
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }
    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
}
