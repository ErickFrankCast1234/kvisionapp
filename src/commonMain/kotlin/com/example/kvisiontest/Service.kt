package com.example.kvisiontest
// 📦 Paquete del proyecto. Organiza el código dentro de la estructura de la app.

import io.kvision.annotations.KVService
// 📥 Importa la anotación especial de KVision que convierte esta interfaz en un servicio remoto RPC.
// Esta anotación será procesada por KVision para generar automáticamente las rutas en el backend
// y el cliente en el frontend para consumir el servicio sin escribir código REST explícito.

@KVService
// 🧠 Anotación de KVision que marca esta interfaz como un servicio remoto.
// KVision generará:
// - Los endpoints necesarios en el backend (basado en Ktor).
// - El cliente para el frontend (proxy JS/TS) que llama a estas funciones usando fetch internamente.

interface IPingService {
    // 📡 Interfaz que define el contrato del servicio entre frontend y backend.
    // Aquí defines las funciones que quieres exponer al cliente de forma remota.

    suspend fun ping(message: String): String
    // ⏳ `suspend`: indica que esta función es asincrónica, ideal para operaciones de red.
    // 📥 Recibe un mensaje tipo String desde el frontend.
    // 📤 Devuelve un String como respuesta desde el backend.
    // KVision se encargará de convertir esta firma en una llamada remota HTTP.
}
