package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;

public class _2015Ten extends Challenge {

  private List<String> input;

  public _2015Ten() {
    super(10);
    if (input == null) input = Utils.getInput(2015, 10);
  }

  @Override
  public String puzzleOne() {
    String current = input.get(0);
    for (int i = 0; i < 40; i++) current = lookAndSay(current);
    return (current + "").length() + "";
  }

  @Override
  public String puzzleTwo() {
    String current = input.get(0);
    for (int i = 0; i < 50; i++) current = lookAndSay(current);
    return (current + "").length() + "";
  }

  private String lookAndSay(String current) {
    StringBuilder next = new StringBuilder();
    char lastPart = 0;
    int count = 0;
    for (char part : current.toCharArray()) {
      if (lastPart != 0 && part != lastPart) {
        next.append(count).append(lastPart);
        lastPart = part;
        count = 1;
      } else {
        count++;
        lastPart = part;
      }
    }
    next.append(count).append(lastPart);
    return next.toString();
  }

  @Override
  public int getDay() {
    return 10;
  }

}
