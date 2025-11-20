package org.codebro;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.*;

import java.io.IOException;
import java.util.Collections;

public class AnthropicSDKClient implements APIClient {
    private final AnthropicClient client;
    private final String model;
    private final long maxTokens;

    public AnthropicSDKClient(String apiKey, String model, long maxTokens) {
        this.client = AnthropicOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
        this.model = model;
        this.maxTokens = maxTokens;
    }

    AnthropicSDKClient(AnthropicClient client, String model, long maxTokens) {
        this.client = client;
        this.model = model;
        this.maxTokens = maxTokens;
    }

    @Override
    public String sendMessage(String prompt) throws IOException {
        try {
            MessageParam messageParam = MessageParam.builder()
                    .role(MessageParam.Role.USER)
                    .content(prompt)
                    .build();

            MessageCreateParams params = MessageCreateParams.builder()
                    .model(model)
                    .maxTokens(maxTokens)
                    .messages(Collections.singletonList(messageParam))
                    .build();
            Message message = client.messages().create(params);

            return message.content().stream()
                    .filter(ContentBlock::isText)
                    .map(block -> block.asText().text())
                    .findFirst()
                    .orElseThrow(() -> new IOException("Message not found"));
        } catch (Exception e) {
            throw new IOException("SDK Error: " + e.getMessage(), e);
        }

    }

    @Override
    public void close() throws Exception {
        this.client.close();
    }
}
