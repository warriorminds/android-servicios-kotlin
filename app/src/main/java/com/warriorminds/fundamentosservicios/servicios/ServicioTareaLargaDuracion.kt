package com.warriorminds.fundamentosservicios.servicios

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

/**
 * Created by rodrigo.guerrero on 10/01/2018.
 */
class ServicioTareaLargaDuracion : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Iniciando tarea de larga duración.", Toast.LENGTH_SHORT).show()
        Thread.sleep(15000)
        Toast.makeText(this, "Terminando tarea de larga duración.", Toast.LENGTH_SHORT).show()
        stopSelf(startId)
        return Service.START_NOT_STICKY
    }
}