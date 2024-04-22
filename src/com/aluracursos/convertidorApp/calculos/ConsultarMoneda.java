package com.aluracursos.convertidorApp.calculos;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMoneda {

    public double tipoCambio(String fromCurrency, String toCurrency, double amount) {
        String url = "https://v6.exchangerate-api.com/v6/741f061b4c2b9bf6b129970f/pair/" + fromCurrency + "/" + toCurrency + "/" + amount;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();
            JSONObject jsonObject = new JSONObject(jsonResponse);
            double result = jsonObject.getDouble("conversion_result");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener el tipo de cambio: " + e.getMessage());
        }
    }
}
