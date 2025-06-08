package mansion;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the MovePet class which implements the ActionInterface.
 */
public class MovePet implements ActionInterface {
  private final Scanner scan;
  
  private final Appendable out;
  
  /**
   * This is the MovePet class constructor.
   * 
   * @param scan the input variable to read from the user.
   * @param out the output.
   */
  public MovePet(Scanner scan, Appendable out) {
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
  public void execute(GameControls worldObject) throws IllegalStateException,
      IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    } 
    try {
      while (true) {
        try {
          String roomName = this.scan.nextLine();
          worldObject.movePet(roomName);
          this.out.append("Pet moved to another room\n");
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
