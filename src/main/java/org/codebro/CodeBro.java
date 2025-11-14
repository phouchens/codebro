package org.codebro;


public class CodeBro {
    private final APIClient claudeAPIClient;
    private final PromptBuilder promptBuilder;

    public CodeBro(String apiKey) {
        this(new ClaudeAPIClient(apiKey), new PromptBuilder());
    }

    public CodeBro(APIClient apiClient, PromptBuilder promptBuilder) {
        this.claudeAPIClient = apiClient;
        this.promptBuilder = promptBuilder;
    }

    public String solveProblem(String problemStatement) throws Exception {
        String prompt = this.promptBuilder.buildTutorialPrompt(problemStatement);
        return this.claudeAPIClient.sendMessage(prompt);
    }

}
