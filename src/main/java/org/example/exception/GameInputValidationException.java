package org.example.exception;

public class GameInputValidationException extends RuntimeException {

  public GameInputValidationException(String message) {
    super(message);
  }
}
