package org.codebro;

public class PromptBuilder {
   public PromptBuilder() {
   }

   public String buildTutorialPrompt(String problemStatement) {
      return """
                You are an expert coding tutor. Analyze the following problem and provide a comprehensive tutorial.

                Structure your response with these sections:

                1. PROBLEM ANALYSIS
                   - Restate the problem
                   - Identify constraints
                   - Determine inputs/outputs

                2. SOLUTION STRATEGY
                   - Multiple approaches
                   - Trade-offs
                   - Best approach and why

                3. ALGORITHM DESIGN
                   - Step-by-step algorithm
                   - Time and space complexity

                4. IMPLEMENTATION
                   - Complete working Java code
                   - Comments and edge cases

                5. TEST CASES
                   - JUnit test code
                   - Example cases and edge cases

            IMPORTANT: When providing code, you MUST use the following format for every file:

            [FILE: Filename.java]
            ```java
            // code here
            ```
            [END_FILE]

            Here's the problem:

            """ + problemStatement;
   }
}
