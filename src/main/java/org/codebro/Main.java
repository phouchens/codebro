package org.codebro;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    static void main() {
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

            CodeBro codeBro = new CodeBro(apiKey);

            String problem = """
                Two Sum Problem:
                Given an array of integers nums and an integer target,
                return indices of the two numbers such that they add up to target.
                
                Example:
                Input: nums = [2,7,11,15], target = 9
                Output: [0,1]
                Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
                """;

            System.out.println("Asking CodeBro to solve the problem...\n");
            String tutorial = codeBro.solveProblem(problem);
            System.out.println(tutorial);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
