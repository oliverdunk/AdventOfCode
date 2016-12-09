package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.HashMap;
import java.util.List;

public class _2015Seven extends Challenge {

  private static List<String> input;
  private HashMap<String, Wire> wires = new HashMap<>();
  private int cachedPartOne = 0;

  public _2015Seven() {
    super(7);
    if (input == null) input = Utils.getInput(2015, 7);
  }

  @Override
  public String puzzleOne() {
    for (String instruction : input) {
      String[] keywords = instruction.split(" ");
      if (keywords.length == 3) wires.put(keywords[2], new Wire(keywords[0], "", "IS"));
      else if (keywords.length == 4) wires.put(keywords[3], new Wire(keywords[1], "", "NOT"));
      else wires.put(keywords[4], new Wire(keywords[0], keywords[2], keywords[1]));
    }
    cachedPartOne = wires.get("a").getValue();
    return cachedPartOne + "";
  }

  @Override
  public String puzzleTwo() {
    for (Wire wire : wires.values()) wire.setCache(0);
    wires.get("b").setCache(cachedPartOne);
    return wires.get("a").getValue() + "";
  }

  public class Wire {

    private String depend, dependTwo, operator;
    private int cached = 0;

    public Wire(String depend, String dependTwo, String operator) {
      this.depend = depend;
      this.operator = operator;
      this.dependTwo = dependTwo;
    }

    public int getValue() {
      if (cached != 0) return cached;
      cached = process();
      return cached;
    }

    public void setCache(int value) {
      this.cached = value;
    }

    private int process() {
      if (operator.contains("IS")) return getDepend(depend);
      if (operator.contains("RSHIFT")) return getDepend(depend) >>> getDepend(dependTwo);
      if (operator.contains("LSHIFT")) return getDepend(depend) << getDepend(dependTwo);
      if (operator.contains("AND")) return getDepend(depend) & getDepend(dependTwo);
      if (operator.contains("OR")) return getDepend(depend) | getDepend(dependTwo);
      if (operator.contains("NOT")) return ~getDepend(depend);
      System.out.println("Error reading wires!");
      return -1;
    }

    public int getDepend(String target) {
      try {
        return Integer.parseInt(target);
      } catch (Exception ex) {
        return wires.get(target).getValue();
      }
    }

  }

  @Override
  public int getDay() {
    return 7;
  }

}