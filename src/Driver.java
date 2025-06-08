package milestone;


import java.util.Scanner;

/**
 * The main class that serves as the entry point for the game.
 */
public class Driver {

  /**
   * The main method that starts the game.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) { 
    
    System.out.println("Enter number of turns to be exhibited:");
    String turns = args[0];
    Controller c = new Controller();
    c.run(Integer.parseInt(turns));


  }
}
