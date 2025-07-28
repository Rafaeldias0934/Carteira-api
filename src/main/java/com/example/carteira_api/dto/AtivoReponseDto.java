package com.example.carteira_api.dto;

import java.math.BigDecimal;

public record AtivoReponseDto(
        String nomeAtivo,
        double totalInvestido,
        int quatidade,
        BigDecimal precoMedio

) {}
