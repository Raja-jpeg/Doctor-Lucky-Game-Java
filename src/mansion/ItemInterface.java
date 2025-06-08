package mansion;

/**
 * This is the ItemInterface interface.
 * It deals with information related to item name, index, and damage value.
 */
public interface ItemInterface {

  /** 
   * Returns the item index given that the input exists.
   * 
   * @return the index location.
   */
  int getItemRoomIndex();

  /**
   * Returns the damage value of the item.
   * 
   * @return damage power.
   */
  int getItemDamageValue();

  /**
   * Returns the name of the item.
   * 
   * @return item name.
   */
  String getItemName();
}
