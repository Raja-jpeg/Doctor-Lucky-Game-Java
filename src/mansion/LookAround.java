package mansion;

import java.io.IOException;

/**
 * This is the LookAround class which implements ActionInterface.
 */
public class LookAround implements ActionInterface {
  private final Appendable out;
  
  /**
   * This is the LookAround class constructor.
   * 
   * @param out the output.
   */
  public LookAround(Appendable out) {
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.out = out;
  }
  
  @Override
  public void execute(GameControls worldObject) throws IllegalStateException,
      IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    }
    try {
      this.out.append(String.valueOf(worldObject.playerLookAround()) + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    } 
  }
}
