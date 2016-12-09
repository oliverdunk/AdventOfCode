package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2015Six extends Challenge {

  private static List<String> input;

  public _2015Six() {
    super(6);
    if (input == null) input = Utils.getInput(2015, 6);
  }

  @Override
  public String puzzleOne() {
    //You've decided to deploy one million lights in a 1000x1000 grid
    int[][] lights = new int[1000][1000];
    for (String instruction : input) {
      String[] keywords = instruction.split(" ");
      if (keywords[0].contains("toggle")) {
        int[] pointOne = Utils.pointToInt(keywords[1]);
        int[] pointTwo = Utils.pointToInt(keywords[3]);
        for (int x = pointOne[0]; x <= pointTwo[0]; x++) {
          for (int z = pointOne[1]; z <= pointTwo[1]; z++) {
            if (lights[x][z] == 0) lights[x][z] = 1;
            else lights[x][z] = 0;
          }
        }
      } else {
        int[] pointOne = Utils.pointToInt(keywords[2]);
        int[] pointTwo = Utils.pointToInt(keywords[4]);
        int setTo = keywords[1].contains("on") ? 1 : 0;
        for (int x = pointOne[0]; x <= pointTwo[0]; x++) {
          for (int z = pointOne[1]; z <= pointTwo[1]; z++) {
            lights[x][z] = setTo;
          }
        }
      }
    }

    //How many lights are lit?
    int brightnessCount = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        brightnessCount += lights[i][j];
      }
    }
    return brightnessCount + "";
  }

  @Override
  public String puzzleTwo() {
    //You've decided to deploy one million lights in a 1000x1000 grid
    int[][] lights = new int[1000][1000];
    for (String instruction : input) {
      String[] keywords = instruction.split(" ");
      if (keywords[0].contains("toggle")) {
        int[] pointOne = Utils.pointToInt(keywords[1]);
        int[] pointTwo = Utils.pointToInt(keywords[3]);
        for (int x = pointOne[0]; x <= pointTwo[0]; x++) {
          for (int z = pointOne[1]; z <= pointTwo[1]; z++) {
            //toggle actually means that you should increase the brightness of those lights by 2
            lights[x][z] += 2;
          }
        }
      } else {
        int[] pointOne = Utils.pointToInt(keywords[2]);
        int[] pointTwo = Utils.pointToInt(keywords[4]);
        for (int x = pointOne[0]; x <= pointTwo[0]; x++) {
          for (int z = pointOne[1]; z <= pointTwo[1]; z++) {
            //turn on actually means that you should increase the brightness of those lights by 1
            if (keywords[1].contains("on")) lights[x][z] += 1;
            /** turn off actually means that you should decrease the brightness of those lights by 1, to a
             minimum of zero. **/
            else if (lights[x][z] > 0) lights[x][z]--;
          }
        }
      }
    }

    //How many lights are lit?
    int brightnessCount = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000; j++) {
        brightnessCount += lights[i][j];
      }
    }
    return brightnessCount + "";
  }

  public int getDay() {
    return 6;
  }

}
