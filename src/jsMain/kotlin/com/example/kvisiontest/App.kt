package com.example.kvisiontest

import io.kvision.Application
import io.kvision.CoreModule
import io.kvision.BootstrapModule
import io.kvision.BootstrapCssModule
import io.kvision.html.Span
import io.kvision.module
import io.kvision.panel.root
import io.kvision.startApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.coroutines.launch

val AppScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

class App : Application() {

    override fun start(state: Map<String, Any>) {
        val root = root("kvapp") {
        }
        console.log("🚀 Entrando al App.start")

        AppScope.launch {
            try {
                val response = window.fetch("http://localhost:8080/api/saludo").await().text().await()
                root.add(Span(response))
            } catch (e: Throwable) {
                console.error("❌ Error al obtener saludo:", e)
            }
        }
    }

}

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

