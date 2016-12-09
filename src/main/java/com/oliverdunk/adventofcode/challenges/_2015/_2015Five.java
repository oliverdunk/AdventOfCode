package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.Arrays;
import java.util.List;

/**
 * Initial solution was mine - now replaced with a regex version by xPaw.
 */
public class _2015Five extends Challenge {

  private static List<String> input;
  private static String[] alphabet =
          new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
                  "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

  public _2015Five() {
    super(5);
    if (input == null) input = Utils.getInput(2015, 5);
  }

  public String puzzleOne() {
    int niceFound = 0;
    for (String string : input) if (isNice(string)) niceFound++;
    //How many strings are nice?
    return niceFound + "";
  }

  public String puzzleTwo() {
    int niceFound = 0;
    for (String string : input) if (isNiceV2(string)) niceFound++;
    //How many strings are nice?
    return niceFound + "";
  }

  private static boolean isNice(String input) {
    //It contains at least three vowels
    String[] vowels = new String[]{"a", "e", "i", "o", "u"};
    int vowelsFound = 0;
    for (char c : input.toCharArray()) if (Arrays.toString(vowels).contains(c + "")) vowelsFound++;
    if (vowelsFound < 3) return false;

    //It does not contain the strings ab, cd, pq, or xy
    String[] blacklist = new String[]{"ab", "cd", "pq", "xy"};
    for (String blacklisted : blacklist) if (input.contains(blacklisted)) return false;

    //It contains at least one letter that appears twice in a row
    for (String letter : alphabet) if (input.contains(letter + letter)) return true;

    return false;
  }

  private static boolean isNiceV2(String input) {
    //Contains a pair of any two letters that appears at least twice in the string without overlapping
    //Contains at least one letter which repeats with exactly one letter between them
    return input.matches(".*(.).\\1.*") && input.matches(".*(..).*\\1.*");
  }

  public int getDay() {
    return 5;
  }

}
