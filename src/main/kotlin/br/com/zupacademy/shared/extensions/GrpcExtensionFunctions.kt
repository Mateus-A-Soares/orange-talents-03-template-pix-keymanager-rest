package br.com.zupacademy.shared.extensions

import br.com.zupacademy.ChavePixCadastradaResponse
import br.com.zupacademy.registra.CadastraChaveResponse

fun ChavePixCadastradaResponse.toApiResponse(): CadastraChaveResponse {
    return CadastraChaveResponse(chavePixId)
}