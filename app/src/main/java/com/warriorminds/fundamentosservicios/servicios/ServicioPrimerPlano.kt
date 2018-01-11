package com.warriorminds.fundamentosservicios.servicios

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            crearNotificacionParaAndroidO(pendingIntent)
        } else {
            val notificacion = NotificationCompat.Builder(this)
                    .setContentTitle("Título de la Notificación")
                    .setContentText("Contenido de la Notificación")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .build()
            startForeground(1, notificacion)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun crearNotificacionParaAndroidO(intentPendiente: PendingIntent) {
        val managerDeNotificaciones = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val idCanal = "mi_canal"
        val canal = NotificationChannel(idCanal, "Nombre del canal", NotificationManager.IMPORTANCE_DEFAULT)
        canal.enableLights(true)
        managerDeNotificaciones.createNotificationChannel(canal)

        val notificacion = NotificationCompat.Builder(this, canal.id)
                .setContentTitle("Título de la Notificación")
                .setContentText("Contenido de la Notificación")
                .setContentIntent(intentPendiente)
                .setSmallIcon(R.drawable.ic_launcher)
                .build()
        startForeground(1, notificacion)
    }
}