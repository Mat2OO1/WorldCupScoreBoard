package org.example.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Game {

  private String homeTeam;
  private String awayTeam;
  private int homeGoals;
  private int awayGoals;

  public int getTotalGoals() {
    return homeGoals + awayGoals;
  }

}
