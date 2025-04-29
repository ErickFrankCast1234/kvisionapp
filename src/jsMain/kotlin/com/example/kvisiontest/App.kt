package com.example.kvisiontest

import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.BootstrapModule
import io.kvision.BootstrapCssModule
import io.kvision.html.Span
import io.kvision.module
import io.kvision.panel.root
import io.kvision.remote.getService
import io.kvision.startApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

// 👇 Scope global para corrutinas del frontend
val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

// 🟢 Punto de entrada del frontend (SPA)
class App : Application() {

    // Se ejecuta cuando inicia la app (al montar la SPA)
    override fun start(state: Map<String, Any>) {

        // 🔧 Contenedor raíz vinculado a <div id="kvapp"> en index.html
        val root = root("kvapp") {
            // Aquí puedes agregar componentes visuales
        }

        // 🧪 Mensaje en consola para debug
        console.log("🚀 Entrando al App.start")

        // 🔄 Llamada asincrónica usando servicio autogenerado
        AppScope.launch {
            try {
                // ✅ Obtenemos el servicio generado por @KVService
                val pingService = getService<IPingService>()

                // 📡 Llamada al backend usando la función remota
                val response = pingService.ping("Hola mi estimado TEO saludos desde el Frontend")

                // 🖋️ Agrega el mensaje del backend dentro del root como un <span>
                root.add(Span(response))

            } catch (e: Throwable) {
                // ❌ Manejo de error en consola del navegador
                console.error("❌ Error al obtener saludo:", e)
            }
        }
    }
}

// 🚀 Arranca la SPA declarando módulos necesarios de KVision
fun main() {
    console.log("🔥 Entrando al main...")

    startApplication(
        ::App,
        module.hot,
        BootstrapModule,
        BootstrapCssModule,
        CoreModule
    )
}
