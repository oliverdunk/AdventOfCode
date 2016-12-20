package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.math.BigInteger;

public class _2016Nineteen extends Challenge {

  private int numberOfElves;

  public _2016Nineteen() {
    super(19);
    numberOfElves = Integer.valueOf(Utils.getInput(2016, 19).get(0));
  }

  public String puzzleOne() {
    String asBinary = Integer.toBinaryString(numberOfElves);
    char firstCharacter = asBinary.charAt(0);
    String solution = asBinary.substring(1, asBinary.length()) + firstCharacter;
    return Integer.valueOf(solution, 2) + "";
  }

  public String puzzleTwo() {
    //Use a ceiled power to find the limit of the group this number of elves is in
    int limitingPower = (int) Math.round(Math.floor(Math.log(numberOfElves) / Math.log(3)));
    long limit = Math.round(Math.pow(3, limitingPower));

    if (numberOfElves == limit) return numberOfElves + "";

    //Subtract two times the offset (since we only count odd numbers)
    long difference = numberOfElves - limit;
    return (difference + Math.max(numberOfElves - 2 * limit, 0)) + "";
  }

}
