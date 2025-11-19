package org.codebro;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

public class GeminiSDKClient implements APIClient {

    private final String projectId;
    private final String location;
    private final String modelName;

    public GeminiSDKClient(String projectId, String location, String modelName) {
        this.projectId = projectId;
        this.location = location;
        this.modelName = modelName;
    }

    @Override
    public String sendMessage(String prompt) throws Exception {
        try (VertexAI vertexAI = new VertexAI(projectId, location)) {
            GenerativeModel model = new GenerativeModel(modelName, vertexAI);
            GenerateContentResponse response = model.generateContent(prompt);
            return ResponseHandler.getText(response);
        }
    }
}
