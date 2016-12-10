package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.math.BigInteger;
import java.util.HashMap;

public class _2016Nine extends Challenge {

  private String compressedFile;
  private HashMap<String, BigInteger> lengthCache = new HashMap<>();

  public _2016Nine() {
    super(9);
    compressedFile = Utils.getInput(2016, 9).get(0);
  }

  public String puzzleOne() {
    lengthCache.clear();
    return getLength(compressedFile, false).toString();
  }

  public String puzzleTwo() {
    lengthCache.clear();
    return getLength(compressedFile, true).toString();
  }

  private BigInteger getLength(String compressed, boolean deep) {

    //Respond from the cache if already calculated
    if (lengthCache.containsKey(compressed)) {
      return lengthCache.get(compressed);
    }

    char[] chars = compressed.toCharArray();
    BigInteger length = BigInteger.ZERO;

    boolean insideMarker = false;
    int leftToCollect = 0;
    int timesToRepeat = 0;
    StringBuilder markerRefersTo = new StringBuilder();
    StringBuilder markerCollectLength = new StringBuilder();
    StringBuilder markerRepeatLength = new StringBuilder();

    for (int pointer = 0; pointer < chars.length; pointer++) {
      if (insideMarker == false && chars[pointer] != '(' && leftToCollect == 0) {
        //We're not in a marker, we're not entering a marker, and we're not collecting text
        length = length.add(BigInteger.ONE);
      } else if (leftToCollect > 0 && !insideMarker) {
        //We're currently collecting characters to fulfil a marker's dreams
        markerRefersTo.append(chars[pointer]);
        leftToCollect--;
        if (leftToCollect == 0) {
          //Let's do what the marker told us to
          for (int i = 0; i < timesToRepeat; i++) {
            if (deep) {
              length = length.add(getLength(markerRefersTo.toString(), true));
            } else {
              length = length.add(BigInteger.valueOf(markerRefersTo.length()));
            }
          }
          markerRefersTo = new StringBuilder();
        }
      } else {
        //We're still in the process of reading this marker
        if (chars[pointer] == '(' || chars[pointer] == ')') {
          //Let's enter or exit this marker
          insideMarker = (chars[pointer] == '(');
          if (!insideMarker) {
            //Reset before next marker
            timesToRepeat = Integer.parseInt(markerRepeatLength.toString());
            markerCollectLength = new StringBuilder();
            markerRepeatLength = new StringBuilder();
          }
        } else if (chars[pointer] == 'x') {
          leftToCollect = Integer.parseInt(markerCollectLength.toString());
        } else {
          if (leftToCollect == 0) {
            //We're still working out how many characters to collect
            markerCollectLength.append(chars[pointer]);
          } else {
            //We've hit the 'x' character and now want to know how many times to repeat for
            markerRepeatLength.append(chars[pointer]);
          }
        }
      }
    }

    lengthCache.put(compressed, length);
    return length;
  }

}
