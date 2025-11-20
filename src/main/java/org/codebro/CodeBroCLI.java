package org.codebro;

import java.util.Scanner;

public class CodeBroCLI {

    private final CodeBro codeBro;

    public CodeBroCLI(CodeBro codeBro) {
        this.codeBro = codeBro;
    }

    public void start() {
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

            String tutorial = null;
            try {
                tutorial = codeBro.solveProblem(problem);
                System.out.println(tutorial);
                System.out.println("\n" + "=".repeat(50) + "\n");
            } catch (Exception e) {
                System.err.println("Error solving problem: " + e.getMessage());
                System.out.println();
                continue;
            }

            System.out.print("Save solution to file? (y/n): ");
            String saveAnswer = scanner.nextLine().trim().toLowerCase();

            if (saveAnswer.equals("y") || saveAnswer.equals("yes")) {
                System.out.print("Enter directory path (default: ./solution): ");
                String path = scanner.nextLine().trim();
                if (path.isEmpty()) {
                    path = "./solution";
                }

                try {
                    ProjectWriter writer = new ProjectWriter();
                    int count = writer.saveProject(tutorial, path);
                    System.out.println("Successfully saved " + count + " files to " + path);
                } catch (Exception e) {
                    System.err.println("Error saving files: " + e.getMessage());
                }
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
    }
}
