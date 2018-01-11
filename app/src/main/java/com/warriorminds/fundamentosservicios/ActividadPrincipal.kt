package com.warriorminds.fundamentosservicios

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.warriorminds.fundamentosservicios.servicios.IntentServiceLargaDuracion
import com.warriorminds.fundamentosservicios.servicios.PrimerServicioIniciado
import com.warriorminds.fundamentosservicios.servicios.ServicioIniciadoCicloVida
import com.warriorminds.fundamentosservicios.servicios.ServicioTareaLargaDuracion
import kotlinx.android.synthetic.main.actividad_principal.*

class ActividadPrincipal : AppCompatActivity() {

    companion object {
        val MENSAJE = "mensaje"
        val APAGAR_AUTOMATICAMENTE = "apagarAutomaticamente"
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
}
