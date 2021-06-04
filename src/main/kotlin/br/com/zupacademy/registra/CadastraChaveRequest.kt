package br.com.zupacademy.registra

import br.com.zupacademy.CadastraChavePixRequest
import br.com.zupacademy.shared.constraints.ValidChavePix
import br.com.zupacademy.shared.constraints.ValidUUID
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank

@Introspected
@ValidChavePix
data class CadastraChaveRequest(
    @NotBlank @ValidUUID
    val clienteId: String,
    val tipoConta: TipoConta,
    val tipoChave: TipoChave,
    val valorChave: String
) {

    fun toGrpcRequest(): CadastraChavePixRequest {
        return CadastraChavePixRequest.newBuilder()
            .setClienteId(clienteId)
            .setTipoConta(tipoConta.toGrpcRequest())
            .setTipoChave(tipoChave.toGrpcRequest())
            .setValorChave(valorChave)
            .build()
    }
}
