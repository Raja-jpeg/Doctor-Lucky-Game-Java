package mansion;

import java.io.IOException;

/**
 * This is the RoomDescription class which implements ActionInterface.
 */
public class RoomDescription implements ActionInterface {
  private final String roomName;
  private final Appendable out;
  
  /**
   * This is the RoomDescription class constructor.
   * 
   * @param roomName the name of the room.
   * @param out the output.
   */
  public RoomDescription(String roomName, Appendable out) {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("Invalid room name");
    }
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.roomName = roomName;
    this.out = out;
  }
  
  @Override
  public void execute(GameControls worldObject) throws IllegalArgumentException,
      IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    }
    try {
      this.out.append(String.valueOf(worldObject.getSpaceInformation(this.roomName)) + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    } 
  }
}
