package mansion;

import java.io.IOException;

/**
 * This is the WorldGraphics class which implements ActionInterface.
 *
 */
public class TheWorldGraphics implements ActionInterface {

  private final Appendable out;

  /**
   * This is the TheWorldGraphics class constructor.
   * 
   * @param out the output.
   */
  public TheWorldGraphics(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.out = out;
  }

  @Override
  public void execute(GameControls worldObject) throws IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    }
    worldObject.createWorldGraphicalRep();
    try {
      out.append("Image in created in res folder" + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }
  }

}
