package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;

public class _2016Three extends Challenge {

  private List<String> input;
  private int[][] sides = new int[3000][3];

  public _2016Three() {
    super(3);
    input = Utils.getInput(2016, 3);
  }

  @Override
  public String puzzleOne() {
    loadTriangles();
    int possibleTriangles = 0;
    for (int row = 0; row < input.size(); row++) {
      int[] lengths = Arrays.copyOf(sides[row], sides[row].length);
      Arrays.sort(lengths);
      if (lengths[2] < lengths[0] + lengths[1]) possibleTriangles++;
    }
    return possibleTriangles + "";
  }

  @Override
  public String puzzleTwo() {
    int possibleTriangles = 0;
    int y = 0;
    while (sides[y][0] != 0) {
      for (int column = 0; column < 3; column++) {
        int[] lengths = new int[]{
                sides[y][column],
                sides[y + 1][column],
                sides[y + 2][column]
        };
        Arrays.sort(lengths);
        if (lengths[2] < lengths[0] + lengths[1]) possibleTriangles++;
      }
      y = y + 3;
    }
    return possibleTriangles + "";
  }

  private void loadTriangles() {
    //Load all triangles into sides array
    int rowPointer = 0;
    for (String row : input) {
      loadSide(row, rowPointer);
      rowPointer++;
    }
  }

  /**
   * Turns the sides of this triangle into an integer array, and loads into memory for use in each
   * of the questions we are trying to solve.
   */
  private void loadSide(String triangle, int row) {
    triangle = triangle.trim();
    String[] lengths = triangle.split(" +");
    sides[row][0] = Integer.parseInt(lengths[0]);
    sides[row][1] = Integer.parseInt(lengths[1]);
    sides[row][2] = Integer.parseInt(lengths[2]);
  }

}
