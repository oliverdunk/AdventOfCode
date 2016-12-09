package com.oliverdunk.adventofcode.challenges._2015;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.*;

public class _2015Nine extends Challenge {

  private static List<String> input;

  public _2015Nine() {
    super(9);
    if (input == null) input = Utils.getInput(2015, 9);
  }

  @Override
  public String puzzleOne() {
    return doIt(false) + "";
  }

  @Override
  public String puzzleTwo() {
    return doIt(true) + "";
  }

  public int[][] distances = new int[8][8];
  public HashMap<String, Integer> locations = new HashMap<>();

  private int doIt(boolean longest) {

    //Don't do this for the second part of the puzzle, if it's already been done
    if (locations.size() == 0) {
      //Load all inputs into the array of distances
      for (String string : input) {
        String[] parts = string.split(" ");
        if (!locations.containsKey(parts[0])) locations.put(parts[0], locations.size());
        if (!locations.containsKey(parts[2])) locations.put(parts[2], locations.size());
        distances[locations.get(parts[0])][locations.get(parts[2])] = Integer.parseInt(parts[4]);
      }
    }

    List<String> keys = new ArrayList<>();
    keys.addAll(locations.keySet());

    return getBest(longest, keys);
  }


  private int getBest(boolean longest, List<String> keys) {
    int desiredLength = longest ? 0 : Integer.MAX_VALUE;
    for (List<String> route : permute(keys)) {
      int length = 0;
      String prior = null;
      for (String location : route) {
        if (prior != null) {
          if (distances[locations.get(prior)][locations.get(location)] > 0) {
            length += distances[locations.get(prior)][locations.get(location)];
          } else {
            length += distances[locations.get(location)][locations.get(prior)];
          }
        }
        prior = location;
      }
      if (desiredLength > length && !longest) desiredLength = length;
      if (desiredLength < length && longest) desiredLength = length;
    }
    return desiredLength;
  }

  //Taken from StackOverflow (I need to learn more about permutations!)
  //URL: http://codereview.stackexchange.com/questions/68716/permutations-of-any-given-numbers
  public List<List<String>> permute(List<String> strings) {
    List<List<String>> permutations = new ArrayList<List<String>>();
    permutations.add(new ArrayList<String>());

    for (int i = 0; i < strings.size(); i++) {
      List<List<String>> current = new ArrayList<>();
      for (List<String> permutation : permutations) {
        for (int j = 0, n = permutation.size() + 1; j < n; j++) {
          List<String> temp = new ArrayList<>(permutation);
          temp.add(j, strings.get(i));
          current.add(temp);
        }
      }
      permutations = new ArrayList<>(current);
    }

    return permutations;
  }

  @Override
  public int getDay() {
    return 9;
  }

}
