package org.codebro;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.models.messages.*;

import java.io.IOException;
import java.util.Collections;

public class AnthropicSDKClient implements APIClient {
    private final AnthropicClient client;
    private final String model;

    public AnthropicSDKClient(String apiKey, String model) {
        this.client = AnthropicOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
        this.model = model;
    }

    AnthropicSDKClient(AnthropicClient client, String model) {
        this.client = client;
        this.model = model;
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
                    .maxTokens(4096L)
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
}
