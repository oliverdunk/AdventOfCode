package com.oliverdunk.adventofcode.challenges._2016;

import com.oliverdunk.adventofcode.challenges.Challenge;
import com.oliverdunk.adventofcode.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class _2016Ten extends Challenge {

  private List<String> instructions;
  private Bot[] bots = new Bot[250];
  private HashMap<Integer, Stack<Integer>> outputs = new HashMap<>();

  public _2016Ten() {
    super(10);
    instructions = Utils.getInput(2016, 10);
  }

  public String puzzleOne() {

    //Load all instructions as to how the bots should run
    for (String instruction : instructions) {
      runInstruction(instruction);
    }

    //Run the simulation
    Stack<Bot> botsLeftToEmulate = new Stack();

    //Find a bot which already has two items
    for (int i = 0; i < bots.length; i++) {
      if (getBot(i).isFull()) {
        botsLeftToEmulate.push(bots[i]);
      }
    }

    int responsible =  -1;

    //Keep running comparisons until we end up hitting 61 and 17
    while (!botsLeftToEmulate.empty()) {
      Bot currentBot = botsLeftToEmulate.pop();
      if (currentBot.isFull()) {
        int higher = currentBot.highestChip();
        int lower = currentBot.lowestChip();
        if (higher == 61 && lower == 17) {
          responsible = currentBot.id;
        }
        if (currentBot.lowToBot != -1) {
          getBot(currentBot.lowToBot).give(lower);
          if (getBot(currentBot.lowToBot).isFull()) {
            botsLeftToEmulate.push(getBot(currentBot.lowToBot));
          }
        }
        if (currentBot.highToBot != -1) {
          getBot(currentBot.highToBot).give(higher);
          if (getBot(currentBot.highToBot).isFull()) {
            botsLeftToEmulate.push(getBot(currentBot.highToBot));
          }
        }
        if (currentBot.lowToOut != -1) {
          Stack output = outputs.get(currentBot.lowToOut);
          if (output == null) {
            output = new Stack();
            outputs.put(currentBot.lowToOut, output);
          }
          output.push(lower);
        }
        if (currentBot.highToOut != -1) {
          Stack output = outputs.get(currentBot.highToOut);
          if (output == null) {
            output = new Stack();
            outputs.put(currentBot.highToOut, output);
          }
          output.push(higher);
        }
      }
    }

    return responsible + "";
  }

  public String puzzleTwo() {
    return (outputs.get(0).pop() * outputs.get(1).pop() * outputs.get(2).pop()) + "";
  }

  private void runInstruction(String instruction) {
    String[] parts = instruction.split(" ");

    //bot 127 gives low to output 1 and high to bot 180
    if (parts[0].equalsIgnoreCase("bot")) {
      Bot targetBot = getBot(Integer.parseInt(parts[1]));

      //Output boxes won't change the outcome of our simulation, for now
      if (!parts[5].equalsIgnoreCase("output")) {
        targetBot.lowToBot = Integer.parseInt(parts[6]);
      } else {
        targetBot.lowToOut = Integer.parseInt(parts[6]);
      }

      if (!parts[10].equalsIgnoreCase("output")) {
        targetBot.highToBot = Integer.parseInt(parts[11]);
      } else {
        targetBot.highToOut = Integer.parseInt(parts[11]);
      }
    }

    //value 2 goes to bot 2
    if (parts[0].equalsIgnoreCase("value")) {
      getBot(Integer.parseInt(parts[5])).give(Integer.parseInt(parts[1]));
    }
  }

  private Bot getBot(int id) {
    if (bots[id] != null) {
      return bots[id];
    }
    Bot newBot = new Bot(id);
    bots[id] = newBot;
    return newBot;
  }

  private class Bot {

    public int id;
    public int lowToBot = -1;
    public int highToBot = -1;
    public int lowToOut = -1;
    public int highToOut = -1;
    public int firstChip = -1;
    public int secondChip = -1;

    public Bot(int id) {
      this.id = id;
    }

    public void give(int chip) {
      if (firstChip == -1) {
        firstChip = chip;
      } else {
        secondChip = chip;
      }
    }

    public boolean isFull() {
      return firstChip != -1 && secondChip != -1;
    }

    public int highestChip() {
      return firstChip > secondChip ? firstChip : secondChip;
    }

    public int lowestChip() {
      return firstChip < secondChip ? firstChip : secondChip;
    }

    public String toString() {
      return String.format("ID: %s, lowTo: %s, highTo: %s, holding: [%s, %s]",
              id, lowToBot, highToBot, firstChip, secondChip);
    }

  }

}
