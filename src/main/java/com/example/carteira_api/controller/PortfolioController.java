package com.example.carteira_api.controller;

import com.example.carteira_api.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/portfolio/asset")
@RestController
public class PortfolioController {

    private final AssetService assetService;

    public PortfolioController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/{assetName}")
    public ResponseEntity<?> getAsset (@PathVariable String assetName) {
        try {
            var dto = assetService.getDataSheets(assetName);
            return dto == null ? ResponseEntity.notFound().build()
                               : ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
