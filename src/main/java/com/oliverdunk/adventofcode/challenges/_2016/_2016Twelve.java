package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.Arrays;

public class _2016Twelve extends Challenge {

  private String[] instructions;
  private int[] registers;

  public _2016Twelve() {
    super(12);
    instructions = Utils.getInput(2016, 12).toArray(new String[]{});
  }

  public String puzzleOne() {
    return solve(false) + "";
  }

  public String puzzleTwo() {
    return solve(true) + "";
  }

  private int solve(boolean secondPuzzle) {

    //Setup initial state
    registers = new int[4];
    if (secondPuzzle) registers[getIndex('c')] = 1;

    for (int currentIndex = 0; currentIndex < instructions.length; currentIndex++) {
      String instruction = instructions[currentIndex];
      String[] parts = instruction.split(" ");

      if (parts[0].equals("cpy")) {
        int valueToCopy;
        if(Utils.isInteger(parts[1])) valueToCopy = Integer.parseInt(parts[1]);
        else valueToCopy = registers[getIndex(parts[1].charAt(0))];
        registers[getIndex(parts[2].charAt(0))] = valueToCopy;
      }

      if (parts[0].equals("inc") || parts[0].equals("dec")) {
        registers[getIndex(parts[1].charAt(0))] += parts[0].equals("inc") ? 1 : -1;
      }

      if (parts[0].equals("jnz")) {
        int valueToCompare;
        if (Utils.isInteger(parts[1])) valueToCompare = Integer.parseInt(parts[1]);
        else valueToCompare = registers[getIndex(parts[1].charAt(0))];
        if (valueToCompare == 0) continue;
        //We have to subtract one to counteract the loop behaviour
        currentIndex += Integer.parseInt(parts[2]) - 1;
      }
    }

    return registers[getIndex('a')];
  }

  private int getIndex(char letter) {
    return Arrays.binarySearch(Utils.getAlphabet(), letter);
  }

}
