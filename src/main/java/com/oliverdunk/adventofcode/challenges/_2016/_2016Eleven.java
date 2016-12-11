package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * To simplify this puzzle, I used my own terms:
 * Generators are owners of precious materials (O)
 * Microchips are the precious materials (G)
 *
 * A precious material can't be on its own without its owner - the owner will take it!
 * An owner is safe alone.
 */
public class _2016Eleven extends Challenge {

  public _2016Eleven() {
    super(11);
  }

  public String puzzleOne() {
    loadInput();
    return getFastestPath(loadInput(), 1, new ArrayList<>()) + "";
  }

  public String puzzleTwo() {
    return null;
  }

  private List<String>[] loadInput() {
    ArrayList<String>[] floors = new ArrayList[4];

    floors[0] = new ArrayList<>();
    floors[1] = new ArrayList<>();
    floors[2] = new ArrayList<>();
    floors[3] = new ArrayList<>();

    floors[0].add("G1");
    floors[0].add("G2");
    floors[1].add("O2");
    floors[2].add("O1");
    return floors;
  }

  private HashMap<List<String>[], Integer> cache = new HashMap<>();
  private HashMap<List<String>[], Integer> beenThereDoneThat = new HashMap<>();

  private int getFastestPath(List<String>[] floors, int floor, ArrayList<List<String>[]> steps) {

    //This basically replies with the largest possible path value (so that it's ignored)
    //if we've already got to this state (to prevent infinite loops)
    for (List<String>[] deJaVu : beenThereDoneThat.keySet()) {
      boolean matches = true;
      for (int i = 0; i < deJaVu.length; i++) {
        if (!deJaVu[i].equals(floors[i])) matches = false;
      }
      if (matches && beenThereDoneThat.get(deJaVu) < steps.size()) return Integer.MAX_VALUE;
    }

    beenThereDoneThat.put(floors, steps.size());

    if (fromCache(floors) != -1) {
      return fromCache(floors);
    }

    floors = cloneFloors(floors);

    if ((floors[0].isEmpty() && floors[1].isEmpty() && floors[2].isEmpty())) {
      System.out.println("START OF SOLUTION");
      for (List<String>[] floorState : steps) {
        System.out.println(Arrays.toString(floorState));
      }
      System.out.println("END OF SOLUTION");
      return steps.size();
    }

    int shortestPath = Integer.MAX_VALUE;

    for (List<String> group : getPotentialLiftContents(floors[floor - 1])) {
      int took = fastestWayToMove(floors, floor, group, steps);
      if (took < shortestPath) shortestPath = took;
    }

    cache.put(floors, shortestPath);
    return shortestPath;
  }

  private List<List<String>> getPotentialLiftContents(List<String> floor) {
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

  private int fromCache(List<String>[] floors) {
    for (List<String>[] item : cache.keySet()) {
      boolean matches = true;
      for (int floor = 0; floor < floors.length; floor ++) {
        if (!item[floor].equals(floors[floor])) matches = false;
      }
      if (matches) return cache.get(item);
    }
    return -1;
  }

  private int fastestWayToMove(List<String>[] floors, int floor, List<String> moving, ArrayList<List<String>[]> steps) {
    int shortestPath = Integer.MAX_VALUE;

    for (int direction = -1; direction < 2; direction++) {
      if (direction == -1 && floor == 1) continue;
      if (direction == 1 && floor == 4) continue;
      if (direction == 0) continue;

      List<String>[] floorState = cloneFloors(floors);
      for (String item : moving) floorState[floor - 1].remove(item);
      for (String item : moving) floorState[floor - 1 + direction].add(item);

      if (!stateIsAllowed(floorState)) continue;
      steps = (ArrayList<List<String>[]>) steps.clone();
      steps.add(floorState);
      int took = getFastestPath(cloneFloors(floorState), floor + direction, steps);
      if (took < shortestPath) shortestPath = took;
    }

    return shortestPath;
  }

  private boolean stateIsAllowed(List<String>[] floors) {
    for (List<String> floor : floors) {
      List<String> owners = new ArrayList<>();
      List<String> minerals = new ArrayList<>();
      for (String item : floor) {
        if (item.charAt(0) == 'O') owners.add(item);
        else minerals.add(item);
      }
      for (String mineral : minerals) {
        if (!owners.contains(getCorresponding(mineral)) && owners.size() > 0) {
          //There are other owners on this floor, and this mineral doesn't have one
          return false;
        }
      }
    }
    return true;
  }

  private String getCorresponding(String item) {
    if (item.charAt(0) == 'G') {
      return item.replace('G', 'O');
    } else {
      return item.replace('O', 'G');
    }
  }

  private List<String>[] cloneFloors(List<String>[] floors) {
    ArrayList<String>[] cloned = new ArrayList[4];
    cloned[0] = new ArrayList<>(floors[0]);
    cloned[1] = new ArrayList<>(floors[1]);
    cloned[2] = new ArrayList<>(floors[2]);
    cloned[3] = new ArrayList<>(floors[3]);
    return cloned;
  }

}
