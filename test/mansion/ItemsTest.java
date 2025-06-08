package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is ItemsTest class.
 * This class tests the items in the room.
 */
public class ItemsTest {
  private Item item;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.item = new Item(6, 2, "Trowel");
  }
  
  /**
   * Item class constructor.
   * 
   * @param roomIndex the index of the location.
   * @param damageHealth the damage value.
   * @param itemName the name of the item.
   * @return Item object.
   */
  protected Item item(int roomIndex, int damageHealth, String itemName) {
    return new Item(roomIndex, damageHealth, itemName);
  }
  
  @Test
  public void testItemDamagePower() {
    Assert.assertEquals(this.item.getDamageHealth(), 2L);
  }
  
  @Test
  public void testItemName() {
    Assert.assertEquals(this.item.getItemName(), "Trowel");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDamagePower() {
    item(6, -12, "Trowel");
  }
  
  @Test
  public void testItemEquals() {
    Assert.assertTrue(this.item.equals(this.item));
    Assert.assertTrue(this.item.equals(item(6, 2, "Trowel")));
    Assert.assertTrue(item(6, 2, "Trowel").equals(item(6, 2, "Trowel")));
    Assert.assertFalse(this.item.equals(null));
    Assert.assertFalse(this.item.equals(new Object()));
  }
  
  @Test
  public void testItemHashCode() {
    Assert.assertEquals(this.item.hashCode(),
        item(6, 2, "Trowel").hashCode());
  }
  
  @Test
  public void testItemToString() {
    Assert.assertEquals(this.item.toString(),
        "Item Trowel is in room index 6 and has damage power 2");
  }
}
