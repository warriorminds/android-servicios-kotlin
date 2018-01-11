package com.warriorminds.fundamentosservicios

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Toast
import com.warriorminds.fundamentosservicios.servicios.*
import kotlinx.android.synthetic.main.actividad_principal.*

class ActividadPrincipal : AppCompatActivity() {

    companion object {
        val MENSAJE = "mensaje"
        val APAGAR_AUTOMATICAMENTE = "apagarAutomaticamente"
    }

    var servicioBinderConectado : Boolean = false
    var servicioEnlazadoBinder : ServicioEnlazadoBinder? = null

    val conexionServicioBinder = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            servicioBinderConectado = false
            servicioEnlazadoBinder = null
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            servicioBinderConectado = true
            service?.let {
                servicioEnlazadoBinder = (service as ServicioEnlazadoBinder.BinderServicioHora).obtenerServicio()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)

        btnIniciarPrimerServicio.setOnClickListener {
            iniciarPrimerServicio()
        }

        btnDetenerPrimerServicio.setOnClickListener {
            detenerPrimerServicio()
        }

        cbDetenerServicioAutomaticamente.setOnCheckedChangeListener { _, isChecked -> btnDetenerPrimerServicio.isEnabled = !isChecked }

        btnCicloVidaServicioIniciado.setOnClickListener {
            iniciarServicioIniciadoCicloVida()
        }

        btnServicioLargaDuracion.setOnClickListener {
            iniciarServicioTareaLargaDuracion()
        }

        btnIntentServiceLargaDuracion.setOnClickListener {
            iniciarIntentService()
        }

        btnServicioPrimerPlano.setOnClickListener {
            iniciarServicioPrimerPlano()
        }

        btnDetenerServicioPrimerPlano.setOnClickListener {
            detenerServicioPrimerPlano()
        }

        btnObtenerHora.setOnClickListener {
            obtenerHoraServicioConIBinder()
        }
    }

    override fun onStart() {
        super.onStart()
        enlazarServicioBinder()
    }

    override fun onStop() {
        super.onStop()
        desenlazarServicioBinder()
    }

    private fun detenerPrimerServicio() {
        stopService(Intent(this, PrimerServicioIniciado::class.java))
    }

    private fun iniciarPrimerServicio() {
        val intent = Intent(this, PrimerServicioIniciado::class.java)
        val texto = if (TextUtils.isEmpty(mensajePrimerServicioIniciado.text.toString())) "El usuario no escribió ningún mensaje" else mensajePrimerServicioIniciado.text.toString()
        intent.putExtra(MENSAJE, texto)
        intent.putExtra(APAGAR_AUTOMATICAMENTE, cbDetenerServicioAutomaticamente.isChecked)
        startService(intent)
    }

    private fun iniciarServicioIniciadoCicloVida() {
        val intent = Intent(this, ServicioIniciadoCicloVida::class.java)
        startService(intent)
    }

    private fun iniciarServicioTareaLargaDuracion() {
        val intent = Intent(this, ServicioTareaLargaDuracion::class.java)
        startService(intent)
    }

    private fun iniciarIntentService() {
        val intent = Intent(this, IntentServiceLargaDuracion::class.java)
        startService(intent)
    }

    private fun iniciarServicioPrimerPlano() {
        val intent = Intent(this, ServicioPrimerPlano::class.java)
        startService(intent)
    }

    private fun detenerServicioPrimerPlano() {
        val intent = Intent(this, ServicioPrimerPlano::class.java)
        stopService(intent)
    }

    private fun obtenerHoraServicioConIBinder() {
        if (servicioBinderConectado) {
            servicioEnlazadoBinder?.let {
                Toast.makeText(this, "Hora actual: " + it.obtenerHora(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun enlazarServicioBinder() {
        val intent = Intent(this, ServicioEnlazadoBinder::class.java)
        bindService(intent, conexionServicioBinder, Context.BIND_AUTO_CREATE)
    }

    private fun desenlazarServicioBinder() {
        unbindService(conexionServicioBinder)
        servicioBinderConectado = false
    }
}
