package org.codebro;

public class PromptBuilder {
   private final String template;

   public PromptBuilder() {
      try {
         var stream = getClass().getClassLoader().getResourceAsStream("prompts/tutorial.txt");
         if (stream == null) {
            throw new RuntimeException("Prompt template file 'prompts/tutorial.txt' not found in resources");
         }
         this.template = new String(stream.readAllBytes());
      } catch (Exception e) {
         throw new RuntimeException("Failed to load prompt template", e);
      }
   }

   public String buildTutorialPrompt(String problemStatement) {
      return template.replace("{{PROBLEM}}", problemStatement);
   }
}
