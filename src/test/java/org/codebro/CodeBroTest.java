package org.codebro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CodeBroTest {

    @Mock
    private APIClient mockApiClient;

    @Mock
    private PromptBuilder mockPromptBuilder;

    private CodeBro codeBro;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.codeBro = new CodeBro(mockApiClient, mockPromptBuilder);
    }

    @Test
    public void testSolveProblem_CallsPromptBuilder() throws Exception {
        // Arrange
        String problem = "Test problem";
        String mockPrompt = "Mock prompt";
        String mockResponse = "Mock response";

        when(mockPromptBuilder.buildTutorialPrompt(problem)).thenReturn(mockPrompt);
        when(mockApiClient.sendMessage(mockPrompt)).thenReturn(mockResponse);

        // Act
        codeBro.solveProblem(problem);

        // Assert
        verify(mockPromptBuilder).buildTutorialPrompt(problem);
    }

    @Test
    public void testSolveProblem_CallsAPIClient() throws Exception {
        // Arrange
        String problem = "Test problem";
        String mockPrompt = "Mock prompt";
        String mockResponse = "Mock response";

        when(mockPromptBuilder.buildTutorialPrompt(problem)).thenReturn(mockPrompt);
        when(mockApiClient.sendMessage(mockPrompt)).thenReturn(mockResponse);

        // Act
        codeBro.solveProblem(problem);

        // Assert
        verify(mockApiClient).sendMessage(mockPrompt);
    }

    @Test
    public void testSolveProblem_ReturnsAPIResponse() throws Exception {
        String problem = "Test problem";
        String mockPrompt = "Mock prompt";
        String expectedResponse = "This is the solution...";

        when(mockPromptBuilder.buildTutorialPrompt(problem)).thenReturn(mockPrompt);
        when(mockApiClient.sendMessage(mockPrompt)).thenReturn(expectedResponse);

        // Act
        String actualResponse = codeBro.solveProblem(problem);

        // Assert
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testSolveProblem_PassesPromptToAPIClient() throws Exception {
        // Arrange
        String problem = "Two Sum Problem";
        String expectedPrompt = "You are a coding tutor...";

        when(mockPromptBuilder.buildTutorialPrompt(problem)).thenReturn(expectedPrompt);
        when(mockApiClient.sendMessage(expectedPrompt)).thenReturn("Response");

        // Act
        codeBro.solveProblem(problem);

        // Assert - verify the exact prompt was passed
        verify(mockApiClient).sendMessage(expectedPrompt);
    }
}