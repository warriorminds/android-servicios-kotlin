package com.warriorminds.fundamentosservicios.servicios

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

/**
 * Created by rodrigo.guerrero on 11/01/2018.
 */
class ServicioEnlazadoBinder : Service() {

    override fun onBind(intent: Intent?): IBinder? = BinderServicioHora()

    fun obtenerHora() : String {
        return Calendar.getInstance().time.toString()
    }

    inner class BinderServicioHora : Binder() {

        fun obtenerServicio() = this@ServicioEnlazadoBinder
    }
}