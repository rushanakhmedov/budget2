package com.budget.budget2.response;

import java.util.HashMap;
import java.util.Map;

public class Response<T> {
    private T content;
    private String errorMessage;
    private Map<String, String> errorDetails = new HashMap<>();

    public Response() {
    }

    public Response(T content) {
        this.content = content;
    }

    public Response(T content, String errorMessage, Map<String, String> errorDetails) {
        this(content);
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }

    public void addError(String key, String value) {
        errorDetails.put(key, value);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }
}
