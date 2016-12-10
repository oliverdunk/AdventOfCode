package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class _2016Eight extends Challenge {

  private List<String> instructions;
  private List<Pixel> pixels = new ArrayList<>();

  private int width;
  private int height;

  public _2016Eight() {
    super(8);
    instructions = Utils.getInput(2016, 8);
  }

  public String puzzleOne() {
    setupScreen(50, 6);

    for (String instruction : instructions) {
      runInstruction(instruction);
    }

    int poweredPixels = 0;
    for (Pixel pixel : pixels) {
      if (pixel.powered) {
        poweredPixels++;
      }
    }

    return poweredPixels + "";
  }

  public String puzzleTwo() {
    printScreen();
    return "For once, you're going to have to work this one out for yourself, lazy.";
  }

  /* Adds all pixels for the current screen dimensions. */
  private void setupScreen(int width, int height) {
    this.width = width;
    this.height = height;
    for (int y = 1; y <= height; y++) {
      for (int x = 1; x <= width; x++) {
        pixels.add(new Pixel(x, y));
      }
    }
  }

  /**
   * Prints the screen. A little hacky, but it makes the actual puzzle easier to solve.
   */
  private void printScreen() {
    System.out.println();
    Pixel[][] screen = new Pixel[width][height];

    for (Pixel pixel : pixels) {
      screen[pixel.x - 1][pixel.y - 1] = pixel;
    }

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        System.out.print(screen[x][y].powered ? "#" : ".");
      }
      System.out.println();
    }
    System.out.println();
  }

  private void runInstruction(String instruction) {
    String[] parts = instruction.split(" ");

    //Rect command (rect 3x2)
    if (parts[0].equalsIgnoreCase("rect")) {
      int width = Integer.parseInt(parts[1].split("x")[0]);
      int height = Integer.parseInt(parts[1].split("x")[1]);
      for (Pixel pixel : pixels) {
        if (pixel.x <= width && pixel.y <= height) {
          pixel.powered = true;
        }
      }
    }

    //Rotate command (rotate column/row x/y=0 by 4)
    if(parts[0].equalsIgnoreCase("rotate")) {
      boolean row = parts[1].equalsIgnoreCase("row");
      int selector = Integer.parseInt(parts[2].split("=")[1]) + 1;
      int amount = Integer.parseInt(parts[4]);
      for (Pixel pixel : pixels) {
        if ((!row && pixel.x == selector) || (row && pixel.y == selector)) {
          if (row) pixel.x = ((pixel.x + amount - 1) % width) + 1;
          else pixel.y = ((pixel.y + amount - 1) % height) + 1;
        }
      }
    }
  }

  private class Pixel {

    public int x;
    public int y;
    public boolean powered;

    public Pixel(int x, int y) {
      this.x = x;
      this.y = y;
      this.powered = false;
    }

  }

}
