package com.example.carteira_api.service;

import com.example.carteira_api.dto.AtivoResponseDto;
import lombok.Getter;
import com.example.carteira_api.model.Ativo;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class AtivoService {

    private final List<Ativo> ativos = List.of(
            new Ativo("JURO11", 121.25,10),
            new Ativo("MXRF11", 28.10,20)
    );

    public  AtivoResponseDto getAllActiveforName(String nomeAtivo) {
        return ativos.stream()
                .filter(ativo -> ativo.getNomeAtivo().equalsIgnoreCase(nomeAtivo))
                .findFirst()
                .map(ativo -> new AtivoResponseDto(
                        ativo.getNomeAtivo(),
                        ativo.getPreco(),
                        ativo.getQuantidade(),
                        ativo.getTotalInvestido()
                ))
                .orElse(null);
    }
}
//private final List<Ativo> ativos = List.of(
//            new Ativo("JURO11", 121.25,10),
//            new Ativo("MXRF11", 28.10,20)
//    );
//
//    public  AtivoResponseDto getAllActiveforName(String nomeAtivo) {
//        return ativos.stream()
//                .filter(ativo -> ativo.getNomeAtivo().equalsIgnoreCase(nomeAtivo))
//                .findFirst()
//                .map(ativo -> new AtivoResponseDto(
//                        ativo.getNomeAtivo(),
//                        ativo.getPreco(),
//                        ativo.getQuantidade(),
//                        ativo.getTotalInvestido()
//                ))
//                .orElse(null);
//
//    }