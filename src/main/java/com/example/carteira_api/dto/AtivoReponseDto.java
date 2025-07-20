package com.example.carteira_api.dto;

public record AtivoReponseDto(
        String nomeAtivo,
        int quatidade,
        double precoMedio,
        double totalInvestido
) {}
