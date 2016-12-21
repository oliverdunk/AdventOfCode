package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _2016TwentyOne extends Challenge {

  private List<String> instructions;
  private char[] result;

  public _2016TwentyOne() {
    super(21);
    instructions = Utils.getInput(2016, 21);
  }

  public String puzzleOne() {
    result = "abcdefgh".toCharArray();
    run(true);
    return new String(result);
  }

  public String puzzleTwo() {
    result = "fbgdceah".toCharArray();
    Collections.reverse(instructions);
    run(false);
    return new String(result);
  }

  private void run(boolean scramble) {
    for (String instruction : instructions) {
      String[] parts = instruction.split(" ");

      if (instruction.startsWith("swap")) {
        char firstChar;
        char secondChar;
        int firstIndex = -1;
        int secondIndex = -1;

        if (instruction.startsWith("swap letter")) {
          firstChar = parts[2].charAt(0);
          for (int i = 0; i < result.length; i++) if (result[i] == firstChar) firstIndex = i;
          secondChar = parts[5].charAt(0);
          for (int i = 0; i < result.length; i++) if (result[i] == secondChar) secondIndex = i;
        } else {
          firstIndex = Integer.parseInt(parts[2]);
          firstChar = result[firstIndex];
          secondIndex = Integer.parseInt(parts[5]);
          secondChar = result[secondIndex];
        }

        result[firstIndex] = secondChar;
        result[secondIndex] = firstChar;
      }


      if (instruction.startsWith("reverse positions")) {
        int from = Integer.parseInt(parts[2]);
        int to = Integer.parseInt(parts[4]);
        char[] toReverse = Arrays.copyOfRange(result, from, to + 1);
        for (int i = 0; i < toReverse.length; i++) result[from + i] = toReverse[toReverse.length - 1 - i];
      }

      if (instruction.startsWith("move")) {
        int from = Integer.parseInt(scramble ? parts[2] : parts[5]);
        int to = Integer.parseInt(scramble ? parts[5] : parts[2]);

        char[] newArray = new char[result.length];
        newArray[to] = result[from];

        for (int i = 0; i < result.length; i++) {
          if (i == to) continue;
          else if ((i < to && i < from) || (i > to && i > from)) newArray[i] = result[i];
          else if (to > from) newArray[i] = result[i + 1];
          else newArray[i] = result[i - 1];
        }

        result = newArray;
      }

      if (instruction.startsWith("rotate")) {
        int rotateBy = 0;

        if (instruction.startsWith("rotate based")) {
          char lookingFor = parts[6].charAt(0);

          if (!scramble) {
            //We need to find something that scrambles based on this letter to get where we are
            int trying = 1;
            boolean found = false;
            while (!found) {
              char[] backtrack = rotate(result.clone(), trying);
              if (rotateBasedOn(backtrack, lookingFor)[3] == result[3]) {
                result = backtrack;
                found = true;
                continue;
              }
              trying++;
            }
            continue;
          }

          result = rotateBasedOn(result, lookingFor);
          continue;
        }

        //Not based on a particular position
        rotateBy = Integer.parseInt(parts[2]);
        if (parts[1].equals("right")) rotateBy = rotateBy * -1;
        if (!scramble) rotateBy *= -1;
        result = rotate(result, rotateBy);
      }
    }
  }

  private char[] rotateBasedOn(char[] toRotate, char basedOn) {
    int rotateBy = 0;
    for (int i = 0; i < toRotate.length; i++)
      if (toRotate[i] == basedOn) rotateBy = -(i + (i >= 4 ? 2 : 1));
    return rotate(toRotate, rotateBy);
  }

  private char[] rotate(char[] toRotate, int rotateBy) {
    toRotate = toRotate.clone();

    //Let's actually do the rotation
    for (int y = 0; y < Math.abs(rotateBy); y++) {
      int direction = rotateBy > 0 ? 1 : -1;
      char[] newArray = new char[toRotate.length];
      for (int i = 0; i < newArray.length; i++) {
        int getFrom = i + direction;
        if (getFrom < 0) getFrom = newArray.length + getFrom;
        else if (getFrom + 1 > newArray.length) getFrom = ((getFrom + 1) % newArray.length) - 1;
        newArray[i] = toRotate[getFrom];
      }
      toRotate = newArray;
    }

    return toRotate;
  }

}
