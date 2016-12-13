package com.oliverdunk.adventofcode.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Utils {

  public static List<String> getInput(int year, int day) {
    try {
      File file = new File("src/main/resources/inputs/" + year + "/Day" + day + ".txt");
      FileInputStream stream = new FileInputStream(file);

      if (!file.exists()) {
        System.out.println("Input file not found!");
        return null;
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      List<String> lines = new ArrayList<>();
      while (reader.ready()) lines.add(reader.readLine());
      return lines;
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("Failed to fetch input for day " + day + ", " + year + ".");
      return null;
    }
  }

  private static MessageDigest MD5;

  public static String digest(String input) {
    try {
      if (MD5 == null) MD5 = MessageDigest.getInstance("MD5");
      byte[] array = MD5.digest(input.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (Exception ex) {
      System.out.println("Unable to load MD5!");
      return null;
    }
  }

  public static List<int[]> lightsBetweenPoints(String pointOne, String pointTwo) {
    List<int[]> lights = new ArrayList<>();

    int oneX = Integer.parseInt(pointOne.split(",")[0]);
    int oneZ = Integer.parseInt(pointOne.split(",")[1]);
    int twoX = Integer.parseInt(pointTwo.split(",")[0]);
    int twoZ = Integer.parseInt(pointTwo.split(",")[1]);

    for (int x = oneX; x <= twoX; x++) {
      for (int z = oneZ; z <= twoZ; z++) {
        lights.add(new int[]{x, z});
      }
    }

    return lights;
  }

  public static int[] pointToInt(String point) {
    String[] points = point.split(",");
    return new int[]{Integer.parseInt(points[0]), Integer.parseInt(points[1])};
  }

  public static char[] getAlphabet() {
    return "abcdefghijklmnopqrstuvwxyz".toCharArray();
  }

  public static boolean isInteger(String input) {
    try {
      Integer.parseInt(input);
      return true;
    } catch(Exception ex) {
      return false;
    }
  }

}