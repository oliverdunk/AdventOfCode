package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2016Fifteen extends Challenge {

  private List<String> diskDefinitions;
  private int time = 0;
  private int diskCount = 0;
  private int[] diskLength;
  private int[] diskPositions;

  public _2016Fifteen() {
    super(15);
    diskDefinitions = Utils.getInput(2016, 15);
  }

  public String puzzleOne() {
    resetInput();
    solve();
    return (time - diskCount) + "";
  }

  public String puzzleTwo() {
    resetInput();
    diskPositions[diskCount] = 0;
    diskLength[diskCount] = 11;
    diskCount = diskCount + 1;
    solve();
    return (time - diskCount) + "";
  }

  private void solve() {
    int currentlySearching = 1;
    boolean canFind = false;

    while (currentlySearching != diskCount + 1) {
      time++;
      for (int disk = 0; disk < diskCount; disk++) {
        int newValue = (diskPositions[disk] == diskLength[disk] - 1) ? 0 : diskPositions[disk] + 1;
        diskPositions[disk] = newValue;
        if (currentlySearching - 1 == disk && canFind) {
          if (newValue == 0) {
            currentlySearching++;
            canFind = false;
          }
          else currentlySearching = 1;
        }
      }
      canFind = true;
    }
  }

  private void resetInput() {
    time = 0;
    diskCount = diskDefinitions.size();
    diskLength = new int[diskCount + 1];
    diskPositions = new int[diskCount + 1];

    Pattern inputFormat = Pattern.compile(".*has ([0-9]+) positions.*position ([0-9]+).*");

    for (int disk = 0; disk < diskCount; disk++) {
      Matcher inputMatcher = inputFormat.matcher(diskDefinitions.get(disk));
      inputMatcher.find();
      diskLength[disk] = Integer.parseInt(inputMatcher.group(1));
      diskPositions[disk] = Integer.parseInt(inputMatcher.group(2));
    }
  }

}
