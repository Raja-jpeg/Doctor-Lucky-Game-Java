package milestone;



import java.util.ArrayList;
import java.util.List;

/**
 * Represents a room in the world.
 *
 */

public class Rooms {
  public String roomName;
  public List<Items> items;
  private int upperLeftX;
  private int upperLeftY;
  private int lowerRightX;
  private int lowerRightY;

  /**
     * Creates a new instance of the Rooms class with the specified parameters.
     *
     * @param roomName     the name of the room
     * @param upperLeftX   the X coordinate of the upper left corner of the room
     * @param upperLeftY   the Y coordinate of the upper left corner of the room
     * @param lowerRightX  the X coordinate of the lower right corner of the room
     * @param lowerRightY  the Y coordinate of the lower right corner of the room
     */
  public Rooms(int upperLeftX, int upperLeftY, int lowerRightX, int lowerRightY, String roomName) {
    
  
    this.roomName = roomName;
    this.upperLeftX = upperLeftX;
    this.upperLeftY = upperLeftY;
    this.lowerRightX = lowerRightX;
    this.lowerRightY = lowerRightY;
    this.items = new ArrayList<>();
  }

  /**
     * Returns the name of the room.
     *
     * @return the name of the room
     */
  public String getRoomName() {
    return roomName;
  }

  /**
     * Adds an item to the room.
     *
     * @param item the item to add
     */
  public void addItem(Items item) {
    items.add(item);
  }

  /**
     * Returns the list of items in the room.
     *
     * @return the list of items in the room
     */
  public List<Items> getItems() {
    return items;
  }

  public int getTopLeftX() {
    // TODO Auto-generated method stub
    return upperLeftX;
  }

  public int getBottomRightX() {
    // TODO Auto-generated method stub
    return lowerRightX;
  }

  public int getTopLeftY() {
    // TODO Auto-generated method stub
    return upperLeftY;
  }
  
  public int getBottomRightY() {
    // TODO Auto-generated method stub
    return lowerRightY;
  }
}