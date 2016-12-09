package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

public class _2015Four extends Challenge {

  private static String input;

  public _2015Four() {
    super(4);
    if (input == null) input = Utils.getInput(2015, 4).get(0);
  }

  public String puzzleOne() {
    int i = 0;
    //He needs to find MD5 hashes which, in hexadecimal, start with at least five zeros.
    while (!Utils.digest(input + i).subSequence(0, 5).toString().equals("00000")) i++;
    return i + "";
  }

  public String puzzleTwo() {
    int i = 0;
    //Now find one that starts with six zeros
    while (!Utils.digest(input + i).subSequence(0, 6).toString().equals("000000")) i++;
    return i + "";
  }

  public int getDay() {
    return 4;
  }

}
