package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _2016Four extends Challenge {

  private List<String> rooms;
  private List<String> realRooms = new ArrayList<>();
  private Pattern matcher = Pattern.compile("([a-zA-Z-]*)-([0-9]{3})\\[([a-z]{5})\\]");

  public _2016Four() {
    super(4);
    rooms = Utils.getInput(2016, 4);
  }

  @Override
  public String puzzleOne() {
    int sum = 0;
    for (String room : rooms) {
      Matcher matches = matcher.matcher(room);
      matches.matches();
      String encryptedName = matches.group(1);
      int sectorId = Integer.parseInt(matches.group(2));
      char[] checksum = matches.group(3).toCharArray();
      if (isReal(encryptedName.replace("-", ""), checksum)) {
        sum += sectorId;
        realRooms.add(room);
      }
    }
    return sum + "";
  }

  private boolean isReal(String encryptedName, char[] checksum) {
    HashMap<Character, Integer> characterRank = new HashMap<>();
    for (char letter : encryptedName.toCharArray()) {
      int currentCount = characterRank.containsKey(letter) ? characterRank.get(letter) : 0;
      characterRank.put(letter, currentCount + 1);
    }
    Stack<Character> actualRank = sortByValueAlphabetically(characterRank);
    for (int i = 0; i < 5; i++) {
      if (actualRank.get(i) != checksum[i]) return false;
    }
    return true;
  }

  @Override
  public String puzzleTwo() {
    for (String room : realRooms) {
      Matcher matches = matcher.matcher(room);
      matches.matches();
      char[] encryptedName = matches.group(1).toCharArray();
      int sectorId = Integer.parseInt(matches.group(2));

      char[] alphabet = Utils.getAlphabet();
      for (int i = 0; i < sectorId; i++) {
        for (int charIndex = 0; charIndex < encryptedName.length; charIndex++) {
          int currentLocation = Arrays.binarySearch(alphabet, encryptedName[charIndex]);
          if (encryptedName[charIndex] == '-') continue;
          if (currentLocation + 1 == alphabet.length) encryptedName[charIndex] = 'a';
          else encryptedName[charIndex] = alphabet[currentLocation + 1];
        }
      }
      String decryptedName = new String(encryptedName).replace("-", " ");
      if(decryptedName.contains("northpole")) return sectorId + "";
    }
    return "No match found.";
  }

  /**
   * Returns a stack of keys, sorted by their corresponding value in decreasing order. Ties
   * are resolved by comparing the key alphabetically.
   */
  private static Stack<Character> sortByValueAlphabetically(HashMap<Character, Integer> map) {
    //Find the highest highestValues in the array
    int highestValue = 0;
    for (Integer count : map.values()) {
      if (count > highestValue) highestValue = count;
    }

    //Find out who these highestValues belong to
    Stack<Character> sortedCharacters = new Stack<>();
    for (int lookingFor = highestValue; lookingFor > 0; lookingFor--) {
      SortedSet<Integer> charsWithValue = new TreeSet<>();
      for (Character key : map.keySet()) {
        if (map.get(key) == lookingFor) charsWithValue.add(Character.getNumericValue(key));
      }
      for (int toAdd : charsWithValue) sortedCharacters.add(Character.forDigit(toAdd, 36));
    }

    return sortedCharacters;
  }

}
