package org.codebro;

import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            try (InputStream input = Main.class.getClassLoader()
                    .getResourceAsStream("config.properties")) {
                props.load(input);
            }
            String apiKey = props.getProperty("anthropic.api.key");

            if (apiKey == null || apiKey.isEmpty()) {
                System.err.println("API key not found in config.properties");
                return;
            }

           CodeBro codeBro = new CodeBro(new AnthropicSDKClient(apiKey), new PromptBuilder());

            Scanner scanner = new Scanner(System.in);

            System.out.println("=================================");
            System.out.println("  Welcome to CodeBro! 🤝");
            System.out.println("  Your AI Coding Buddy");
            System.out.println("=================================\n");


            while (true) {
                System.out.println("Paste your coding problem below.");
                System.out.println("Type 'END' on a new line when done, or 'quit' to exit.\n");

                StringBuilder problemBuilder = new StringBuilder();
                while (true) {
                    String line = scanner.nextLine();

                    if (line.trim().equalsIgnoreCase("quit")) {
                        System.out.println("\nThanks for using CodeBro! Happy coding! 👋");
                        scanner.close();
                        return;
                    }

                    if (line.trim().equalsIgnoreCase("END")) {
                        break;
                    }

                    problemBuilder.append(line).append("\n");
                }

                String problem = problemBuilder.toString().trim();

                if (problem.isEmpty()) {
                    System.out.println("No problem entered. Try again.\n");
                    continue;
                }

                System.out.println("\n🤔 CodeBro is thinking...\n");

                try {
                    String tutorial = codeBro.solveProblem(problem);
                    System.out.println(tutorial);
                    System.out.println("\n" + "=".repeat(50) + "\n");
                } catch (Exception e) {
                    System.err.println("Error solving problem: " + e.getMessage());
                    System.out.println();
                }

                System.out.print("Solve another problem? (y/n): ");
                String answer = scanner.nextLine().trim().toLowerCase();

                if (!answer.equals("y") && !answer.equals("yes")) {
                    System.out.println("\nThanks for using CodeBro! Happy coding! 👋");
                    break;
                }

                System.out.println();
            }

            scanner.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
