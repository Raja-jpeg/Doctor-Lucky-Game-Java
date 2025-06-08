package mansion;

import java.io.IOException;

/**
 * This is the AddComputerPlayer class which implements the ActionInterface.
 */
public class AddComputerPlayer implements ActionInterface {
  private final Appendable out;
  
  /**
   * This is the AddComputerPlayer constructor.
   * 
   * @param out the output.
   */ 
  public AddComputerPlayer(Appendable out) {
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
    worldObject.addComputerPlayer();
    try {
      this.out.append("Computer player added to the game\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    } 
  }
}
