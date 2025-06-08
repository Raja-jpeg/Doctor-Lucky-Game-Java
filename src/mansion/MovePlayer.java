package mansion;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the MovePlayer class which implements ActionInterface.
 */
public class MovePlayer implements ActionInterface {
  private final Scanner scan;
  private final Appendable out;
  
  /**
   * This is the MovePlayer class constructor.
   * 
   * @param scan the input variable for user input.
   * @param out the output.
   */
  public MovePlayer(Scanner scan, Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter"); 
    }
    if (scan == null) {
      throw new IllegalArgumentException("Invalid Scanner Parameter"); 
    }
    this.scan = scan;
    this.out = out;
  }
  
  @Override
  public void execute(GameControls worldObject) 
      throws IllegalStateException, IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    }
    try {
      while (true) {
        try {
          String roomName = this.scan.nextLine();
          worldObject.movePlayer(roomName);
          this.out.append("Player moved to another room\n");
          break;
        } catch (IllegalStateException e) {
          this.out.append(String.valueOf(e.getMessage()) + "\n");
          break;
        } catch (IllegalArgumentException e) {
          this.out.append(String.valueOf(e.getMessage()) + "\n");
        } 
      } 
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    } 
  }
}
