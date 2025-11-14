package org.codebro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PromptBuilderTest {

    private PromptBuilder promptBuilder;

    @BeforeEach
    public void setUp() {
        promptBuilder = new PromptBuilder();
    }

    @Test
    public void testBuildTutorialPrompt_NotNull() {
        String problemStatement = "Test problem";
        String prompt = promptBuilder.buildTutorialPrompt(problemStatement);

        assertNotNull(prompt, "Prompt should not be null");
        assertFalse(prompt.isEmpty(), "Prompt should not be empty");
    }

    @Test
    public void testBuildTutorialPrompt_ContainsProblemStatement() {
        String problemStatement = "Given an array of integers, find two numbers that add up to a target.";
        String prompt = promptBuilder.buildTutorialPrompt(problemStatement);

        assertTrue(prompt.contains(problemStatement),
                "Prompt should contain the original problem statement");
    }

    @Test
    public void testBuildTutorialPrompt_ContainsRequiredSections() {
        String problemStatement = "Test problem";
        String prompt = promptBuilder.buildTutorialPrompt(problemStatement);

        // Check for all 5 required sections
        assertTrue(prompt.contains("PROBLEM ANALYSIS") || prompt.contains("Problem analysis"),
                "Prompt should mention problem analysis");
        assertTrue(prompt.contains("SOLUTION STRATEGY") || prompt.contains("Solution strategy"),
                "Prompt should mention solution strategy");
        assertTrue(prompt.contains("ALGORITHM") || prompt.contains("Algorithm"),
                "Prompt should mention algorithm");
        assertTrue(prompt.contains("IMPLEMENTATION") || prompt.contains("Implementation"),
                "Prompt should mention implementation");
        assertTrue(prompt.contains("TEST") || prompt.contains("Test"),
                "Prompt should mention tests");
    }

    @Test
    public void testBuildTutorialPrompt_HasInstructions() {
        String problemStatement = "Test problem";
        String prompt = promptBuilder.buildTutorialPrompt(problemStatement);

        // Should have some instructional text for Claude
        assertTrue(prompt.contains("tutor") || prompt.contains("expert") || prompt.contains("provide"),
                "Prompt should contain instructions for Claude");
    }

    @Test
    public void testBuildTutorialPrompt_HandlesMultilineProblem() {
        String problemStatement = """
            Two Sum Problem:
            Given an array of integers nums and an integer target,
            return indices of the two numbers such that they add up to target.
            """;

        String prompt = promptBuilder.buildTutorialPrompt(problemStatement);

        assertTrue(prompt.contains("Two Sum Problem"), "Should handle multiline problems");
        assertTrue(prompt.contains("array of integers"), "Should preserve problem content");
    }
}