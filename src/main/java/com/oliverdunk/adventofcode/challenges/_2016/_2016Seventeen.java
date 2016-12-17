package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.LinkedList;

public class _2016Seventeen extends Challenge {

  private String passcode;
  private String bestSolution;
  private int longestSolution;

  public _2016Seventeen() {
    super(17);
    passcode = Utils.getInput(2016, 17).get(0);
  }

  public String puzzleOne() {
    return solve();
  }

  public String puzzleTwo() {
    return longestSolution + "";
  }

  private String solve() {
    LinkedList<State> queue = new LinkedList();
    queue.add(new State(1, 1, ""));

    String[] directions = new String[]{"U", "D", "L", "R"};

    while (queue.size() > 0) {
      State currentState = queue.pop();
      for (String direction : directions) {
        int newX = currentState.x;
        int newY = currentState.y;

        if (direction.equals("U")) newY -= 1;
        else if (direction.equals("D")) newY += 1;
        else if (direction.equals("L")) newX -= 1;
        else newX += 1;

        if (newX < 1 || newY < 1 || newX > 4 || newY > 4) continue;
        if (!canMove(currentState, direction)) continue;

        if (newX == 4 && newY == 4) {
          if (bestSolution == null) bestSolution =  currentState.steps + direction;
          else longestSolution = (currentState.steps + direction).length();
        }
        else queue.add(currentState.clone(newX, newY, direction));
      }
    }

    return bestSolution;
  }

  private boolean canMove(State currentState, String direction) {
    String hash = Utils.digest(passcode + currentState.steps.toUpperCase());
    if (direction.equals("U") && isOpen(hash.charAt(0))) return true;
    if (direction.equals("D") && isOpen(hash.charAt(1))) return true;
    if (direction.equals("L") && isOpen(hash.charAt(2))) return true;
    if (direction.equals("R") && isOpen(hash.charAt(3))) return true;
    return false;
  }

  private boolean isOpen(char doorHashChar) {
    if (doorHashChar == 'b') return true;
    if (doorHashChar == 'c') return true;
    if (doorHashChar == 'd') return true;
    if (doorHashChar == 'e') return true;
    if (doorHashChar == 'f') return true;
    return false;
  }

  private class State {

    int x;
    int y;
    String steps;

    public State(int x, int y, String steps) {
      this.x = x;
      this.y = y;
      this.steps = steps;
    }

    public State clone(int newX, int newY, String stepTaken) {
      return new State(newX, newY, steps + stepTaken);
    }

  }

}
