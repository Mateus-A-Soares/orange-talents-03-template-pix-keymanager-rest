package br.com.zupacademy.registra

import br.com.zupacademy.TipoContaRequest

enum class  TipoConta {
    CORRENTE, POUPANCA;

    fun toGrpcRequest(): TipoContaRequest {
        return when (this) {
            CORRENTE -> TipoContaRequest.TIPO_CONTA_CORRENTE
            POUPANCA -> TipoContaRequest.TIPO_CONTA_POUPANCA
        }
    }
}
