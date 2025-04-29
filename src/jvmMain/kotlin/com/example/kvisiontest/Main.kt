package com.example.kvisiontest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.routing.*
import io.kvision.remote.applyRoutes
import io.kvision.remote.getAllServiceManagers
import io.kvision.remote.kvisionInit

fun Application.main() {
    // 🛡️ Plugin CORS: permite solicitudes desde otros orígenes (por ejemplo, frontend en otro puerto)
    install(CORS) {
        anyHost() // ⚠️ En producción reemplaza con host("https://tu-dominio.com")
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Get)
        allowMethod(HttpMethod.Post)
    }

    // 📦 Plugin de compresión para respuestas HTTP (reduce tamaño de datos transferidos)
    install(Compression)

    routing {
        // 🌐 Sirve los archivos estáticos del frontend generado por KVision
        staticResources("/", "build/dist/js/productionExecutable") {
            default("index.html")
        }

        // 🔁 Aplica automáticamente las rutas generadas por los servicios remotos (@KVService)
        getAllServiceManagers().forEach {
            applyRoutes(it)
        }
    }

    // 🚀 Inicializa internamente KVision en el backend (serialización, configuración, etc.)
    kvisionInit()
}
