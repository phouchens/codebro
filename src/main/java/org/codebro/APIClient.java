package org.codebro;

public interface APIClient extends AutoCloseable {
    String sendMessage(String prompt) throws Exception;
}
