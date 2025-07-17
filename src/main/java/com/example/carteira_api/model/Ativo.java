package com.example.carteira_api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ativo {

    private String nomeAtivo;
    private double preco;
    private int quantidade;
    private double totalInvestido;

    public Ativo(){
    }

    public Ativo(String nomeAtivo, double preco, int quantidade) {
        this.nomeAtivo = nomeAtivo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.totalInvestido = preco * quantidade;
    }

}
