package com.pieropan.analisecredito.service.impl;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OutrosEmprestimosEmAndamento implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return outrosEmpretimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmpretimosEmAndamento(){
        return new Random().nextBoolean();
    }
}
