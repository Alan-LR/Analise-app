package com.pieropan.analisecredito.service.impl;

import com.pieropan.analisecredito.domain.Proposta;
import com.pieropan.analisecredito.service.strategy.CalculoPonto;

import java.util.Random;

public class NomeNegativaImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if(nomeNegativado()){
            throw new RuntimeException("Nome negativado");
        }
        return 100;
    }

    private boolean nomeNegativado(){
        return new Random().nextBoolean();
    }
}
