package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the AddComputerPlayer class which implements the ActionInterface.
 * It acts as adding the computer-controlled player command.
 *
 */
public class AddComputerPlayer implements ActionInterface {
  /**
   * This is the AddComputerPlayer class constructor.
   * It is helpful in initializing the AddComputerPlayer class fields.
   * 
   */
  public AddComputerPlayer() {

  }

  @Override
  public void execute(GameControls worldInstance) throws IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    worldInstance.addComputerPlayer();
  }

}