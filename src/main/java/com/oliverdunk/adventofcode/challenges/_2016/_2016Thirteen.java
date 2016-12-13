package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _2016Thirteen extends Challenge {

  private List<String> alreadyVisited = new ArrayList<>();
  private List<String> lessThan50Steps = new ArrayList<>();
  private int favoriteNumber;
  private int targetX;
  private int targetY;

  public _2016Thirteen() {
    super(13);
    favoriteNumber = Integer.parseInt(Utils.getInput(2016, 13).get(0));
  }

  public String puzzleOne() {
    targetX = 31;
    targetY = 39;
    return solve();
  }

  public String puzzleTwo() {
    return lessThan50Steps.size() + "";
  }

  private String solve() {
    LinkedList<State> queue = new LinkedList();
    queue.add(new State(1, 1, 0));

    while (queue.size() > 0) {
      State currentState = queue.pop();
      for (int direction = 1; direction <= 4; direction++) {
        int newX = currentState.x;
        int newY = currentState.y;

        if (direction == 1) newX += 1;
        else if (direction == 2) newX -= 1;
        else if (direction == 3) newY += 1;
        else newY -= 1;

        if (newX < 0 || newY < 0) continue; //The void isn't a friendly place
        if (alreadyVisited.contains(newX + "/" + newY)) continue;
        if (isWall(newX, newY)) continue; //Unsurprisingly, we don't want to walk into a wall
        if (newX == targetX && newY == targetY) return (currentState.steps + 1) + "";
        alreadyVisited.add(newX + "/" + newY);
        queue.add(currentState.clone(newX, newY));
        if (currentState.steps + 1 <= 50) lessThan50Steps.add(newX + "/" + newY);
      }
    }

    return "Unable to solve the maze. We might be stuck here for a while.";
  }

  private boolean isWall(int x, int y) {
    //x*x + 3*x + 2*x*y + y + y*y
    int result = (x * x) + (3 * x) + (2 * x * y) + (y) + (y * y);
    result += favoriteNumber;
    int oneBits = Integer.bitCount(result);

    //If the number minus one is even, the number must be odd
    return (oneBits - 1) % 2 == 0;
  }

  private class State {

    int x;
    int y;
    int steps;

    public State(int x, int y, int steps) {
      this.x = x;
      this.y = y;
      this.steps = steps;
    }

    public State clone(int newX, int newY) {
      return new State(newX, newY, steps + 1);
    }

  }

}
