package uk.co.which.exercise.exception;

import lombok.Getter;

public class ForensicsAPIClientException extends RuntimeException {

    @Getter
    private final int statusCode;

    public ForensicsAPIClientException(int StatusCode, String message) {
        super(message);
        statusCode = StatusCode;
    }
}
