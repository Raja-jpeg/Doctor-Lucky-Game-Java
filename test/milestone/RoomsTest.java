package milestone;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the Room class.
 */
public class RoomsTest {

  /**
   * Test case to verify the constructor with coordinates.
   */
  @Test
  public void testConstructorWithCoordinates() {
    // Arrange
    int upperLeftRow = 1;
    int upperLeftColumn = 2;
    int lowerRightRow = 6;
    int lowerRightColumn = 7;
    String roomName = "Crepe";

    // Act
    Rooms room = new Rooms(upperLeftRow, upperLeftColumn, lowerRightRow,
        lowerRightColumn, roomName);

    // Assert
    assertEquals(upperLeftRow, room.getTopLeftX());
    assertEquals(upperLeftColumn, room.getTopLeftY());
    assertEquals(lowerRightRow, room.getBottomRightX());
    assertEquals(lowerRightColumn, room.getBottomRightY());
  }

  /**
   * Test case to verify the getRoomName() method.
   */
  @Test
  public void testGetRoomName() {
    // Arrange
    String roomName = "Living Room";
    Rooms room = new Rooms(1, 2, 6, 7, roomName);

    // Act
    String actualRoomName = room.getRoomName();

    // Assert
    assertEquals(roomName, actualRoomName);
  }
}
