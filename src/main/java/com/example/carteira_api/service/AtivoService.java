package com.example.carteira_api.service;

import com.example.carteira_api.dto.AtivoReponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Service
public class AtivoService {

    private final SheetsService sheetsService;

    public AtivoService(SheetsService  sheetsService) {
        this.sheetsService = sheetsService;
    }

    public AtivoReponseDto getDataSheets(String nomeAtivo) throws Exception {
        var linhas = sheetsService.readData();


        int qtdeTotal = 0;
        double valorTotal = 0;
        BigDecimal precoMedio;


        for (List<Object> row : linhas) {
            if (row.size() < 3) continue;

            String ticker = row.get(0).toString().trim();


            if (!ticker.equalsIgnoreCase(nomeAtivo)) continue;

            try {
            String valorStrOriginal = row.get(1).toString();
            String quantidadeStr = row.get(2).toString().trim();


                String valorStr = valorStrOriginal
                        .replace("R$", "")
                        .replaceAll("[^\\d.,]", "")
                        .replace(",", ".")
                        .trim();


                int quantidade = Integer.parseInt(quantidadeStr);

                double valor = Double.parseDouble(valorStr);

                qtdeTotal += quantidade;
                valorTotal += valor ;
            } catch (NumberFormatException e) {
                System.out.println("Erro ao converter valores: " + e.getMessage());
            }
        }
        if (qtdeTotal == 0) return null;
        precoMedio = BigDecimal.valueOf(valorTotal)
                .divide(BigDecimal.valueOf(qtdeTotal),2, RoundingMode.HALF_UP);

        return  new AtivoReponseDto(
                nomeAtivo.toUpperCase(),
                valorTotal,
                qtdeTotal,
               precoMedio
        );
    }

}
