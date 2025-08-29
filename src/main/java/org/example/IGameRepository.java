package org.example;

import java.util.List;
import java.util.Optional;

public interface IGameRepository {

  void addGame(Game game);

  void removeGame(Game game);

  Optional<Game> findGame(String homeTeam, String awayTeam);

  List<Game> getGames();

}
