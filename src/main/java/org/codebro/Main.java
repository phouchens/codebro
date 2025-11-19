
package org.codebro;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Map<String, Function<Properties, APIClient>> clientFactories = new HashMap<>();
        clientFactories.put("anthropic",
                (props) -> new AnthropicSDKClient(getRequiredProperty(props, "anthropic.api.key")));
        clientFactories.put("gemini", (props) -> {
            String location = getRequiredProperty(props, "gemini.location");
            String projectId = getRequiredProperty(props, "gemini.project.id");
            String modelName = props.getProperty("gemini.model.name", "gemini-2.5-pro");
            return new GeminiSDKClient(projectId, location, modelName);
        });

        try {
            Properties props = new Properties();
            try (InputStream input = Main.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {
                props.load(input);
            }

            String provider = props.getProperty("provider", "anthropic");

            APIClient apiClient = clientFactories.getOrDefault(provider.toLowerCase(), (p) -> {
                throw new IllegalArgumentException("Unknown provider: " + provider);
            }).apply(props);

            CodeBro codeBro = new CodeBro(apiClient, new PromptBuilder());
            CodeBroCLI cli = new CodeBroCLI(codeBro);
            cli.start();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static String getRequiredProperty(Properties props, String key) {
        String value = props.getProperty(key);
        if (value == null || value.isEmpty()) {
            throw new RuntimeException("Missing required property: " + key);
        }
        return value;
    }
}
