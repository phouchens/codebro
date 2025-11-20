package org.codebro;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ProjectWriterTest {

    @Test
    void saveProject_ShouldExtractAndSaveFiles(@TempDir Path tempDir) throws IOException {
        ProjectWriter writer = new ProjectWriter();
        String response = """
                Here is the solution:

                [FILE: Main.java]
                ```java
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Hello");
                    }
                }
                ```
                [END_FILE]

                And a helper class:

                [FILE: Helper.java]
                ```
                public class Helper {}
                ```
                [END_FILE]
                """;

        int count = writer.saveProject(response, tempDir.toString());

        assertEquals(2, count);

        Path mainFile = tempDir.resolve("Main.java");
        assertTrue(Files.exists(mainFile));
        String mainContent = Files.readString(mainFile);
        assertTrue(mainContent.contains("public class Main"));

        Path helperFile = tempDir.resolve("Helper.java");
        assertTrue(Files.exists(helperFile));
    }

    @Test
    void saveProject_ShouldHandleNoFiles(@TempDir Path tempDir) throws IOException {
        ProjectWriter writer = new ProjectWriter();
        String response = "Just some text without code blocks.";

        int count = writer.saveProject(response, tempDir.toString());

        assertEquals(0, count);
    }
}
