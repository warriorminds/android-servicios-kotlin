package com.warriorminds.fundamentosservicios.servicios

import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.v4.app.NotificationCompat
import com.warriorminds.fundamentosservicios.R
import com.warriorminds.fundamentosservicios.SegundaActividad

/**
 * Created by rodrigo.guerrero on 11/01/2018.
 */
class ServicioPrimerPlano : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        crearNotificacion()
        return Service.START_NOT_STICKY
    }

    private fun crearNotificacion() {
        val intent = Intent(this, SegundaActividad::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        val notificacion = NotificationCompat.Builder(this)
                .setContentTitle("Título de la Notificación")
                .setContentText("Contenido de la Notificación")
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build()
        startForeground(1, notificacion)
    }
}