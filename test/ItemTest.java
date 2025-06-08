import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mansion.Item;
import org.junit.Before;
import org.junit.Test;


/**
 * This is the ItemTest class.
 * It is used to test the items in the mansion.
 */
public class ItemTest {
  private Item item;

  /**
   * Initializing the class fields.
   */
  @Before
  public void setUp() {
    item = new Item(6, 2, "Trowel");
  }

  /**
   * The Item class constructor.
   * 
   * @param itemRoomIndex room index of the item.
   * @param itemDamageValue damage value of the item.
   * @param itemName name of the item.
   * @return Item class object.
   */
  protected Item item(int itemRoomIndex, int itemDamageValue, String itemName) {
    return new Item(itemRoomIndex, itemDamageValue, itemName);
  }

  /**
   * Verify the item damage value.
   */
  @Test
  public void testItemDamageValue() {
    assertEquals(item.getItemDamageValue(), 2);
  }

  /**
   * Verify the item name.
   */
  @Test
  public void testItemName() {
    assertEquals(item.getItemName(), "Trowel");
  }

  /**
   * Verify if the item damage value is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidDamageValue() {
    item(6, -12, "Trowel");
  }

  /**
   * Testing equals method.
   */
  @Test
  public void testItemEquals() {
    assertTrue(item.equals(item));
    assertTrue(item.equals(item(6, 2, "Trowel")));
    assertTrue((item(6, 2, "Trowel")).equals(item(6, 2, "Trowel")));
    assertFalse(item.equals(null));
    assertFalse(item.equals(new Object()));
  }

  /**
   * Testing hashCode method.
   */
  @Test
  public void testItemHashCode() {
    assertEquals(item.hashCode(), item(6, 2, "Trowel").hashCode());
  }

  /**
   * Testing toString for items.
   */
  @Test
  public void testItemToString() {
    assertEquals(item.toString(), "Item Trowel is in room index 6 and has damage value 2");
  }
}
