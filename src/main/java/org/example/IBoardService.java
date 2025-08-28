package org.example;

import java.util.List;

public interface IBoardService {

  void startGame(String homeTeam, String awayTeam);

  List<Game> getGames();
}
