# CodeBro 🤝

Your AI-powered coding buddy that helps you crush LeetCode problems!

CodeBro is an interactive CLI tool that takes any coding problem and breaks it down into:
- 📊 Problem Analysis
- 🎯 Solution Strategy
- 🧠 Algorithm Design
- 💻 Java Implementation
- ✅ Comprehensive Test Cases

## Features

- 🔄 **Interactive CLI** - Solve multiple problems in one session
- 🤖 **Powered by Claude AI** (Sonnet 4) - Expert-level explanations
- 📈 **Multiple Approaches** - See brute force → optimized solutions
- ⚡ **Complexity Analysis** - Understand time/space trade-offs
- 💻 **Working Code** - Get production-ready Java implementations with comments
- ✅ **Test Cases** - JUnit tests with edge cases included
- 🏗️ **Built with Official SDK** - Uses Anthropic's official Java SDK

## Prerequisites

- Java 21 or higher
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

### Interactive Mode

Run CodeBro:
```bash
mvn exec:java -Dexec.mainClass="org.codebro.Main"
```

Or run `Main.java` directly from your IDE.

### How It Works

1. **Paste your problem** - Copy any LeetCode or coding problem
2. **Type 'END'** on a new line when done
3. **Get your tutorial** - CodeBro provides a comprehensive breakdown
4. **Solve another** or type 'quit' to exit

### Example Session
```
=================================
  Welcome to CodeBro! 🤝
  Your AI Coding Buddy
=================================

Paste your coding problem below.
Type 'END' on a new line when done, or 'quit' to exit.

Two Sum Problem:
Given an array of integers nums and an integer target,
return indices of the two numbers such that they add up to target.

Example:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
END

🤔 CodeBro is thinking...

[Complete tutorial with analysis, strategy, algorithm, code, and tests appears here]

==================================================

Solve another problem? (y/n): 
```

## Project Structure
```
src/
├── main/
│   ├── java/org/codebro/
│   │   ├── Main.java                  # Interactive CLI entry point
│   │   ├── CodeBro.java               # Main agent orchestrator
│   │   ├── APIClient.java             # API interface
│   │   ├── AnthropicSDKClient.java    # Anthropic SDK implementation
│   │   └── PromptBuilder.java         # Builds structured prompts
│   └── resources/
│       └── config.properties          # API key configuration (gitignored)
└── test/java/org/codebro/
    ├── CodeBroTest.java               # Unit tests with mocks
    └── PromptBuilderTest.java         # Prompt builder tests
```

## Architecture

CodeBro uses a clean, testable architecture:

- **APIClient Interface** - Abstraction for AI providers
- **AnthropicSDKClient** - Implementation using official Anthropic SDK
- **PromptBuilder** - Crafts structured prompts for optimal responses
- **CodeBro** - Orchestrates the problem-solving workflow

This design makes it easy to:
- Swap AI providers (just implement `APIClient`)
- Test with mocks (dependency injection)
- Customize prompts without changing core logic

## Testing

Run all tests:
```bash
mvn test
```

Run specific test:
```bash
mvn test -Dtest=CodeBroTest
```

The test suite uses Mockito to mock the API client, ensuring fast, reliable tests without API calls.

## Configuration

### API Settings

Modify `AnthropicSDKClient.java` to change:
- Model version (default: `claude-sonnet-4-20250514`)
- Max tokens (default: 4096)

### Prompt Customization

Edit `PromptBuilder.java` to customize:
- Tutorial structure and sections
- Level of detail
- Output format and style

## Dependencies

- **Anthropic Java SDK** (2.10.0) - Official Claude API client
- **Gson** (2.11.0) - JSON parsing (used by SDK)
- **JUnit 5** (5.10.0) - Testing framework
- **Mockito** (5.2.0) - Mocking for unit tests

## Tips for Best Results

- **Be specific**: Include constraints, examples, and expected behavior
- **Format clearly**: Use line breaks and sections for complex problems
- **Include examples**: Show input/output examples from the problem
- **Ask follow-ups**: After getting a solution, you can solve another related problem

## Troubleshooting

### "Unable to find config.properties"
Make sure you created `src/main/resources/config.properties` with your API key.

### "API key not found"
Check that your `config.properties` file has the correct property name: `anthropic.api.key`

### API Errors
- Verify your API key is valid at https://console.anthropic.com
- Check you have sufficient credits in your account
- Ensure you're using Java 21+

## Future Enhancements

- [ ] Save tutorials to files (Markdown, HTML)
- [ ] Problem history and favorites
- [ ] Different difficulty levels (beginner/interview/advanced)
- [ ] Conversation mode for follow-up questions
- [ ] Support for multiple programming languages
- [ ] Export to Anki flashcards
- [ ] Integration with LeetCode API
- [ ] Batch processing from files

## Contributing

Contributions are welcome! Feel free to:
- Open issues for bugs or feature requests
- Submit pull requests
- Suggest improvements to prompts or architecture

## License

MIT License - feel free to use this for learning and building!

## Acknowledgments

- Built with [Anthropic's Claude API](https://www.anthropic.com/api)
- Uses the official [Anthropic Java SDK](https://github.com/anthropics/anthropic-sdk-java)
- Inspired by the LeetCode learning community

---

**Happy Coding! 💪**

*Built by developers, for developers who want to level up their problem-solving skills.*