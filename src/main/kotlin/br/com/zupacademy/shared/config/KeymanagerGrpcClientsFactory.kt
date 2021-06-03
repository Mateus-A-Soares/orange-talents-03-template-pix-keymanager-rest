package br.com.zupacademy.shared.config

import br.com.zupacademy.CadastraChavePixServiceGrpc
import br.com.zupacademy.CadastraChavePixServiceGrpc.CadastraChavePixServiceBlockingStub
import br.com.zupacademy.ConsultaChavePixServiceGrpc
import br.com.zupacademy.ConsultaChavePixServiceGrpc.ConsultaChavePixServiceBlockingStub
import br.com.zupacademy.ConsultaChavesPixPorClienteServiceGrpc
import br.com.zupacademy.ConsultaChavesPixPorClienteServiceGrpc.ConsultaChavesPixPorClienteServiceBlockingStub
import br.com.zupacademy.DeletaChavePixServiceGrpc
import br.com.zupacademy.DeletaChavePixServiceGrpc.DeletaChavePixServiceBlockingStub
import io.grpc.ManagedChannel
import io.micronaut.context.annotation.Factory
import io.micronaut.grpc.annotation.GrpcChannel
import javax.inject.Singleton

@Suppress("unused")
@Factory
class KeymanagerGrpcClientsFactory(@GrpcChannel(CHANNEL_NAME) val channel: ManagedChannel) {

    companion object {
        const val CHANNEL_NAME = "keymanagerGrpcServer";
    }

    @Singleton
    fun cadastraClientStub(): CadastraChavePixServiceBlockingStub = CadastraChavePixServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun consultaClientStub(): ConsultaChavePixServiceBlockingStub = ConsultaChavePixServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun consultaPorClienteClientStub(): ConsultaChavesPixPorClienteServiceBlockingStub = ConsultaChavesPixPorClienteServiceGrpc.newBlockingStub(channel)

    @Singleton
    fun deletaClientStub(): DeletaChavePixServiceBlockingStub = DeletaChavePixServiceGrpc.newBlockingStub(channel)
}
