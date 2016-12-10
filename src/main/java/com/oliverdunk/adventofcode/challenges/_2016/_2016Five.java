package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

public class _2016Five extends Challenge {

  private String doorId;

  public _2016Five() {
    super(5);
    doorId = Utils.getInput(2016, 5).get(0);
  }

  public String puzzleOne() {
    StringBuilder password = new StringBuilder();
    int currentIndex = 0;
    while (password.length() != 8) {
      String hash = Utils.digest(doorId + currentIndex);
      if (hash.startsWith("00000")) password.append(hash.charAt(5));
      currentIndex++;
    }
    return password.toString();
  }

  public String puzzleTwo() {
    char[] password = new char[] {'z', 'z', 'z', 'z', 'z', 'z', 'z', 'z'};
    int currentIndex = 0;
    int found = 0;
    while (found != 8) {
      String hash = Utils.digest(doorId + currentIndex);
      if (hash.startsWith("00000")) {
        try {
          int position = Integer.parseInt(hash.charAt(5) + "");
          if (position < 8 && password[position] == 'z') {
            password[position] = hash.charAt(6);
            found++;
          }
        } catch (Exception ex) {
          //Do nothing - the index wasn't an integer
        }
      }
      currentIndex++;
    }
    return new String(password);
  }

}
