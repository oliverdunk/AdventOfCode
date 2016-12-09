package com.oliverdunk.adventofcode;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.challenges._2015.*;
import com.oliverdunk.adventofcode.challenges._2016.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is a basic utility used to run the challenges.
 */
public class AdventOfCode {

  private static Challenge[] _2015;
  private static Challenge[] _2016;

  public static void main(String[] args) {

    _2015 = new Challenge[] {
            new _2015One(), new _2015Two(), new _2015Three(), new _2015Four(), new _2015Five(),
            new _2015Six(), new _2015Seven(), new _2015Eight(), new _2015Nine(), new _2015Ten(),
            new _2015Eleven(), new _2015Twelve()
    };

    _2016 = new Challenge[] {
            new _2016One(), new _2016Two(), new _2015Three(), new _2015Four()
    };

    //Display user prompt to select challenge
    System.out.print("What year are we going for? (2015-2016): ");
    Scanner scanner = new Scanner(System.in);

    //Attempt to run the selected challenge
    try {
      Challenge[] solutions = scanner.nextInt() == 2015 ? _2015 : _2016;
      System.out.printf("Which day would you like to solve? (1-%s): ", solutions.length);
      int challenge = scanner.nextInt();
      for (Challenge challengeInstance : solutions) {
        if (challengeInstance.getDay() != challenge) continue;
        System.out.println();

        long start = System.currentTimeMillis();
        String result = challengeInstance.puzzleOne();
        long took = (System.currentTimeMillis() - start);
        System.out.printf("Part One: %s (took %sms).\n", result, took);

        start = System.currentTimeMillis();
        result = challengeInstance.puzzleTwo();
        took = (System.currentTimeMillis() - start);
        System.out.printf("Part Two: %s (took %sms).\n", result, took);

        return;
      }
    } catch (InputMismatchException ex) {
      System.out.println("Number expected, aborting...");
      return;
    }

    //If the challenge cannot be found, restart the program
    System.out.println("Challenge not found!");
    System.out.println();

    main(new String[]{});
  }

}
