package com.example.kvisiontest

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class PingService : IPingService {

    override suspend fun ping(message: String): String {
        println("Mensaje recibido desde el frontend: $message")
        return "Respuesta del backend: $message"
    }
}
