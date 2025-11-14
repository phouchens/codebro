package org.codebro;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClaudeAPIClient implements APIClient {
    private static final String API_URL = "https://api.anthropic.com/v1/messages";
    private static final String MODEL = "claude-sonnet-4-20250514";

    private final String apiKey;
    private final HttpClient httpClient;
    private final Gson gson;

    public ClaudeAPIClient(String apiKey, HttpClient httpClient) {
        this.apiKey = apiKey;
        this.httpClient = httpClient;
        this.gson = new Gson();
    }

    public ClaudeAPIClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder().build();
        gson = new Gson();
    }

    public String sendMessage(String userMessage) throws IOException, InterruptedException {
        JsonObject requestJson = new JsonObject();
        requestJson.addProperty("model", ClaudeAPIClient.MODEL);
        requestJson.addProperty("max_tokens", 4096);

        JsonArray messages = new JsonArray();
        JsonObject message = new JsonObject();
        message.addProperty("role", "user");
        message.addProperty("content", userMessage);
        messages.add(message);
        requestJson.add("messages", messages);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ClaudeAPIClient.API_URL))
                .header("x-api-key", apiKey)
                .header("anthropic-version", "2023-06-01")
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(requestJson)))
                .build();

        HttpResponse<String> response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        if (response.statusCode() != 200) {
            throw new IOException("API error: " + response.body());
        }

        JsonObject responseJson = gson.fromJson(response.body(), JsonObject.class);
        return responseJson.getAsJsonArray("content")
                .get(0).getAsJsonObject()
                .get("text").getAsString();
    }
}
