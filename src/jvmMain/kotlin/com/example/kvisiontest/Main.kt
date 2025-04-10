package com.example.kvisiontest

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.compression.*
import io.ktor.server.plugins.cors.routing.*
import io.kvision.remote.applyRoutes
import io.kvision.remote.getAllServiceManagers
import io.kvision.remote.kvisionInit

fun Application.main() {
    // Permitir CORS (para que funcione entre 8080 y 3000)
    install(CORS) {
        anyHost() // ⚠️ SOLO PARA DESARROLLO. No lo uses así en producción.
        allowHeader(HttpHeaders.ContentType)
        allowMethod(HttpMethod.Get)
    }

    install(Compression)

    routing {
        // Sirve los archivos estáticos del frontend
        staticResources("/", "build/dist/js/productionExecutable") {
            default("index.html")
        }

        get("/api/saludo") {
            call.respondText(
                "La aplicación fullstack en KVision ya está funcionando correctamente. El frontend en Kotlin se comunica con el backend usando Ktor, y se muestra en pantalla la respuesta obtenida desde el servidor. Todo el flujo se realiza en un solo lenguaje, sin duplicidad de modelos, tal como se plantea en la arquitectura de KVision.",
                ContentType.Text.Plain
            )
        }

        // Rutas de servicios remotos de KVision
        getAllServiceManagers().forEach {
            applyRoutes(it)
        }
    }

    // Inicialización de KVision backend
    kvisionInit()
}
