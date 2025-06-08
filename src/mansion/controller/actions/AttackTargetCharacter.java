package mansion.controller.actions;

import java.io.IOException;
import java.util.Scanner;
import mansion.GameControls;

/**
 * This is the AttackTarget class which implements ActionInterface.
 * It acts as attacking the target character command in the game.
 *
 */
public class AttackTargetCharacter implements ActionInterface {

  private final Scanner scanner;
  private final Appendable out;

  /**
   * This is the AttackTarget class constructor.
   * It is helpful in initializing the AttackTarget class fields.
   * 
   * @param scanner Scanner
   * @param out  Appendable
   */
  public AttackTargetCharacter(Scanner scanner, Appendable out) throws IllegalArgumentException {
    if (scanner == null) {
      throw new IllegalArgumentException("Invalid Scanner Parameter");
    }
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.scanner = scanner;
    this.out = out;
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalStateException, IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    try {
      while (true) {
        try {
          String attackInformation = "";
          if (worldInstance.checkIfPlayerHasItems()) {
            out.append("Enter the name of the item" + "\n");
            String itemName = scanner.nextLine();
            attackInformation = worldInstance.attackTarget(itemName);
          } else {
            attackInformation = worldInstance.pokeTheTargetCharacter();
          }
          out.append(attackInformation + "\n");
          break;
        } catch (IllegalStateException e) {
          out.append(e.getMessage() + "\n");
          break;
        } catch (IllegalArgumentException e) {
          out.append(e.getMessage() + "\n");
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }

}
