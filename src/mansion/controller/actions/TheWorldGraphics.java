package mansion.controller.actions;

import java.io.IOException;
import mansion.GameControls;

/**
 * This is TheWorldGraphics class which implements ActionInterface.
 * It acts as generating a graphical representation of the game status command.
 *
 */
public class TheWorldGraphics implements ActionInterface {

  private final Appendable out;

  /**
   * This is the TheWorldGraphics class constructor.
   * It is helpful in initializing TheWorldGraphics class fields.
   * 
   * @param out Appendable object.
   */
  public TheWorldGraphics(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Appendable Parameter is invalid.");
    }
    this.out = out;
  }

  @Override
  public void execute(GameControls worldInstance) throws IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    worldInstance.createWorldGraphicalRep();
    try {
      out.append("The generated image is located in the res folder" + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }

}
