package araujo.pablwo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HttpService {
    private static final String URL = "https://v6.exchangerate-api.com/v6/";
    private static final String APIKEY = System.getenv("API_KEY");

    public ConversionResult getConversionResult(String baseCode, String targetCode, double amount) {
        String address = URL + APIKEY + "/pair/" + baseCode + "/" + targetCode + "/" + amount;
        URI uri = URI.create(address);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).build();

        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Não foi possível fazer a requisição a API");
        }

        String json = response.body();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        ConversionResult conversionResult = gson.fromJson(json, ConversionResult.class);

        if (Objects.equals(conversionResult.result(), "success")) {
            return conversionResult;
        } else {
            throw new RuntimeException("A requisição a API falhou");
        }
    }
}
