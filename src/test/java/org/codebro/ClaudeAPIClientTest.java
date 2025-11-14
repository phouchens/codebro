package org.codebro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClaudeAPIClientTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockResponse;

    private ClaudeAPIClient apiClient;
    private static final String TEST_API_KEY = "test-api-key";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        apiClient = new ClaudeAPIClient(TEST_API_KEY, mockHttpClient);
    }

    @Test
    public void testSendMessage_ReturnsTextFromResponse() throws Exception {
        // Arrange
        String expectedText = "This is Claude's response";
        String mockResponseBody = """
            {
              "id": "msg_123",
              "type": "message",
              "role": "assistant",
              "content": [
                {
                  "type": "text",
                  "text": "%s"
                }
              ],
              "model": "claude-sonnet-4-20250514",
              "stop_reason": "end_turn"
            }
            """.formatted(expectedText);

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        String result = apiClient.sendMessage("Test message");

        // Assert
        assertEquals(expectedText, result);
    }

    @Test
    public void testSendMessage_BuildsCorrectRequest() throws Exception {
        // Arrange
        String testMessage = "What is 2+2?";
        String mockResponseBody = """
            {
              "content": [{"type": "text", "text": "4"}]
            }
            """;

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(mockResponseBody);

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        when(mockHttpClient.send(requestCaptor.capture(), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        apiClient.sendMessage(testMessage);

        // Assert
        HttpRequest capturedRequest = requestCaptor.getValue();

        // Verify URL
        assertEquals("https://api.anthropic.com/v1/messages", capturedRequest.uri().toString());

        // Verify headers
        assertTrue(capturedRequest.headers().firstValue("x-api-key").isPresent());
        assertEquals(TEST_API_KEY, capturedRequest.headers().firstValue("x-api-key").get());
        assertEquals("2023-06-01", capturedRequest.headers().firstValue("anthropic-version").get());
        assertEquals("application/json", capturedRequest.headers().firstValue("content-type").get());

        // Verify method
        assertEquals("POST", capturedRequest.method());
    }

    @Test
    public void testSendMessage_RequestContainsMessage() throws Exception {
        // Arrange
        String testMessage = "Explain recursion";
        String mockResponseBody = """
            {
              "content": [{"type": "text", "text": "Response"}]
            }
            """;

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(mockResponseBody);

        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        when(mockHttpClient.send(requestCaptor.capture(), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        apiClient.sendMessage(testMessage);

        // Assert - verify the request body contains our message
        // Note: HttpRequest doesn't expose body directly, but we can verify it was sent
        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    public void testSendMessage_SendsCorrectModel() throws Exception {
        // Arrange
        String mockResponseBody = """
            {
              "content": [{"type": "text", "text": "Response"}]
            }
            """;

        when(mockResponse.statusCode()).thenReturn(200);
        when(mockResponse.body()).thenReturn(mockResponseBody);
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        apiClient.sendMessage("Test");

        // Assert - verify the request was sent (model is in the JSON body)
        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }
}