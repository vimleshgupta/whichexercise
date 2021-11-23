package uk.co.which.exercise.exception;

public class UnknownDirectionFoundException extends RuntimeException {

    public UnknownDirectionFoundException(String message) {
        super(message);
    }
}
