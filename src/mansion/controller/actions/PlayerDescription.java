package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the PlayerDescription class which implements the ActionInterface.
 * It acts as the player description command in the game.
 */
public class PlayerDescription implements ActionInterface {

  /**
   * This is the PlayerDescription class constructor.
   * It is helpful in initializing the PlayerDescription class fields. 
   */
  public PlayerDescription() {
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalArgumentException, IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    worldInstance.getPlayerDescription();
  }

}
