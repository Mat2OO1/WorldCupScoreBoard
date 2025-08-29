package org.example.service;

import java.util.List;
import org.example.domain.Game;

public interface IBoardService {

  void startGame(String homeTeam, String awayTeam);

  Game updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);

  List<Game> getSummary();
}
