package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class _2016One extends Challenge {

  private String input;
  private String[] instructions;
  private List<String> visited = new ArrayList<>();
  private String firstDuplicateDistance;
  private int x = 0;
  private int y = 0;
  private int facing = 1;

  public _2016One() {
    super(1);
    input = Utils.getInput(2016, 1).get(0);
    instructions = input.split(", ");
  }

  public String puzzleOne() {
    for (int i = 0; i < instructions.length; i++) {
      runInstruction(instructions[i]);
    }
    return (Math.abs(x) + Math.abs(y)) + "";
  }

  public String puzzleTwo() {
    return firstDuplicateDistance;
  }

  private void runInstruction(String instruction) {
    String direction = instruction.substring(0, 1);
    int steps = Integer.parseInt(instruction.substring(1, instruction.length()));
    turn(direction.equals("L") ? -1 : 1);
    moveForwards(steps);
  }

  private void turn(int offset) {
    if (facing + offset == 0) facing = 4;
    else if (facing + offset == 5) facing = 1;
    else facing += offset;
  }

  private void moveForwards(int steps) {
    int yMove = 0;
    int xMove = 0;

    if (facing == 1) yMove = 1;
    else if (facing == 2) xMove = 1;
    else if (facing == 3) yMove = -1;
    else xMove = -1;

    for (int i = 0; i < steps; i++) {
      y += yMove;
      x += xMove;
      if (visited.contains(x + ":" + y) && firstDuplicateDistance == null) {
        firstDuplicateDistance = (Math.abs(x) + Math.abs(y)) + "";
      } else {
        visited.add(x + ":" + y);
      }
    }
  }

}
