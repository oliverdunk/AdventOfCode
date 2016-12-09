package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class _2015Two extends Challenge {

  private static List<String> input;

  public _2015Two() {
    super(2);
    if (input == null) input = Utils.getInput(2015, 2);
  }

  public String puzzleOne() {
    int totalWrapping = 0;

    for (String present : input) {
      String[] dimensions = present.split("x");
      int length = Integer.parseInt(dimensions[0]);
      int width = Integer.parseInt(dimensions[1]);
      int height = Integer.parseInt(dimensions[2]);

      //Calculate area of each side
      int sideOne = length * width;
      int sideTwo = width * height;
      int sideThree = height * length;

      //find the surface area of the box, which is 2*l*w + 2*w*h + 2*h*l
      int currentWrapping = (2 * sideOne) + (2 * sideTwo) + (2 * sideThree);

      //The elves also need a little extra paper for each present: the area of the smallest side
      if (sideOne < sideTwo)
        currentWrapping = currentWrapping + (sideOne < sideThree ? sideOne : sideThree);
      else currentWrapping = currentWrapping + (sideTwo < sideThree ? sideTwo : sideThree);

      totalWrapping += currentWrapping;
    }

    return totalWrapping + "";
  }

  public String puzzleTwo() {
    int totalRibbon = 0;

    for (String present : input) {
      String[] dimensions = present.split("x");
      int length = Integer.parseInt(dimensions[0]);
      int width = Integer.parseInt(dimensions[1]);
      int height = Integer.parseInt(dimensions[2]);
      int[] intDimensions = new int[]{length, width, height};

      //Sort dimensions
      Arrays.sort(intDimensions);

      //The ribbon required to wrap a present is the shortest distance around its sides
      totalRibbon += (intDimensions[0] + intDimensions[0] + intDimensions[1] + intDimensions[1]);

      //the feet or ribbon required for the perfect bow is equal to the cubic feet of volume of the present
      totalRibbon += length * width * height;
    }

    return totalRibbon + "";
  }

  public int getDay() {
    return 2;
  }

}
