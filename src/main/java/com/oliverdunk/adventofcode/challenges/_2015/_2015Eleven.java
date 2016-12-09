package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2015Eleven extends Challenge {

  private List<String> input;

  public _2015Eleven() {
    super(11);
    if (input == null) input = Utils.getInput(2015, 11);
  }

  String firstSolution;

  @Override
  public String puzzleOne() {
    String currentPassword = input.get(0);
    while (!allowed(currentPassword)) currentPassword = increment(currentPassword);
    firstSolution = currentPassword;
    return currentPassword;
  }

  @Override
  public String puzzleTwo() {
    String currentPassword = input.get(0);
    while (!allowed(currentPassword) | currentPassword.equals(firstSolution))
      currentPassword = increment(currentPassword);
    return currentPassword;
  }

  public String increment(String input) {
    char[] characters = input.toCharArray();
    for (int i = characters.length - 1; i >= 0; i--) {
      if (characters[i] == 'z') {
        characters[i] = 'a';
        continue;
      } else {
        characters[i] = (char) (characters[i] + 1);
        break;
      }
    }
    return new String(characters);
  }

  public boolean allowed(String input) {
    return input.matches("(?s).*(abc|bcd|cde|def|efg|fgh|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz).*")
            && input.matches("(?s).*(.)\\1.*(.)\\2.*") && !input.contains("i") && !input.contains("o")
            && !input.contains("l");
  }

  @Override
  public int getDay() {
    return 11;
  }

}
