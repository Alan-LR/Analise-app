package com.pieropan.analisecredito.listener;

import com.pieropan.analisecredito.domain.Proposta;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class PropostaEmAnaliseListener {

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaEmAnalise(Proposta proposta){
        if (Objects.isNull(proposta) || Objects.isNull(proposta.getUsuario()) || Objects.isNull(proposta.getUsuario().getNome())) {
            System.err.println("Proposta ou usuário nulo: " + proposta);
            return;
        }

    }
}
