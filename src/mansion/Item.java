package mansion;

import java.util.Objects;

/**
 * This is the Item class which implements the ItemInterface interface.
 * It initializes the index location of the item along with the
 * name of the item and its corresponding damage value.
 */
public class Item implements ItemInterface {
  private final int itemRoomIndex;
  private final int itemDamageValue;
  private final String itemName;

  /**
   * This is the Item class constructor.
   * This is useful in creating Item class objects.
   * 
   * @param itemRoomIndex the index of the specified location.
   * @param itemDamageValue the item's damage value.
   * @param itemName the name of the item.
   */
  public Item(int itemRoomIndex, int itemDamageValue, String itemName) 
          throws IllegalArgumentException {
    if (itemName == null || itemName.trim().isEmpty() || "".equals(itemName.trim())) {
      throw new IllegalArgumentException("Invalid item name");
    }
    if (itemDamageValue < 0 || itemRoomIndex < 0) {
      throw new IllegalArgumentException("Invalid item damage value and index location");
    }
    if (itemRoomIndex < 0) {
      throw new IllegalArgumentException("Invalid item index location");
    }
    this.itemRoomIndex = itemRoomIndex;
    this.itemDamageValue = itemDamageValue;
    this.itemName = itemName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemDamageValue, itemName, itemRoomIndex);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Item)) {
      return false;
    }
    Item other = (Item) obj;
    return itemDamageValue == other.itemDamageValue && Objects.equals(itemName, other.itemName)
        && itemRoomIndex == other.itemRoomIndex;
  }

  @Override
  public int getItemRoomIndex() {
    return itemRoomIndex;
  }

  @Override
  public int getItemDamageValue() {
    return itemDamageValue;
  }

  @Override
  public String getItemName() {
    return itemName;
  }

  @Override
  public String toString() {
    return String.format("Item %s is in room index %d and has damage value %d", this.getItemName(),
        this.getItemRoomIndex(), this.getItemDamageValue());
  }

}
