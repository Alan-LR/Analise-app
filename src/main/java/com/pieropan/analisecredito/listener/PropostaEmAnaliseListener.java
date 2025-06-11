package com.pieropan.analisecredito.listener;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.service.AnaliseCreditoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class PropostaEmAnaliseListener {

    private final AnaliseCreditoService analiseCreditoService;

    public PropostaEmAnaliseListener(AnaliseCreditoService analiseCreditoService) {
        this.analiseCreditoService = analiseCreditoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaEmAnalise(Proposta proposta) {
        if (Objects.isNull(proposta) || Objects.isNull(proposta.getUsuario()) || Objects.isNull(proposta.getUsuario().getNome())) {
            System.err.println("Proposta ou usuário nulo: " + proposta);
            return;
        }

        analiseCreditoService.analisar(proposta);

    }
}
