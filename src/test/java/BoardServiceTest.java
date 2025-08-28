

import static org.junit.Assert.assertFalse;

import org.example.BoardService;
import org.junit.Test;

public class BoardServiceTest {

  @Test
  public void shouldStartGame(){
    BoardService boardService = new BoardService();

    boardService.startGame("Home", "Away");

    assertFalse(boardService.getGames().isEmpty());
  }

}
