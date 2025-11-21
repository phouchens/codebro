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
- 🤖 **Multi-Model Support** - Choose between **Claude** (Anthropic) and **Gemini** (Google Vertex AI)
- 📈 **Multiple Approaches** - See brute force → optimized solutions
- ⚡ **Complexity Analysis** - Understand time/space trade-offs
- 💻 **Working Code** - Get production-ready Java implementations with comments
- 💾 **Save to File** - Automatically save generated code and tests to a local project directory
- ✅ **Test Cases** - JUnit tests with edge cases included
- 🏗️ **Built with Official SDKs** - Uses Anthropic's official Java SDK and Google Vertex AI SDK
- ⚡ **Gradle Build** - Fast, modern build system with incremental compilation

## Prerequisites

- Java 21 or higher
- **For Claude:** Anthropic API key (get one at https://console.anthropic.com)
- **For Gemini:** Google Cloud Project with Vertex AI API enabled & Google Cloud SDK installed

**Note:** Gradle wrapper is included - no separate Gradle installation needed!

## Setup

1. **Clone the repository**
```bash
   git clone <your-repo-url>
   cd codebro
```

2. **Configure API Key**

   Create `src/main/resources/config.properties`:

   **Option A: Use Claude (Anthropic)**
```properties
   provider=anthropic
   anthropic.api.key=your-api-key-here
   # Optional: anthropic.model=claude-3-5-sonnet-latest
   # Optional: anthropic.max.tokens=4096
```

   **Option B: Use Gemini (Google Vertex AI)**
```properties
   provider=gemini
   gemini.project.id=your-gcp-project-id
   gemini.location=us-central1
   # Optional: gemini.model.name=gemini-2.5-pro
```

   If using Gemini, you must also authenticate locally:
   ```bash
   gcloud auth application-default login
   ```

⚠️ **Important**: Never commit this file! It's already in `.gitignore`.

3. **Build the project**
```bash
   ./gradlew build
```

## Usage

### Interactive Mode

Run CodeBro:
```bash
./gradlew run
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

Save solution to file? (y/n): y
Enter directory path (default: ./solution): ./mysolution
Successfully saved 3 files to ./mysolution

Solve another problem? (y/n): 
```

## Project Structure
```
src/
├── main/
│   ├── java/org/codebro/
│   │   ├── Main.java                  # Application entry point & config
│   │   ├── CodeBroCLI.java            # CLI User Interface
│   │   ├── CodeBro.java               # Main agent orchestrator
│   │   ├── APIClient.java             # API interface
│   │   ├── AnthropicSDKClient.java    # Anthropic SDK implementation
│   │   ├── GeminiSDKClient.java       # Google Vertex AI implementation
│   │   ├── ProjectWriter.java         # Saves generated code to files
│   │   └── PromptBuilder.java         # Builds structured prompts
│   └── resources/
│       ├── config.properties          # API key configuration (gitignored)
│       └── prompts/tutorial.txt       # Prompt template
├── test/java/org/codebro/
│   ├── CodeBroTest.java               # Unit tests with mocks
│   └── PromptBuilderTest.java         # Prompt builder tests
├── build.gradle                       # Gradle build configuration
├── settings.gradle                    # Gradle settings
└── gradlew                            # Gradle wrapper script
```

## Architecture

CodeBro uses a clean, testable architecture:

- **APIClient Interface** - Abstraction for AI providers
- **AnthropicSDKClient** - Implementation using official Anthropic SDK
- **GeminiSDKClient** - Implementation using Google Vertex AI SDK
- **PromptBuilder** - Crafts structured prompts for optimal responses
- **CodeBro** - Orchestrates the problem-solving workflow

This design makes it easy to:
- Swap AI providers (just implement `APIClient`)
- Test with mocks (dependency injection)
- Customize prompts without changing core logic

## Testing

Run all tests:
```bash
./gradlew test
```

Run specific test:
```bash
./gradlew test --tests CodeBroTest
```

The test suite uses Mockito to mock the API client, ensuring fast, reliable tests without API calls.

## Configuration

### API Settings

Modify `AnthropicSDKClient.java` to change:
- Model version (default: `claude-sonnet-4-20250514`)
- Max tokens (default: 4096)

### Prompt Customization

CodeBro uses a template-based system for prompts. The tutorial prompt is stored in:
```
src/main/resources/prompts/tutorial.txt
```

To customize the prompt:
1. Edit `tutorial.txt` directly
2. Use `{{PROBLEM}}` as a placeholder for the user's input
3. No code changes needed - the template is loaded at runtime

## Dependencies

All dependencies are managed in `build.gradle`:

- **Anthropic Java SDK** (2.10.0) - Official Claude API client
- **Google Cloud Vertex AI** (0.5.0) - Official Gemini API client
- **Gson** (2.11.0) - JSON parsing (used by SDK)
- **JUnit 5** (5.10.0) - Testing framework
- **Mockito** (5.11.0) - Mocking for unit tests

See [GRADLE_GUIDE.md](GRADLE_GUIDE.md) for more Gradle commands and tips.

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

### Build Issues
- Run `./gradlew clean build` to rebuild from scratch
- Check `build.gradle` for correct dependency versions
- Try `./gradlew --refresh-dependencies` to update dependencies

## Future Enhancements

- [x] Save tutorials to files (Java Project)
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

**Happy Coding! 💪**

*Built by developers, for developers who want to level up their problem-solving skills.*