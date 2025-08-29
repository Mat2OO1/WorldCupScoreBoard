package org.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.domain.Game;

public class GameRepository implements IGameRepository {

  private final List<Game> games = new ArrayList<>();

  public void addGame(Game game) {
    games.add(game);
  }

  public void removeGame(Game game) {
    games.remove(game);
  }

  public Optional<Game> findGame(String homeTeam, String awayTeam) {
    return games.stream()
        .filter(game -> game.getHomeTeam().equals(homeTeam) && game.getAwayTeam().equals(awayTeam))
        .findFirst();
  }

  public List<Game> getGames() {
    return games;
  }
}
