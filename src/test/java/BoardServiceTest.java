

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.example.BoardService;
import org.junit.Test;

public class BoardServiceTest {

  @Test
  public void shouldStartGame() {
    BoardService boardService = new BoardService();

    boardService.startGame("Home", "Away");

    assertFalse(boardService.getGames().isEmpty());
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

}
