package mansion;

import java.util.List;

/**
 * This is the Room interface.
 * It deals with the information about the name of the room and its coordinates.
 */
public interface RoomInterface {

  /**
   * Returns the name of the room.
   * 
   * @return the name of the room.
   */
  String getRoomName();

  /**
   * Returns the upper left row position.
   * 
   * @return top left row position.
   */
  int getUpperLeftRow();

  /**
   * Returns the bottom right row position.
   * 
   * @return the bottom right row position.
   */
  int getLowerRightRow();

  /**
   * Returns the upper column position. 
   * 
   * @return top column position.
   */
  int getUpperLeftColumn();

  /**
   * Returns the lower right column position.
   * 
   * @return bottom column position.
   */
  int getLowerRightColumn();

  /**
   * Returns the items list in the specified room.
   * 
   * @return items list in the room.
   */
  List<String> getRoomItems();

  /**
   * Adds an item to the specified room.
   * 
   * @param itemName name of the item to be added.
   */
  void addItem(String itemName);

  /**
   * Removes the item from the specified room.
   * 
   * @param itemName name of the item to be removed.
   */
  void removeItem(String itemName);
}
