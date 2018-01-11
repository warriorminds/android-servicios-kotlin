package com.warriorminds.fundamentosservicios.servicios

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.warriorminds.fundamentosservicios.ActividadPrincipal

/**
 * Created by rodrigo.guerrero on 10/01/2018.
 */
class PrimerServicioIniciado : Service() {

    companion object {
        val TAG = this::class.java.simpleName
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val texto = intent?.getStringExtra(ActividadPrincipal.MENSAJE)
        val apagarAutomaticamente = intent?.getBooleanExtra(ActividadPrincipal.APAGAR_AUTOMATICAMENTE, false)
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()

        apagarAutomaticamente?.let {
            if (it) {
                stopSelf(startId)
            }
        }

        return Service.START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "PrimerServicioIniciado ha sido destruido")
    }
}