package org.example.exception;

public class GameAlreadyStartedException extends RuntimeException {
  public GameAlreadyStartedException(String message) {
        super(message);
  }

}
