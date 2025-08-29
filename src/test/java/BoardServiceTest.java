

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.BoardService;
import org.example.exception.GameAlreadyStartedException;
import org.example.exception.GameInputValidationException;
import org.example.exception.GameNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BoardServiceTest {

  @Test
  public void shouldStartGame() {
    BoardService boardService = new BoardService();

    boardService.startGame("Home", "Away");

    assertFalse(boardService.getGames().isEmpty());
  }

  @ParameterizedTest
  @CsvSource(value = {"null, away", "home, null", ",away", "home,"}, nullValues = "null")
  public void shouldNotStartGameWhenInputIsInvalid(String homeTeam, String awayTeam) {
    BoardService boardService = new BoardService();

    assertThrows(
        GameInputValidationException.class, () -> boardService.startGame(homeTeam, awayTeam));
  }

  @Test
  public void shouldThrowExceptionWhenGameAlreadyStarted() {
    BoardService boardService = new BoardService();
    boardService.startGame("Home", "Away");

    assertThrows(GameAlreadyStartedException.class, () -> boardService.startGame("Home", "Away"));

  }

  @Test
  public void shouldFinishGame() {
    BoardService boardService = new BoardService();

    boardService.startGame("Home", "Away");
    boardService.finishGame("Home", "Away");

    assertTrue(boardService.getGames().isEmpty());
  }

  @ParameterizedTest
  @CsvSource(value = {"null, away", "home, null", ",away", "home,"}, nullValues = "null")
  public void shouldNotFinishGameWhenInputIsInvalid(String homeTeam, String awayTeam) {
    BoardService boardService = new BoardService();

    assertThrows(
        GameInputValidationException.class, () -> boardService.finishGame(homeTeam, awayTeam));
  }

  @Test
  public void shouldThrowExceptionWhenGameToFinishNotFound() {
    BoardService boardService = new BoardService();

    boardService.startGame("Home", "Away");

    assertThrows(GameNotFoundException.class, () -> boardService.finishGame("abc", "abc"));
  }

  @Test
  public void shouldUpdateScore() {
    BoardService boardService = new BoardService();
    boardService.startGame("Home", "Away");

    boardService.updateScore("Home", "Away", 1, 3);

    var updatedGame = boardService.getGames().getFirst();
    assertEquals(1, updatedGame.getHomeGoals());
    assertEquals(3, updatedGame.getAwayGoals());
  }

  @ParameterizedTest
  @CsvSource(value = {"null, away", "home, null", ",away", "home,"}, nullValues = "null")
  public void shouldNotUpdateScoreWhenTeamInputIsInvalid(String homeTeam, String awayTeam) {
    BoardService boardService = new BoardService();

    assertThrows(
        GameInputValidationException.class,
        () -> boardService.updateScore(homeTeam, awayTeam, 1, 1));
  }

  @ParameterizedTest
  @CsvSource(value = {"1,-2", "-2,1"})
  public void shouldNotUpdateScoreWhenScoreInputIsInvalid(int homeScore, int awayScore) {
    BoardService boardService = new BoardService();

    assertThrows(
        GameInputValidationException.class,
        () -> boardService.updateScore("home", "away", homeScore, awayScore));
  }

  @Test
  public void shouldThrowExceptionWhenGameToUpdateScoreNotFound() {
    BoardService boardService = new BoardService();

    assertThrows(GameNotFoundException.class, () -> boardService.updateScore("Home", "Away", 1, 3));
  }

  @Test
  public void shouldReturnSummaryOfGames() {
    BoardService boardService = new BoardService();
    boardService.startGame("Spain", "Brazil");
    boardService.startGame("Germany", "France");
    boardService.startGame("Argentina", "Australia");

    boardService.updateScore("Spain", "Brazil", 10, 2);
    boardService.updateScore("Germany", "France", 2, 2);
    boardService.updateScore("Argentina", "Australia", 3, 1);

    var summary = boardService.getSummary();

    assertEquals("Spain", summary.get(0).getHomeTeam());
    assertEquals("Brazil", summary.get(0).getAwayTeam());
    assertEquals("Argentina", summary.get(1).getHomeTeam());
    assertEquals("Australia", summary.get(1).getAwayTeam());
    assertEquals("Germany", summary.get(2).getHomeTeam());
    assertEquals("France", summary.get(2).getAwayTeam());
  }

}
