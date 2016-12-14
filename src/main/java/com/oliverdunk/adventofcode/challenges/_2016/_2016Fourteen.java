package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2016Fourteen extends Challenge {

  private String salt;

  public _2016Fourteen() {
    super(14);
    salt = Utils.getInput(2016, 14).get(0);
  }

  public String puzzleOne() {
    return solve(false) + "";
  }

  public String puzzleTwo() {
    return solve(true) + "";
  }

  private int solve(boolean stretched) {
    List<PotentialHash> potentials = new ArrayList<>();
    List<Integer> foundHashes = new ArrayList<>();
    int index = 0;
    int searchMinimum = 0;

    Pattern repeatedCharacters = Pattern.compile("(.)\\1\\1");

    while (foundHashes.size() < 64 || index < searchMinimum) {
      String newHash = Utils.digest(salt + index);
      if (stretched) for (int i = 0; i < 2016; i++) newHash = Utils.digest(newHash);
      Matcher hashMatcher = repeatedCharacters.matcher(newHash);

      //Let's check to see if any of the hashes we're looking for are resolved by this
      for (PotentialHash potentialHash : potentials) {
        if (foundHashes.contains(potentialHash.index)) continue; //Already resolved
        if (potentialHash.index + 1000 < index) continue; //More than 1000 hashes away
        String needsToContain = "";
        for (int i = 0; i < 5; i++) needsToContain += potentialHash.repeated;
        if (!newHash.contains(needsToContain)) continue;
        foundHashes.add(potentialHash.index);
      }

      //If we've found a new potential hash...
      if (hashMatcher.find() && foundHashes.size() < 64) {
        String repeatedCharacter = hashMatcher.group(0);
        potentials.add(new PotentialHash(repeatedCharacter.charAt(0), index));
        searchMinimum = index + 1000;
      }

      index++;
    }

    foundHashes.sort((x, y) -> Integer.compare(x, y));
    return foundHashes.get(63);
  }

  private class PotentialHash {

    public char repeated;
    public int index;

    public PotentialHash(char repeated, int index) {
      this.repeated = repeated;
      this.index = index;
    }

  }

}
