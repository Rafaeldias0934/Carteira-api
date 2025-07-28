package com.example.carteira_api.controller;

import com.example.carteira_api.service.AtivoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.carteira_api.service.SheetsService;

@RequestMapping("/v1/carteira/ativo")
@RestController
public class AtivoController {

    private final AtivoService service;

    public AtivoController(AtivoService service){
        this.service = service;
    }

    @GetMapping("/{nomeAtivo}")
    public ResponseEntity<?> getAtivo (@PathVariable String nomeAtivo)  {
        try {
            var dto = service.getDataSheets(nomeAtivo);
            return  dto == null ? ResponseEntity.notFound().build()
                                : ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
