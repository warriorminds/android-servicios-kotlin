package com.warriorminds.fundamentosservicios.servicios

import android.app.IntentService
import android.content.Intent
import android.util.Log

/**
 * Created by rodrigo.guerrero on 10/01/2018.
 */
class IntentServiceLargaDuracion : IntentService("Intent Service Larga Duracion") {

    companion object {
        val TAG = IntentServiceLargaDuracion::class.java.simpleName
    }

    override fun onHandleIntent(intent: Intent?) {
        Log.i(TAG, "Iniciando tarea de larga duración. Hilo de ejecución: " + Thread.currentThread().name)
        Thread.sleep(15000)
        Log.i(TAG, "Terminando tarea de larga duración")
    }
}