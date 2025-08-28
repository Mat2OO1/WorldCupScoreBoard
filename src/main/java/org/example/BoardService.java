package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.exception.GameAlreadyStartedException;
import org.example.exception.GameNotFoundException;

public class BoardService implements IBoardService {

  private final List<Game> currentGames = new ArrayList<>();

  public void startGame(String homeTeam, String awayTeam) {
    if (currentGames.stream().anyMatch(
        game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))) {
      throw new GameAlreadyStartedException("Game already started");
    } else {
      this.currentGames.add(Game.builder()
          .homeTeam(homeTeam)
          .awayTeam(awayTeam)
          .homeGoals(0)
          .awayGoals(0)
          .build());
    }
  }

  public void finishGame(String homeTeam, String awayTeam) {
    Game foundGame = currentGames.stream()
        .filter(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))
        .findFirst()
        .orElseThrow(
            () -> new GameNotFoundException("Game with given home team and away team not found"));
    currentGames.remove(foundGame);
  }

  public List<Game> getGames() {
    return currentGames;
  }

}
