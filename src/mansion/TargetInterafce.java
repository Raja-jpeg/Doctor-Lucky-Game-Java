package mansion;

/**
 * This is the Target interface.
 * It deals with information of target health and target name.
 */
public interface TargetInterafce {

  /**
   * Returns the health value of the target.
   * 
   * @return target health value.
   */
  int getTargetHealth();

  /**
   * Returns the name of the target.
   * 
   * @return the name of the target.
   */
  String getTargetName();

  /**
   * Updates the health value of the target after attack.
   * 
   * @param targetHealth target health value.
   */
  void updateTargetHealth(int targetHealth);

  /**
   * Returns the location or name of the room, the target is in.
   * 
   * @return target name of the room.
   */
  String getTargetRoomName();

  /**
   * Updates the room name of the target after each turn.
   * 
   * @param targetRoomName location of the target.
   */
  void updateTargetRoomName(String targetRoomName);
}
