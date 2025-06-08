package mansion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import mansion.controller.Controller;
import mansion.controller.ControllerInterface;
import mansion.view.MansionBoardGameViewImpl;
import mansion.view.MansionBoardGameViewInterface;


/**
 * This is the Driver class. It is the entry point of the game.
 * It gives access to the controller to interact with the game.
 */
public class Driver {

  /**
   * This is the main method to run the Driver class.
   * 
   * @param args the command line arguments: file location and number of turns.
   */
  public static void main(String[] args) {
    try {
      String file = args[0];
      String numberOfTurns = args[1];
      int turns = Integer.parseInt(numberOfTurns);
      Readable fr = new FileReader(file);
      GameControls gamemodel = new TheWorld(fr, new RandomImpl(), turns);
      MansionBoardGameViewInterface gameview = new MansionBoardGameViewImpl(gamemodel);
      ControllerInterface gamecontroller = new Controller(gamemodel, turns, args[0]);
      gamecontroller.playGame(gameview);
    } catch (IllegalArgumentException e) {
      System.out.println("Illegal argument exception raised" + e);
      System.exit(1);
    } catch (FileNotFoundException e) {
      System.out.println("File not found exception raised");
      System.exit(1);
    }
  }
}

