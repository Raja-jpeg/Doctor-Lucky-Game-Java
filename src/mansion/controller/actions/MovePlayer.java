package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the MovePlayer class which implements ActionInterface.
 * It acts as moving the player to the valid specified location command in the game.
 *
 */
public class MovePlayer implements ActionInterface {

  private final int horizontalAxisPosition;
  private final int verticalAxisPosition;

  /**
   * This is the MovePlayer class constructor.
   * It is helpful in initializing MovePlayer class fields.
   * 
   * @param horizontalAxisPosition horizontal axis position.
   * @param verticalAxisPosition vertical axis position.
   */
  public MovePlayer(int horizontalAxisPosition, int verticalAxisPosition) {
    this.horizontalAxisPosition = horizontalAxisPosition;
    this.verticalAxisPosition = verticalAxisPosition;
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalArgumentException, IllegalStateException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    try {
      worldInstance.movePlayer(horizontalAxisPosition, verticalAxisPosition);
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Cannot move player");
    } catch (IllegalArgumentException e) {
      throw new IllegalStateException("Cannot move player");
    }
  }
}
