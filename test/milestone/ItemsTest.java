package milestone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * Test class for the Items class.
 */
public class ItemsTest {

  /**
     * Test case to verify the constructor of the Items class.
     */
  @Test
  public void testConstructor() {
    // Arrange
    String itemName = "A";
    int roomIndex = 2;
    int destructionCapacityPoints = 18;

    // Act
    Items item = new Items(itemName, roomIndex, destructionCapacityPoints);

    // Assert
    assertEquals(itemName, item.itemName);
    assertEquals(roomIndex, item.roomIndex);
    assertEquals(destructionCapacityPoints, item.destructionCapacityPoints);
  }

  /**
     * Test case to verify the getItemLocation() method for an existing item.
     */
  @Test
  public void testGetItemLocationForExistingItem() {
    // Arrange
    String itemName = "A";
    int roomIndex = 2;
    Items item = new Items(itemName, roomIndex, 18);

    // Act
    String itemLocation = item.getItemLocation(itemName);

    // Assert
    assertEquals("Room " + roomIndex, itemLocation);
  }

  /**
   * Test case to verify the getItemLocation() method for a non-existing item.
   */
  @Test
    public void testGetItemLocationForNonExistingItem() {
    // Arrange
    String itemName = "nonExistingItem";
    Items item = new Items("A", 2, 18);

    // Act
    String itemLocation = item.getItemLocation(itemName);

    // Assert
    assertNull(itemLocation);
  }

  /**
     * Test case to verify the setItemLocation() method for an existing item.
     */
  @Test
    public void testSetItemLocationForExistingItem() {
    // Arrange
    String itemName = "A";
    int initialRoomIndex = 2;
    int newRoomIndex = 5;
    Items item = new Items(itemName, initialRoomIndex, 18);

    // Act
    item.setItemLocation(itemName, newRoomIndex);
    String itemLocation = item.getItemLocation(itemName);

    // Assert
    assertEquals("Room " + newRoomIndex, itemLocation);
  }

  /**
     * Test case to verify the getDamageValue() method.
     */
  @Test
  public void testGetDamageValue() {
    // Arrange
    String itemName = "A";
    int destructionCapacityPoints = 18;
    Items item = new Items(itemName, 2, destructionCapacityPoints);

    // Act
    int damageValue = item.getDamageValue(itemName);

    // Assert
    assertEquals(destructionCapacityPoints, damageValue);
  }

  /**
     * Test case to verify the behavior when creating an item with a negative room number.
     */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRoomNumber() {
    // Arrange
    String itemName = "A";
    int roomIndex = -7;
    // Act
    new Items(itemName, roomIndex, 25);

    // Expecting an IllegalArgumentException to be thrown
  }

  /**
     * Test case to verify the behavior when setting a negative damage value for an item.
     */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeDamageValue() {
    // Arrange
    String itemName = "A";
    int destructionCapacityPoints = -14;
    Items item = new Items(itemName, 2, 18);

    // Act
    item.setDamageValue(itemName, destructionCapacityPoints);

    // Expecting an IllegalArgumentException to be thrown
  }
}
