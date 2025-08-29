package org.example;

import java.util.List;

public interface IBoardService {

  void startGame(String homeTeam, String awayTeam);

  Game updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

  List<Game> getSummary();
}
