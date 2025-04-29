package com.example.kvisiontest

// Esta anotación suprime la advertencia del compilador de que se está usando "actual" sin un "expect" correspondiente.
// En un proyecto multiplataforma, normalmente definirías una interfaz o clase esperada (`expect`) en `commonMain`
// y luego implementaciones reales (`actual`) en `jvmMain` y/o `jsMain`.
@Suppress("ACTUAL_WITHOUT_EXPECT")
actual class PingService : IPingService {

    // Esta función sobrescribe el método `ping` definido en la interfaz `IPingService`.
    // Es `suspend` porque puede ser llamada desde una corrutina y permite operaciones asincrónicas.
    override suspend fun ping(message: String): String {
        // Imprime el mensaje recibido en la consola del backend (servidor).
        println("Mensaje recibido desde el frontend: $message")

        // Devuelve una respuesta que será enviada de vuelta al frontend.
        return "Respuesta del backend: $message"
    }
}
