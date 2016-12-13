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
            new _2016One(), new _2016Two(), new _2015Three(), new _2015Four(), new _2016Five(),
            new _2016Six(), new _2016Seven(), new _2016Eight(), new _2016Nine(), new _2016Ten(),
            new _2016Eleven(), new _2016Twelve()
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

        long start = System.nanoTime();
        String result = challengeInstance.puzzleOne();
        String units = "ns";
        long took = (System.nanoTime() - start);
        if (took / 1000000 > 1) {
          took = took / 1000000;
          units = "ms";
        }
        System.out.printf("Part One: %s (took %s).\n", result, took + units);

        start = System.nanoTime();
        result = challengeInstance.puzzleTwo();
        units = "ns";
        took = (System.nanoTime() - start);
        if (took / 1000000 > 1) {
          took = took / 1000000;
          units = "ms";
        }
        System.out.printf("Part Two: %s (took %s).\n", result, took + units);

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
