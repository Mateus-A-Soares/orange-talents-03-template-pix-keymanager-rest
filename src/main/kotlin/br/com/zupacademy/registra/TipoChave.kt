package br.com.zupacademy.registra

import io.micronaut.validation.validator.constraints.EmailValidator
import br.com.zupacademy.TipoChaveRequest

enum class TipoChave(val validate: (String) -> Boolean) {
    CPF(validate@{ value ->
        value ?: return@validate false
        value.matches("^[0-9]{11}\$".toRegex())
    }),
    TELEFONE(validate@{ value ->
        value ?: return@validate false
        value.matches("\\+[1-9][0-9]\\d{1,14}".toRegex())
    }),
    EMAIL(validate@{ value ->
        if (value.isNullOrBlank()) return@validate false
        EmailValidator().run {
            initialize(null)
            return@validate isValid(value, null)
        }
    }),
    ALEATORIA(validate = { value ->
        value.isNullOrBlank()
    });

    fun toGrpcRequest(): TipoChaveRequest {
        return when (this) {
            CPF -> TipoChaveRequest.TIPO_CHAVE_CPF
            EMAIL -> TipoChaveRequest.TIPO_CHAVE_EMAIL
            TELEFONE -> TipoChaveRequest.TIPO_CHAVE_TELEFONE
            ALEATORIA -> TipoChaveRequest.TIPO_CHAVE_ALEATORIA

        }
    }
}
