package com.warriorminds.fundamentosservicios.servicios

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import java.util.*

/**
 * Created by rodrigo.guerrero on 11/01/2018.
 */
class ServicioEnlazadoBinder : Service() {

    val TAG = this::class.java.simpleName

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate(): servicio enlazado.")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(TAG, "onBind(): servicio enlazado.")
        return BinderServicioHora()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind(): servicio enlazado.")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy(): servicio enlazado.")
    }

    fun obtenerHora() : String {
        return Calendar.getInstance().time.toString()
    }

    inner class BinderServicioHora : Binder() {

        fun obtenerServicio() = this@ServicioEnlazadoBinder
    }
}