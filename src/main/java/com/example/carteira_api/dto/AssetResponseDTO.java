package com.example.carteira_api.dto;

import java.math.BigDecimal;

public record AssetResponseDTO(
        String assetName,
        double totalInvested,
        int quantity,
        BigDecimal averagePrice
) {}