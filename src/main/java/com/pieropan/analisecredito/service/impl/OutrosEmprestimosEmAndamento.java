package com.pieropan.analisecredito.service.impl;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.service.strategy.CalculoPonto;

import java.util.Random;

public class OutrosEmprestimosEmAndamento implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return outrosEmpretimosEmAndamento() ? 0 : 80;
    }

    private boolean outrosEmpretimosEmAndamento(){
        return new Random().nextBoolean();
    }
}
