package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

public class _2016Eighteen extends Challenge {

  private String firstLine;
  private char[][] room;
  private int safeTiles;

  public _2016Eighteen() {
    super(18);
    firstLine = Utils.getInput(2016, 18).get(0);
  }

  public String puzzleOne() {
    solve(40);
    return safeTiles + "";
  }

  public String puzzleTwo() {
    solve(400000);
    return safeTiles + "";
  }

  private void solve(int rows) {

    //Setup initial conditions
    room = new char[rows][firstLine.length()];
    room[0] = firstLine.toCharArray();
    safeTiles = firstLine.replace("^", "").length();

    for (int floor = 1; floor < room.length; floor++) {
      char[] lastFloor = room[floor - 1];
      char[] currentFloor = room[floor];
      for (int position = 0; position < currentFloor.length; position++) {
        boolean leftIsTrap = position != 0 && lastFloor[position - 1] == '^';
        boolean rightIsTrap = position != currentFloor.length - 1 && lastFloor[position + 1] == '^';

        if ((!rightIsTrap && !leftIsTrap) || (rightIsTrap && leftIsTrap)) {
          currentFloor[position] = '.';
          safeTiles++;
        } else currentFloor[position] = '^';
      }
    }
  }

}
