package org.example;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {

  private String homeTeam;
  private String awayTeam;
  private int homeGoals;
  private int awayGoals;
}
