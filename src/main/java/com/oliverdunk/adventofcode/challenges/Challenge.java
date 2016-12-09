package com.oliverdunk.adventofcode.challenges;

public abstract class Challenge {

  private int day;

  public Challenge(int day) {
    this.day = day;
  }

  public abstract String puzzleOne();

  public abstract String puzzleTwo();

  public int getDay() {
    return day;
  }

}
