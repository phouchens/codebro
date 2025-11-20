package org.codebro;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.api.GenerateContentResponse;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.google.cloud.vertexai.generativeai.ResponseHandler;

public class GeminiSDKClient implements APIClient {

    private final String modelName;
    private final VertexAI vertexAI;

    public GeminiSDKClient(String projectId, String location, String modelName) {
        this.modelName = modelName;
        this.vertexAI = new VertexAI(projectId, location);
    }

    @Override
    public String sendMessage(String prompt) throws Exception {
        GenerativeModel model = new GenerativeModel(modelName, vertexAI);
        GenerateContentResponse response = model.generateContent(prompt);
        return ResponseHandler.getText(response);
    }

    @Override
    public void close() {
        vertexAI.close();
    }
}
