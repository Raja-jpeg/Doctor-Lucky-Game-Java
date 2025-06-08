package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the MovePetCharacter class which implements ActionInterface.
 * It acts as moving the pet character command to a specified room in the mansion.
 *
 */
public class MovePetCharacter implements ActionInterface {

  private final String roomName;

  /**
   * This is the MovePetCharacter class constructor.
   * It is helpful in initializing the MovePetCharacter class fields.
   * 
   * @param roomName the name of the room.
   */
  public MovePetCharacter(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.roomName = roomName;
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalStateException, IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    worldInstance.movePet(roomName);
  }
}
