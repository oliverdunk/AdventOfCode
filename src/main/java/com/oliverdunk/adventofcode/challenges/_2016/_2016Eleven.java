package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2016Eleven extends Challenge {

  private List<String> input;
  private HashSet<String> knownHashes = new HashSet<>();
  private LinkedList<State> queue = new LinkedList<>();

  public _2016Eleven() {
    super(11);
    input = Utils.getInput(2016, 11);
  }

  public String puzzleOne() {
    return getShortestPath(false).steps + "";
  }

  public String puzzleTwo() {
    return getShortestPath(true).steps + "";
  }

  private State getShortestPath(boolean partTwo) {
    queue.add(new State(partTwo));
    while (queue.size() > 0) {
      State current = queue.pop();
      if (current.isSolved()) return current;

      for (List<String> couldMove : couldMove(current.floors[current.currentFloor - 1])) {
        for (int direction = -1; direction < 2; direction += 2) {
          if (direction == -1 && current.currentFloor == 1) continue;
          if (direction == 1 && current.currentFloor == 4) continue;

          //Calculate new state
          State newState = current.clone();
          for (String item : couldMove) newState.floors[newState.currentFloor - 1].remove(item);
          newState.currentFloor += direction;
          for (String item : couldMove) newState.floors[newState.currentFloor - 1].add(item);

          //Decide if it's worth keeping around
          if (!stateAllowed(newState)) continue;
          if (knownHashes.contains(newState.getHash())) continue;
          knownHashes.add(newState.getHash());
          queue.add(newState);
        }
      }
    }
    return null; //If something goes wrong and we don't end up with a solution
  }

  private class State {

    private ArrayList<String>[] floors;
    private int currentFloor = 1;
    private int steps = 0;

    public State(boolean partTwo) {
      //Default constructor to make a blank state with input
      floors = new ArrayList[4];
      floors[0] = new ArrayList<>();
      floors[1] = new ArrayList<>();
      floors[2] = new ArrayList<>();
      floors[3] = new ArrayList<>();
      loadInput();

      if (partTwo) {
        floors[0].add("ELG");
        floors[0].add("ELM");
        floors[0].add("DIG");
        floors[0].add("DIM");
      }
    }

    public State(ArrayList<String>[] floors, int currentFloor, int steps) {
      this.floors = cloneFloors(floors);
      this.currentFloor = currentFloor;
      this.steps = steps + 1;
    }

    private void loadInput() {
      for (int floor = 0; floor < input.size(); floor++) {
        String contents = input.get(floor);
        List<String> floorState = floors[floor];

        Matcher items = Pattern.compile("a ([a-zA-Z-]+[a-zA-Z]+) (\\w+)").matcher(contents);
        while (items.find()) {
          String type = items.group(2).equals("generator") ? "G" : "M";
          String material = (items.group(1).split("-")[0].substring(0, 2) + "").toUpperCase();
          floorState.add(material + type);
        }
      }
    }

    public State clone() {
      return new State(floors, currentFloor, steps);
    }

    public String getHash() {
      StringBuilder hash = new StringBuilder();
      for (int i = 0; i < floors.length; i++) {
        int generators = 0, microchips = 0;
        for (String item : floors[i]) {
          if (item.contains("M")) microchips++;
          else generators++;
        }
        hash.append(String.format("%s[%s:%s]-%s", i, generators, microchips, currentFloor));
      }
      return hash.toString();
    }

    private boolean isSolved() {
      return floors[0].isEmpty() && floors[1].isEmpty() && floors[2].isEmpty();
    }

  }

  private ArrayList<String>[] cloneFloors(ArrayList<String>[] floors) {
    ArrayList<String>[] cloned = new ArrayList[4];
    for (int i = 0; i < 4; i++)
      cloned[i] = (ArrayList<String>) floors[i].clone();
    return cloned;
  }

  private String getCorresponding(String item) {
    if (item.contains("M")) return item.replace("M", "G");
    else return item.replace("G", "M");
  }

  private boolean stateAllowed(State stateToCheck) {
    for (List<String> floor : stateToCheck.floors) {
      //See if this floor is permitted, or if a microchip will be fried
      int generators = 0;
      for (String item : floor) if (item.contains("G")) generators++;
      for (String item : floor)
        if (item.contains("M") && generators >= 1 && !floor.contains(getCorresponding(item)))
          return false;
    }
    return true;
  }

  private List<List<String>> couldMove(List<String> floor) {
    List<List<String>> potentialContents = new ArrayList<>();
    List<String> alreadyHad = new ArrayList<>();

    for (String item : floor) {
      List<String> singleItem = new ArrayList<>();
      singleItem.add(item);
      potentialContents.add(singleItem);
      alreadyHad.add(item);

      //Find potential combinations which could go with this item
      for (String moveWith : floor) {
        if (item.equals(moveWith)) continue;
        if (alreadyHad.contains(moveWith)) continue;
        alreadyHad.add(item);
        List<String> group = new ArrayList<>();
        group.add(item);
        group.add(moveWith);
        potentialContents.add(group);
      }
    }

    return potentialContents;
  }

}
