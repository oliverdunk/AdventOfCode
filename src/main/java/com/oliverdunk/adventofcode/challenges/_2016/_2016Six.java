package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2016Six extends Challenge {

  private List<String> input;
  private int[][] charactersAtPosition = new int[20][150];

  public _2016Six() {
    super(6);
    input = Utils.getInput(2016, 6);
  }

  public String puzzleOne() {
    loadInput();
    return getResult(true);
  }

  public String puzzleTwo() {
    return getResult(false);
  }

  public String getResult(boolean highest) {
    StringBuilder result = new StringBuilder();
    for (int position = 0; position < input.get(0).length(); position++) {
      char currentChar = '?';
      int currentLimit = highest ? 0 : Integer.MAX_VALUE;
      for (int character = 0; character < 150; character++) {
        int count = charactersAtPosition[position][character];
        if (count != 0 && (highest ? (count > currentLimit) : (count < currentLimit))) {
          currentLimit = count;
          currentChar = (char) character;
        }
      }
      result.append(currentChar);
    }
    return result.toString();
  }

  private void loadInput() {
    for (String message : input) {
      for (int position = 0; position < message.length(); position++) {
        int character = message.charAt(position);
        int positionCount = charactersAtPosition[position][character] + 1;
        charactersAtPosition[position][character] = positionCount;
      }
    }
  }

}
