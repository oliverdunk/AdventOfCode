package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2016Seven extends Challenge {

  private List<String> addresses;

  public _2016Seven() {
    super(7);
    addresses = Utils.getInput(2016, 7);
  }

  public String puzzleOne() {
    int count = 0;
    for (String address : addresses)
      if (supportsTLS(address)) count++;
    return count + "";
  }

  public String puzzleTwo() {
    int count = 0;
    for (String address : addresses)
      if (supportsSSL(address)) count++;
    return count + "";
  }

  private boolean supportsTLS(String address) {
    //Return false if there exists a hypernet which contains an abba
    Matcher hypernetMatcher = Pattern.compile("[^\\[][^\\[\\]]*(?=\\])").matcher(address);
    while (hypernetMatcher.find()) {
      String hypernet = hypernetMatcher.group();
      if (containsAbba(hypernet)) return false;
    }

    String outsideHypernet = address.replaceAll("\\[[^\\]]*\\]", ",");
    //Returns true if outside the hypernet, there is an abba
    return containsAbba(outsideHypernet);
  }

  private boolean containsAbba(String input) {
    Pattern abbaPattern = Pattern.compile(".*([a-zA-Z])([a-zA-Z])\\2\\1.*");
    Matcher abbaMatcher = abbaPattern.matcher(input);
    while (abbaMatcher.find()) {
      String abba = abbaMatcher.group();
      if (abba.charAt(0) == abba.charAt(3)) return false;
      return true;
    }
    return false;
  }

  private boolean supportsSSL(String address) {
    String outsideHypernet = address.replaceAll("\\[[^\\]]*\\]", ",");
    Pattern abaPattern = Pattern.compile("([a-zA-Z])[a-zA-Z]\\1");
    Matcher abaMatcher = abaPattern.matcher(outsideHypernet);

    //Find all babs not within hypernet
    if (abaMatcher.find()) {
      do {
        String aba = abaMatcher.group();
        Matcher hypernetMatcher = Pattern.compile("[^\\[][^\\[\\]]*(?=\\])").matcher(address);
        while (hypernetMatcher.find()) {
          Matcher hypernetBab = abaPattern.matcher(hypernetMatcher.group());
          if (hypernetBab.find()) {
            do {
              String bab = hypernetBab.group();
              if (bab.charAt(1) == aba.charAt(0) && bab.charAt(0) == aba.charAt(1)) return true;
            } while (hypernetBab.find(hypernetBab.start() + 1));
          }
        }
      } while (abaMatcher.find(abaMatcher.start() + 1));
    }

    return false;
  }

}
