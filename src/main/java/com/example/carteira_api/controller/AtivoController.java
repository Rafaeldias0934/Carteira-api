package com.example.carteira_api.controller;

import com.example.carteira_api.dto.AtivoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carteira_api.service.AtivoService;

@RequestMapping("/v1/carteira/ativo")
@RestController
public class AtivoController {

    private final AtivoService service;

    public AtivoController(){
        this.service = new AtivoService();
    }

    @GetMapping("/{nomeAtivo}")
    public ResponseEntity<AtivoResponseDto> getAtivo (@PathVariable String nomeAtivo)  {
        var ativo = service.getAllActiveforName(nomeAtivo);
        if (ativo == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(ativo);
    }
}
