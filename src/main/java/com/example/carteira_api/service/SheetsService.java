package com.example.carteira_api.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.time.temporal.ValueRange;
import java.util.Collections;
import java.util.List;

@Getter
@Service
public class SheetsService {

    private static final String APPLICATION_NAME = "Control Invest API Java";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FILE_PATH = "src/main/resources/google/credentials.json";
    private static final String ID = "1uwM3sN4n6sY1Xq44sYDOUUH8NdsjdXU4usbiy5QGcVc";
    private static final String RANGE = "Resumo Geral!A1:D3";

    private Sheets getSheetsService() throws Exception {
        InputStream inputStream = new FileInputStream(CREDENTIALS_FILE_PATH);
        if (inputStream == null) {
            throw new RuntimeException("File not found: " + CREDENTIALS_FILE_PATH);
        }


        GoogleCredentials credentials = GoogleCredentials.fromStream(inputStream)
                .createScoped(List.of(SheetsScopes.SPREADSHEETS_READONLY));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JSON_FACTORY,
                new HttpCredentialsAdapter(credentials)
        ).setApplicationName(APPLICATION_NAME).build();

    }

    public List<List<Object>> readData()throws Exception {
        return  getSheetsService().spreadsheets()
                .values()
                .get(ID, RANGE)
                .execute()
                .getValues();

    }

}
