package cl.prezdev.http;

import cl.prezdev.model.response.AddAvlResponse;
import jakarta.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Component;

@Component
public class AvlClient {

    private HttpClient client;
    private ObjectMapper objectMapper;
    private String baseUrl;

    @PostConstruct
    public void init() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.baseUrl = "http://localhost:8080";
    }

    public AddAvlResponse addAvls(String type, int count) {
        try {
            String url = baseUrl + "/api/avl/add?type=" + type + "&count=" + count;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .POST(HttpRequest.BodyPublishers.noBody())
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return objectMapper.readValue(response.body(), AddAvlResponse.class);
            } else {
                throw new RuntimeException("Request failed with status: " + response.statusCode());
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error during HTTP request", e);
        }
    }
}

