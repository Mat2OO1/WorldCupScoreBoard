package org.example.utils;

import org.example.constants.BoardConstants;
import org.example.exception.GameInputValidationException;

public class BoardServiceUtils {

  public static void validateInput(String homeTeam, String awayTeam) {
    if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()
        || homeTeam.isBlank() || awayTeam.isBlank()) {
      throw new GameInputValidationException(BoardConstants.TEAM_NAME_NULL_OR_EMPTY);
    }
  }

  public static void validateUpdateInput(String homeTeam, String awayTeam, int homeScore,
      int awayScore) {
    validateInput(homeTeam, awayTeam);
    if (homeScore < 0 || awayScore < 0) {
      throw new GameInputValidationException(BoardConstants.NEGATIVE_SCORE);
    }
  }

}
