package mansion;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is the PickItem class which impelements ActionInterface.
 */
public class PickItem implements ActionInterface {
  private final Scanner scan;
  private final Appendable out;
  
  /**
   * This is the PickItem class constructor.
   * 
   * @param scan the input variable for the user input.
   * @param out the output.
   */
  public PickItem(Scanner scan, Appendable out) {
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
          String itemName = this.scan.nextLine();
          worldObject.playerPickItems(itemName);
          this.out.append("Player picked item\n");
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
