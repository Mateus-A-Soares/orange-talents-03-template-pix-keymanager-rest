package br.com.zupacademy.registra

import br.com.zupacademy.CadastraChavePixRequest
import br.com.zupacademy.CadastraChavePixServiceGrpc.CadastraChavePixServiceBlockingStub
import br.com.zupacademy.ChavePixCadastradaResponse
import br.com.zupacademy.shared.erroresponse.DefaultErrorMessage
import br.com.zupacademy.shared.extensions.toApiResponse
import io.grpc.Status.Code.*
import io.grpc.StatusRuntimeException
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import javax.inject.Inject

@Controller("/api/v1/chave")
class RegistraChaveController(@Inject val cadastraChaveStub: CadastraChavePixServiceBlockingStub) {

    @Post
    @Validated
    fun registraChave(request: CadastraChaveRequest): HttpResponse<Any?>? {
        try {
            val grpcRequest: CadastraChavePixRequest = request.toGrpcRequest()
            val grpcResponse: ChavePixCadastradaResponse = cadastraChaveStub.registra(grpcRequest)
            return HttpResponse.ok(grpcResponse.toApiResponse())
        } catch (e: StatusRuntimeException) {
            with(e.status) {
                return when (code) {
                    ALREADY_EXISTS, INVALID_ARGUMENT, FAILED_PRECONDITION -> HttpResponse.unprocessableEntity<DefaultErrorMessage>()
                        .body(
                            DefaultErrorMessage(
                                message = description ?: "",
                                statusCode = HttpStatus.UNPROCESSABLE_ENTITY
                            )
                        )
                    else -> HttpResponse.serverError()
                }
            }
        }
    }
}
