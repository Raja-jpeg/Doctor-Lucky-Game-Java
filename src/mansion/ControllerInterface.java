package mansion;

/**
 * This is the ControllerInterface.
 * It interacts with the users via console and performs desired actions.
 */
public interface ControllerInterface {

  /**
   * Starts the game from the controller.
   * 
   */
  void playGame(GameControls game) throws IllegalArgumentException;
}
