package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.example.exception.GameAlreadyStartedException;
import org.example.exception.GameNotFoundException;

public class BoardService implements IBoardService {

  private final List<Game> currentGames = new ArrayList<>();

  public void startGame(String homeTeam, String awayTeam) {
    BoardServiceUtils.validateInput(homeTeam, awayTeam);
    this.getGame(homeTeam, awayTeam).ifPresent(game -> {
      throw new GameAlreadyStartedException(BoardConstants.GAME_ALREADY_STARTED);
    });
    this.currentGames.add(Game.builder()
        .homeTeam(homeTeam)
        .awayTeam(awayTeam)
        .homeGoals(BoardConstants.GAME_STARTING_VALUE)
        .awayGoals(BoardConstants.GAME_STARTING_VALUE)
        .build());
  }

  public void finishGame(String homeTeam, String awayTeam) {
    BoardServiceUtils.validateInput(homeTeam, awayTeam);
    this.getGame(homeTeam, awayTeam).ifPresentOrElse(currentGames::remove, () -> {
      throw new GameNotFoundException(BoardConstants.GAME_NOT_FOUND);
    });
  }

  public Game updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
    BoardServiceUtils.validateUpdateInput(homeTeam, awayTeam, homeScore, awayScore);
    Game foundGame = this.getGame(homeTeam, awayTeam)
        .orElseThrow(() -> new GameNotFoundException(BoardConstants.GAME_NOT_FOUND));
    foundGame.setHomeGoals(homeScore);
    foundGame.setAwayGoals(awayScore);
    return foundGame;
  }

  public List<Game> getSummary() {
    var games = new ArrayList<>(currentGames);

    Collections.reverse(games);
    games.sort(Comparator.comparingInt(Game::getTotalGoals).reversed());
    return games;
  }

  public List<Game> getGames() {
    return currentGames;
  }

  private Optional<Game> getGame(String homeTeam, String awayTeam) {
    return currentGames.stream()
        .filter(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))
        .findFirst();
  }
}
