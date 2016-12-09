package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2016Two extends Challenge {

  private List<String> input;

  //We'll class the origin (1, 1) as the top left
  private int x = 2;
  private int y = 2;

  //Width at any given Y value, height at any given X value
  private String[][] shape;


  public _2016Two() {
    super(2);
    input = Utils.getInput(2016, 2);
  }

  public String puzzleOne() {
    shape = new String[][] {
            {"1", "4", "7"},
            {"2", "5", "8"},
            {"3", "6", "9"}
    };
    return solve();
  }

  public String puzzleTwo() {
    x = 1;
    y = 3;
    shape = new String[][] {
            {" ", " ", "5", " ", " "},
            {" ", "2", "6", "A", " "},
            {"1", "3", "7", "B", "D"},
            {" ", "4", "8", "C", " "},
            {" ", " ", "9", " ", " "}
    };
    return solve();
  }

  private String solve() {
    StringBuilder code = new StringBuilder();
    for (String line : input) {
      String[] instructions = line.split("");
      for (int i = 0; i < instructions.length; i++) {
        if (instructions[i].equals("L") && isPossible(x - 1, y)) x -= 1;
        if (instructions[i].equals("R") && isPossible(x + 1, y)) x += 1;
        if (instructions[i].equals("D") && isPossible(x, y + 1)) y += 1;
        if (instructions[i].equals("U") && isPossible(x, y - 1)) y -= 1;
      }
      code.append(getValue(x, y));
    }
    return code.toString();
  }

  private boolean isPossible(int x, int y) {
    if (x < 1 || y < 1) return false;
    if (x > shape.length || y > shape[x - 1].length) return false;
    return !shape[x - 1][y - 1].equals(" ");
  }

  private String getValue(int x, int y) {
    return shape[x - 1][y - 1];
  }

}
