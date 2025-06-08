package mansion;

import java.io.IOException;
import java.util.Scanner;

/**
 * This is AttackTarget class which implements ActionInterface.
 */
public class AttackTarget implements ActionInterface { 
  private final Scanner scan;
  private final Appendable out;

  /**
   *  This is the AttackTarget class constructor.
   *  
   * @param scan is the input variable for user.
   * @param out is the output.
   * @throws IllegalArgumentException if any invalid input.
   */
  public AttackTarget(Scanner scan, Appendable out) throws IllegalArgumentException {
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
          String attackDetials = "";
          if (worldObject.checkIfPlayerHasItems()) {
            this.out.append("Enter item name\n");
            String itemName = this.scan.nextLine();
            attackDetials = worldObject.attackTarget(itemName);
          } else {
            attackDetials = worldObject.attackTargetWithoutItems();
          } 
          this.out.append(String.valueOf(attackDetials) + "\n");
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
