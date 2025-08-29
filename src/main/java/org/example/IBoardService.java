package org.example;

import java.util.List;

public interface IBoardService {

  void startGame(String homeTeam, String awayTeam);

  List<Game> getGames();

  Game updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

  List<Game> getSummary();
}
