

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.example.BoardService;
import org.example.exception.GameAlreadyStartedException;
import org.example.exception.GameNotFoundException;
import org.junit.Test;

public class BoardServiceTest {

  @Test
  public void shouldStartGame() {
    BoardService boardService = new BoardService();

    boardService.startGame("Home", "Away");

    assertFalse(boardService.getGames().isEmpty());
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

  @Test
  public void shouldThrowExceptionWhenGameToUpdateScoreNotFound() {
    BoardService boardService = new BoardService();

    assertThrows(GameNotFoundException.class, () -> boardService.updateScore("Home", "Away", 1, 3));
  }

}
