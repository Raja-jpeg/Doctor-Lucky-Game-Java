package milestone;



/**
 * Represents an item in the world.
 */

public class Items {
  public String itemName;
  public int roomIndex;
  public int destructionCapacityPoints;
  
  /**
     * Constructs an item with the specified name, room index, and destruction capacity points.
     *
     * @param itemName                  the name of the item
     * @param roomIndex                 the room index where the item is located
     * @param destructionCapacityPoints the destruction capacity points of the item
     */
  public Items(String itemName, int roomIndex, int destructionCapacityPoints) {
    this.itemName = itemName;
    this.roomIndex = roomIndex;
    this.destructionCapacityPoints = destructionCapacityPoints;
  }

  /**
     * Gets the location of the item.
     *
     * @param itemName the name of the item
     * @return the location of the item
     */
  public String getItemLocation(String itemName) {
    // Implementation for getItemLocation method
    return "Room " + roomIndex;
  }
  
  public String getName() {
    return itemName;
  }
  /**
     * Gets the damage value of the item.
     *
     * @param itemName the name of the item
     * @return the damage value of the item
     */
  
  public int getDamageValue(String itemName) {
    // Implementation for getDamageValue method
    return destructionCapacityPoints;
  }

}