package br.com.zupacademy.deleta

import br.com.zupacademy.DeletaChavePixRequest
import br.com.zupacademy.DeletaChavePixServiceGrpc.DeletaChavePixServiceBlockingStub
import br.com.zupacademy.shared.constraints.ValidUUID
import io.grpc.Status.Code.NOT_FOUND
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.PathVariable
import javax.inject.Inject
import javax.validation.constraints.NotBlank

@Controller("/api/v1/chave")
class DeletaChavePixController(@Inject val deletaChaveStub: DeletaChavePixServiceBlockingStub) {

    @Delete("/{id}")
    fun deletaChave(
        @PathVariable("id") @NotBlank @ValidUUID id: String,
        @Body("clienteId") @NotBlank @ValidUUID clienteId: String
    ): HttpResponse<Unit> {
        return try {
            val request = DeletaChavePixRequest.newBuilder()
                .setChavePixId(id)
                .setClienteId(clienteId)
                .build()
            deletaChaveStub.deleta(request)
            HttpResponse.noContent()
        } catch (e: StatusRuntimeException) {
            when (e.status.code) {
                NOT_FOUND -> HttpResponse.notFound()
                else -> HttpResponse.serverError()
            }
        } catch (e: Throwable) {
            HttpResponse.serverError()
        }
    }
}