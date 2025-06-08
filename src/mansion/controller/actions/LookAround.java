package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the LookAround class which implements ActionInterface.
 * It acts as looking around the neighboring spaces in the game.
 *
 */
public class LookAround implements ActionInterface {

  /**
   * This is the LookAround class constructor.
   * It is helpful in initializing the LookAround class fields.
   */
  public LookAround() {
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalArgumentException, IllegalStateException {
    worldInstance.playerLookAround();
  }
}
