package com.example.carteira_api.service;

import com.example.carteira_api.dto.AtivoReponseDto;
import org.springframework.stereotype.Service;

@Service
public class AtivoService {

    private final SheetsService sheetsService;

    public AtivoService(SheetsService  sheetsService) {
        this.sheetsService = sheetsService;
    }

    public AtivoReponseDto getDataSheets(String nomeAtivo) throws Exception {
        var linhas = sheetsService.readData();
        int qtde = 0;
        double somatorio = 0;

        for (var row : linhas) {
            if (row.size() < 3) continue;
            String ticker = row.get(0).toString();
            if (!ticker.equalsIgnoreCase(nomeAtivo)) continue;

            int q = Integer.parseInt(row.get(1).toString());
            double summation = Double.parseDouble(row.get(2).toString()
                    .replace("R$", "").replace(",", "."));
            qtde += q;
            somatorio += summation * q;
        }
        if (qtde == 0) return null;
        return  new AtivoReponseDto(
                nomeAtivo.toUpperCase(),
                qtde,
                somatorio / qtde,
                somatorio
        );


    }

}
