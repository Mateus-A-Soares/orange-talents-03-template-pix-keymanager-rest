package br.com.zupacademy.shared.erroresponse

import io.micronaut.http.HttpStatus
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

open class DefaultErrorMessage(
    val message: String,
    private val statusCode: HttpStatus,
    private val timestamp: LocalDateTime = LocalDateTime.now()
) {

    companion object {
        @JvmStatic
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    }

    fun getStatusCode(): Int {
        return statusCode.code
    }

    fun getTimestamp(): String {
        return formatter.format(timestamp)
    }
}
