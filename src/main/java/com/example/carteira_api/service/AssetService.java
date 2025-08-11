package com.example.carteira_api.service;
import com.example.carteira_api.dto.AssetResponseDTO;
import com.example.carteira_api.dto.PairInvestments;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    private final SheetsService sheetsService;

    public AssetService(SheetsService  sheetsService) {
        this.sheetsService = sheetsService;
    }

    private boolean isRowOfAsset(List<Object> row, String assetName) {
        if (row.size() < 3) return false;
        String ticker = row.get(0).toString().trim();
        return ticker.equalsIgnoreCase(assetName);
    }

    private Optional<PairInvestments> convertRow(List<Object> row) {
        try {
            String unparsedValue = row.get(1).toString();
            String unparsedQuantity = row.get(2).toString().trim();

            String parsedValue = unparsedValue
                    .replace("R$", "")
                    .replaceAll("[^\\d.,]", "")
                    .replace(",", ".")
                    .trim();

            int quantity = Integer.parseInt(unparsedQuantity);
            double total = Double.parseDouble(parsedValue);

            return  Optional.of(new PairInvestments(quantity, total));
        } catch (NumberFormatException e) {
            System.out.println("Error while converting row values: " + e.getMessage());
            return Optional.empty();
        }
    }

    public AssetResponseDTO getDataSheets(String assetName) throws Exception {
        var rows = sheetsService.readData();
        int quantityTotal = 0;
        double total_invested = 0;

        for (List<Object> row : rows) {
            if (!isRowOfAsset(row, assetName)) continue;

            var investmentOpt = convertRow(row);
            if (investmentOpt.isEmpty()) continue;

            var  investment = investmentOpt.get();
            quantityTotal += investment.quantity();
            total_invested += investment.total();
        }

        if (quantityTotal == 0) return null;

        BigDecimal average_price = BigDecimal.valueOf(total_invested)
                .divide(BigDecimal.valueOf(quantityTotal),2, RoundingMode.HALF_UP);

        return new AssetResponseDTO(assetName.toUpperCase(), total_invested, quantityTotal, average_price);
    }
}

