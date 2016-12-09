package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

public class _2015Three extends Challenge {

  private static String input;

  public _2015Three() {
    super(3);
    if (input == null) input = Utils.getInput(2015, 3).get(0);
  }

  public String puzzleOne() {
    int santaX = 0, santaY = 0;

    //an infinite (though not in this implementation) two-dimensional grid of houses
    int[][] houses = new int[1000][1000];
    houses[santaX + 500][santaY + 500] = 1;
    int visited = 1;

    for (int i = 0; i < input.length(); i++) {
      String character = input.subSequence(i, i + 1).toString();

      if (character.equals("^")) santaY++;
      if (character.equals("v")) santaY--;
      if (character.equals(">")) santaX++;
      if (character.equals("<")) santaX--;

      if (houses[santaX + 500][santaY + 500] == 1) continue;
      houses[santaX + 500][santaY + 500] = 1;
      visited++;
    }

    //How many houses receive at least one present?
    return visited + "";
  }

  public String puzzleTwo() {
    int santaX = 0, santaY = 0, robotX = 0, robotY = 0;

    //an infinite (though not in this implementation) two-dimensional grid of houses
    int[][] houses = new int[1000][1000];
    houses[santaX + 500][santaY + 500] = 1;
    int visited = 1;

    //Santa and Robot-Santa... take turns moving based on instructions from the elf
    boolean santa = true;

    for (int i = 0; i < input.length(); i++) {
      String character = input.subSequence(i, i + 1).toString();

      if (santa) {
        santa = false;
        if (character.equals("^")) santaY++;
        if (character.equals("v")) santaY--;
        if (character.equals(">")) santaX++;
        if (character.equals("<")) santaX--;

        if (houses[santaX + 500][santaY + 500] == 1) continue;
        houses[santaX + 500][santaY + 500] = 1;
        visited++;
      } else {
        santa = true;
        if (character.equals("^")) robotY++;
        if (character.equals("v")) robotY--;
        if (character.equals(">")) robotX++;
        if (character.equals("<")) robotX--;

        if (houses[robotX + 500][robotY + 500] == 1) continue;
        houses[robotX + 500][robotY + 500] = 1;
        visited++;
      }
    }

    //How many houses receive at least one present?
    return visited + "";
  }

  public int getDay() {
    return 3;
  }

}
