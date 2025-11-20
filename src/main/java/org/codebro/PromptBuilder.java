package org.codebro;

public class PromptBuilder {
   private final String template;

   public PromptBuilder() {
      try {
         this.template = new String(getClass().getClassLoader()
               .getResourceAsStream("prompts/tutorial.txt").readAllBytes());
      } catch (Exception e) {
         throw new RuntimeException("Failed to load prompt template", e);
      }
   }

   public String buildTutorialPrompt(String problemStatement) {
      return template.replace("{{PROBLEM}}", problemStatement);
   }
}
