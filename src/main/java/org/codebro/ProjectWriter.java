package org.codebro;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProjectWriter {

    private static final Pattern FILE_PATTERN = Pattern.compile(
            "\\[FILE: (.*?)\\]\\s*```(?:java)?\\s*(.*?)\\s*```\\s*\\[END_FILE\\]",
            Pattern.DOTALL);

    public int saveProject(String response, String targetDirectory) throws IOException {
        Path rootPath = Paths.get(targetDirectory);
        if (!Files.exists(rootPath)) {
            Files.createDirectories(rootPath);
        }

        Matcher matcher = FILE_PATTERN.matcher(response);
        int filesSaved = 0;

        while (matcher.find()) {
            String filename = matcher.group(1).trim();
            String content = matcher.group(2).trim();

            Path filePath = rootPath.resolve(filename);
            Files.writeString(filePath, content);
            System.out.println("Saved: " + filePath.toAbsolutePath());
            filesSaved++;
        }

        return filesSaved;
    }
}
