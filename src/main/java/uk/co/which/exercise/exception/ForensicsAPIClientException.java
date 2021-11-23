package uk.co.which.exercise.exception;

import lombok.Getter;

public class ForensicsAPIClientException extends RuntimeException {

    @Getter
    private final int statusCode;

    public ForensicsAPIClientException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }
}
