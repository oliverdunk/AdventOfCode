package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

public class _2015One extends Challenge {

  private static String input;

  public _2015One() {
    super(1);
    if (input == null) input = Utils.getInput(2015, 1).get(0);
  }

  public String puzzleOne() {
    //An opening parenthesis means he should go up one floor
    int up = input.replace(")", "").length();
    //A closing parenthesis means he should go down one floor
    int down = input.replace("(", "").length();
    //To what floor do the instructions take santa?
    return (up - down) + "";
  }

  public String puzzleTwo() {
    int floor = 0;
    for (int i = 1; i <= input.length(); i++) {
      floor = (input.subSequence(i - 1, i).toString().equals("(")) ? floor + 1 : floor - 1;
      //find the position of the first character that causes him to enter the basement (floor -1)
      if (floor != -1) continue;
      return i + "";
    }
    //return 0 if he never reaches the basement
    return 0 + "";
  }

  public int getDay() {
    return 1;
  }

}
