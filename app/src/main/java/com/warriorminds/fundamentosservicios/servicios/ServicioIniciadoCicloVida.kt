package com.warriorminds.fundamentosservicios.servicios

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * Created by rodrigo.guerrero on 10/01/2018.
 */
class ServicioIniciadoCicloVida : Service() {

    companion object {
        val TAG = ServicioIniciadoCicloVida::class.java.simpleName
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate(): Servicio Iniciado")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand(): Servicio Iniciado. Hilo de ejecución: " + Thread.currentThread().name)
        stopSelf(startId)
        return Service.START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy(): Servicio Iniciado.")
    }
}