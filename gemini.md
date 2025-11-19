# Gemini Project Overview: CodeBro

## Project Description

CodeBro is an interactive CLI tool that helps users solve LeetCode-style coding problems. It takes a problem description as input and, using the Anthropic Claude AI model, generates a detailed tutorial that includes problem analysis, solution strategy, algorithm design, a Java implementation, and comprehensive test cases.

## Getting Started

### Prerequisites

*   Java 21 or higher
*   Maven 3.6+
*   An Anthropic API key

### Setup & Execution

1.  **Configure API Key**: Create a file named `src/main/resources/config.properties` with the following content:
    ```properties
    anthropic.api.key=your-api-key-here
    ```

2.  **Build the project**:
    ```bash
    mvn clean install
    ```

3.  **Run the application**:
    ```bash
    mvn exec:java -Dexec.mainClass="org.codebro.Main"
    ```

4.  **Run tests**:
    ```bash
    mvn test
    ```

## Key Components

*   `src/main/java/org/codebro/Main.java`: The main entry point for the CLI application. It handles user input, interacts with the `CodeBro` class, and prints the results to the console.
*   `src/main/java/org/codebro/CodeBro.java`: The core class of the application. It orchestrates the process of solving a problem by using a `PromptBuilder` to create a prompt and an `APIClient` to communicate with the Anthropic API.
*   `src/main/java/org/codebro/APIClient.java`: An interface for API clients, allowing for different AI providers to be used.
*   `src/main/java/org/codebro/AnthropicSDKClient.java`: The implementation of the `APIClient` interface that uses the official Anthropic Java SDK to communicate with the Claude API.
*   `src/main/java/org/codebro/PromptBuilder.java`: A class responsible for constructing the detailed prompt sent to the AI model.
*   `pom.xml`: The Maven project file, which defines project dependencies, build plugins, and other project settings.

## Usage

The application runs in an interactive loop:

1.  Paste a coding problem into the console.
2.  Type `END` on a new line to submit the problem.
3.  The tool will then display a detailed tutorial for solving the problem.
4.  You can then choose to solve another problem or quit the application.

## Dependencies

*   **Anthropic Java SDK**: Used to interact with the Claude API.
*   **Gson**: For JSON serialization and deserialization.
*   **JUnit 5**: Used for unit testing.
*   **Mockito**: Used for creating mocks in tests.
