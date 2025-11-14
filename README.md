# CodeBro 🤝

Your AI-powered coding buddy that helps you crush LeetCode problems!

CodeBro takes any coding problem and breaks it down into:
- 📊 Problem Analysis
- 🎯 Solution Strategy
- 🧠 Algorithm Design
- 💻 Java Implementation
- ✅ Comprehensive Test Cases

## Features

- Uses Claude AI (Sonnet 4) to provide expert-level explanations
- Explains multiple approaches (brute force → optimized)
- Includes time/space complexity analysis
- Generates working Java code with comments
- Provides JUnit test cases with edge cases

## Prerequisites

- Java 21
- Maven 3.6+
- Anthropic API key (get one at https://console.anthropic.com)

## Setup

1. **Clone the repository**
```bash
   git clone <your-repo-url>
   cd codebro
```

2. **Configure API Key**

   Create `src/main/resources/config.properties`:
```properties
   anthropic.api.key=your-api-key-here
```

⚠️ **Important**: Never commit this file! It's already in `.gitignore`.

3. **Build the project**
```bash
   mvn clean install
```

## Usage

Run the main class:
```bash
mvn exec:java -Dexec.mainClass="org.codebro.Main"
```

Or run `Main.java` directly from your IDE.

### Example

Input a problem like:
```
Two Sum Problem:
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.

Example:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
```

CodeBro will provide a complete tutorial with analysis, strategy, algorithm, implementation, and tests!

## Project Structure
```
src/
├── main/java/org/codebro/
│   ├── Main.java                  # Entry point
│   ├── CodeBro.java               # Main agent orchestrator
│   ├── APIClient.java             # API interface
│   ├── ClaudeAPIClient.java       # Claude API implementation
│   └── PromptBuilder.java         # Builds structured prompts
└── test/java/org/codebro/
    ├── CodeBroTest.java
    ├── ClaudeAPIClientTest.java
    └── PromptBuilderTest.java
```

## Testing

Run all tests:
```bash
mvn test
```

Run specific test:
```bash
mvn test -Dtest=CodeBroTest
```

## Configuration

### API Settings

Modify `ClaudeAPIClient.java` to change:
- Model version (default: `claude-sonnet-4-20250514`)
- Max tokens (default: 4096)
- API URL

### Prompt Customization

Edit `PromptBuilder.java` to customize:
- Tutorial structure
- Level of detail
- Output format

## Dependencies

- **Gson** (2.11.0) - JSON parsing
- **JUnit 5** (5.10.0) - Testing
- **Mockito** (5.2.0) - Mocking for tests

## Future Enhancements

- [ ] Interactive CLI for multiple problems
- [ ] Support for different programming languages
- [ ] Save tutorials to files
- [ ] Different difficulty levels (beginner/advanced)
- [ ] Conversation mode for follow-up questions
- [ ] Support for other AI providers (OpenAI, Gemini)

## Contributing

Feel free to open issues or submit pull requests!

## License

MIT License - feel free to use this for learning and building!

## Acknowledgments

- Built with [Anthropic's Claude API](https://www.anthropic.com/api)
- Inspired by the LeetCode learning community

---

**Happy Coding! 💪**