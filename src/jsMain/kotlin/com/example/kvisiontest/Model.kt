package com.example.kvisiontest
import kotlinx.coroutines.await
import io.kvision.remote.getService
import kotlinx.browser.window
object Model {
    suspend fun ping(message: String): String {
        val response = window.fetch("/api/saludo").await()
        return response.text().await()
    }
}
