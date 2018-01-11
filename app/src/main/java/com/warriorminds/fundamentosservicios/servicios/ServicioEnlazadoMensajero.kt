package com.warriorminds.fundamentosservicios.servicios

import android.app.Service
import android.content.Intent
import android.os.*
import java.util.*

/**
 * Created by rodrigo.guerrero on 11/01/2018.
 */
class ServicioEnlazadoMensajero : Service() {

    companion object {
        val MENSAJE_HORA = 0
        val MENSAJE_NUMERO_ALEATORIO = 1
        val MENSAJE_TEXTO = 2

        val HORA_EXTRA = "hora"
        val NUMERO_ALEATORIO_EXTRA = "numeroAleatorio"
        val TEXTO_EXTRA = "texto"
    }

    var mensajero : Messenger? = null

    init {
        mensajero = Messenger(Manejador())
    }

    override fun onBind(intent: Intent?): IBinder? = mensajero?.binder

    fun enviarHora(mensajeroResponder: Messenger) {
        val hora = Calendar.getInstance().time.toString()
        val mensaje = Message.obtain(null, MENSAJE_HORA)
        val bundle = Bundle()
        bundle.putString(HORA_EXTRA, hora)
        mensaje.data = bundle
        mensajeroResponder.send(mensaje)
    }

    fun enviarNumeroAleatorio(mensajeroResponder: Messenger) {
        val mensaje = Message.obtain(null, MENSAJE_NUMERO_ALEATORIO)
        val bundle = Bundle()
        bundle.putInt(NUMERO_ALEATORIO_EXTRA, Random().nextInt(1000))
        mensaje.data = bundle
        mensajeroResponder.send(mensaje)
    }

    fun enviarTexto(mensajeroResponder: Messenger) {
        val mensaje = Message.obtain(null, MENSAJE_TEXTO)
        val bundle = Bundle()
        bundle.putString(TEXTO_EXTRA, "Mensaje enviado desde el servicio.")
        mensaje.data = bundle
        mensajeroResponder.send(mensaje)
    }

    inner class Manejador : Handler() {
        override fun handleMessage(msg: Message?) {
            when (msg?.what) {
                MENSAJE_HORA -> this@ServicioEnlazadoMensajero.enviarHora(msg.replyTo)
                MENSAJE_NUMERO_ALEATORIO -> this@ServicioEnlazadoMensajero.enviarNumeroAleatorio(msg.replyTo)
                MENSAJE_TEXTO -> this@ServicioEnlazadoMensajero.enviarTexto(msg.replyTo)
            }
        }
    }
}