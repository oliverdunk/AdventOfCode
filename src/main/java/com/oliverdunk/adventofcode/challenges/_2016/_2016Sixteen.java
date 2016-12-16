package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2016Sixteen extends Challenge {

  private String input;

  public _2016Sixteen() {
    super(16);
    input = Utils.getInput(2016, 16).get(0);
  }

  public String puzzleOne() {
    return generateChecksum(generateDragonCurve(input, 272));
  }

  public String puzzleTwo() {
    return generateChecksum(generateDragonCurve(input, 35651584));
  }

  private String generateDragonCurve(String input, int length) {
    StringBuilder dragonCurve = new StringBuilder();
    dragonCurve.append(input);
    dragonCurve.append(0);

    String stringB = new StringBuilder(input).reverse().toString();
    stringB = stringB.replace("0", "2");
    stringB = stringB.replace("1", "0");
    stringB = stringB.replace("2", "1");
    dragonCurve.append(stringB);

    if (dragonCurve.length() >= length) return dragonCurve.toString().substring(0, length);
    else return generateDragonCurve(dragonCurve.toString(), length);
  }

  private String generateChecksum(String input) {
    Pattern pairFinder = Pattern.compile("([01]{2})");
    Matcher pairMatcher = pairFinder.matcher(input);

    StringBuilder checksum = new StringBuilder();

    while (pairMatcher.find()) {
      String match = pairMatcher.group(1);
      if (match.charAt(0) == match.charAt(1)) checksum.append(1);
      else checksum.append(0);
    }

    if ((checksum.length() + 1) % 2 != 0) return generateChecksum(checksum.toString());
    else return checksum.toString();
  }

}
