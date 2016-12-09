package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2015Twelve extends Challenge {

  private List<String> input;

  public _2015Twelve() {
    super(12);
    if (input == null) input = Utils.getInput(2015, 12);
  }

  @Override
  public String puzzleOne() {
    String inputJSON = input.get(0);
    int total = 0;
    StringBuilder number = new StringBuilder();
    for (char character : inputJSON.toCharArray()) {
      if (Character.isDigit(character) || character == '-') number = number.append(character);
      else {
        if (number.toString().equals("")) continue;
        total += Integer.parseInt(number.toString());
        number = new StringBuilder();
      }
    }
    return total + "";
  }

  @Override
  public String puzzleTwo() {
    String inputJSON = input.get(0);
    int total = 0;
    StringBuilder number = new StringBuilder();

    inputJSON = inputJSON.replaceAll("[{][^}\\]]*red.*[}]", "");

    for (char character : inputJSON.toCharArray()) {
      if (Character.isDigit(character) || character == '-') number = number.append(character);
      else {
        if (number.toString().equals("")) continue;
        total += Integer.parseInt(number.toString());
        number = new StringBuilder();
      }
    }
    return total + "";
  }

  @Override
  public int getDay() {
    return 12;
  }

}
