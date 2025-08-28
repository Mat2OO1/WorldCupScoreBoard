package org.example;

import java.util.ArrayList;
import java.util.List;

public class BoardService implements IBoardService {

  private final List<Game> currentGames = new ArrayList<>();

  public void startGame(String homeTeam, String awayTeam) {
    this.currentGames.add(Game.builder()
        .homeTeam(homeTeam)
        .awayTeam(awayTeam)
        .homeGoals(0)
        .awayGoals(0)
        .build());
  }

  public List<Game> getGames() {
    return currentGames;
  }

}
