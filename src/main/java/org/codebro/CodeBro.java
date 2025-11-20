package org.codebro;

public class CodeBro {
    private final APIClient apiClient;
    private final PromptBuilder promptBuilder;

    public CodeBro(APIClient apiClient, PromptBuilder promptBuilder) {
        this.apiClient = apiClient;
        this.promptBuilder = promptBuilder;
    }

    public String solveProblem(String problemStatement) throws Exception {
        String prompt = this.promptBuilder.buildTutorialPrompt(problemStatement);
        return this.apiClient.sendMessage(prompt);
    }

}
