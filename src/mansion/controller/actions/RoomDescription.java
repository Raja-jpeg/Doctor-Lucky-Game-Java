package mansion.controller.actions;

import java.io.IOException;
import mansion.GameControls;

/**
 * This is the RoomDescription class which implements ActionInterface.
 * It acts as the room description command in the game. 
 *
 */
public class RoomDescription implements ActionInterface {

  private final String roomName;
  private final Appendable out;

  /**
   * This is the RoomDescription class constructor.
   * It is helpful in initializing the RoomDescription class fields.
   * 
   * @param roomName the name of the specified room.
   * @param out Appendable instance.
   */
  public RoomDescription(String roomName, Appendable out) {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("The provided room name is invalid.");
    }
    if (out == null) {
      throw new IllegalArgumentException("The Appendable Parameter is invalid.");
    }
    this.roomName = roomName;
    this.out = out;
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalArgumentException, IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }

    try {
      out.append(worldInstance.getSpaceInformation(roomName) + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    }

  }

}
