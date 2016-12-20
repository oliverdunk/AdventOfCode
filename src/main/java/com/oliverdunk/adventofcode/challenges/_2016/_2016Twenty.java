package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class _2016Twenty extends Challenge {

  private List<String> input;
  private ArrayList<long[]> ranges = new ArrayList<>();
  private long largestValue = 0;

  public _2016Twenty() {
    super(20);
    input = Utils.getInput(2016, 20);
  }

  public String puzzleOne() {
    loadInput();
    return (ranges.get(0)[1] + 1) + "";
  }

  public String puzzleTwo() {
    BigInteger allowedCount = new BigInteger("4294967296");

    for (long[] blacklisted : ranges) {
      long inRange = (blacklisted[1] - blacklisted[0]) + 1;
      allowedCount = allowedCount.subtract(BigInteger.valueOf(inRange));
    }

    return allowedCount + "";
  }

  private void loadInput() {
    for (String blacklist : input) {
      String[] split = blacklist.split("-");
      long min = Long.parseLong(split[0]);
      long max = Long.parseLong(split[1]);
      if (max > largestValue) largestValue = max;
      ranges.add(new long[] {min, max});
    }
    ranges = getTidiedRanges();
  }

  private ArrayList<long[]> getTidiedRanges() {
    ArrayList<long[]> sorted = (ArrayList<long[]>) ranges.clone();
    sorted.sort((long[] first, long[] second) -> Long.compare(first[0], second[0]));

    ArrayList<long[]> merged = new ArrayList<>();

    long start = -1;
    long end = -1;

    for (long[] range : sorted) {
      if (start == -1) {
        start = range[0];
        end = range[1];
        continue;
      }

      if (range[0] - 1 <= end) {
        if(range[1] > end) end = range[1];
        continue;
      }

      merged.add(new long[] {start, end});
      start = range[0];
      end = range[1];
    }

    merged.add(new long[] {start, end});

    return merged;
  }

}
