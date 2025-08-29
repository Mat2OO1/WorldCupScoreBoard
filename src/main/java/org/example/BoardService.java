package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.exception.GameAlreadyStartedException;
import org.example.exception.GameNotFoundException;

@RequiredArgsConstructor
public class BoardService implements IBoardService {

  private final GameRepository gameRepository;

  public void startGame(String homeTeam, String awayTeam) {
    BoardServiceUtils.validateInput(homeTeam, awayTeam);
    this.gameRepository.findGame(homeTeam, awayTeam).ifPresent(game -> {
      throw new GameAlreadyStartedException(BoardConstants.GAME_ALREADY_STARTED);
    });
    this.gameRepository.addGame(Game.builder()
        .homeTeam(homeTeam)
        .awayTeam(awayTeam)
        .homeGoals(BoardConstants.GAME_STARTING_VALUE)
        .awayGoals(BoardConstants.GAME_STARTING_VALUE)
        .build());
  }

  public void finishGame(String homeTeam, String awayTeam) {
    BoardServiceUtils.validateInput(homeTeam, awayTeam);
    this.gameRepository.findGame(homeTeam, awayTeam)
        .ifPresentOrElse(gameRepository::removeGame, () -> {
          throw new GameNotFoundException(BoardConstants.GAME_NOT_FOUND);
        });
  }

  public Game updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
    BoardServiceUtils.validateUpdateInput(homeTeam, awayTeam, homeScore, awayScore);
    Game foundGame = this.gameRepository.findGame(homeTeam, awayTeam)
        .orElseThrow(() -> new GameNotFoundException(BoardConstants.GAME_NOT_FOUND));
    foundGame.setHomeGoals(homeScore);
    foundGame.setAwayGoals(awayScore);
    return foundGame;
  }

  public List<Game> getSummary() {
    var games = new ArrayList<>(this.gameRepository.getGames());

    Collections.reverse(games);
    games.sort(Comparator.comparingInt(Game::getTotalGoals).reversed());
    return games;
  }
}
