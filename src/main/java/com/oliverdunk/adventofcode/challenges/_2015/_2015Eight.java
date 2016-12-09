package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2015Eight extends Challenge {

  private static List<String> input;

  public _2015Eight() {
    super(8);
    if (input == null) input = Utils.getInput(2015, 8);
  }

  @Override
  public String puzzleOne() {
    return solve(false) + "";
  }

  @Override
  public String puzzleTwo() {
    return solve(true) + "";
  }

  public int solve(boolean encoded) {
    int difference = 0;
    for (String string : input) {
      int wasted = encoded ? 4 : 2;
      string = string.substring(1, string.length() - 1);
      for (int i = 0; i < string.length() - 1; i++) {
        if (string.charAt(i) == '\\' && string.charAt(i + 1) == '"') {
          wasted += encoded ? 2 : 1;
          i++;
        } else if (string.charAt(i) == '\\' && string.charAt(i + 1) == 'x') {
          wasted += encoded ? 1 : 3;
        } else if (string.charAt(i) == '\\' && string.charAt(i + 1) == '\\') {
          wasted += encoded ? 2 : 1;
          i++;
        }
      }
      difference += wasted;
    }
    return difference;
  }


  @Override
  public int getDay() {
    return 8;
  }

}