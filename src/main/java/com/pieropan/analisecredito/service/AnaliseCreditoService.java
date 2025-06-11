package com.pieropan.analisecredito.service;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.exceptions.StrategyException;
import com.pieropan.analisecredito.service.strategy.CalculoPonto;
import com.pieropan.analisecredito.service.strategy.NotificacaoRabbitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {

    private List<CalculoPonto> calculoPontoList;

    private NotificacaoRabbitService notificacaoRabbitService;

    @Value("${rabbitmq.propostaconcluida.exchange}")
    private String exchangePropostaConcluida;

    public AnaliseCreditoService(List<CalculoPonto> calculoPontoList, NotificacaoRabbitService notificacaoRabbitService) {
        this.calculoPontoList = calculoPontoList;
        this.notificacaoRabbitService = notificacaoRabbitService;
    }

    public void analisar(Proposta proposta) {
        try {
            int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovado(pontos > 350);
        } catch (StrategyException ex) {
            proposta.setAprovado(false);
            proposta.setObservacao(ex.getMessage());
        }
        notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
    }
}
