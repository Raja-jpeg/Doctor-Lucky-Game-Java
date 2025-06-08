import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mansion.Room;
import org.junit.Before;
import org.junit.Test;

/**
 * This is RoomTest class.
 * This class tests the space in the mansion.
 */
public class RoomTest {
  private Room room;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.room = new Room("Kitchen", 16, 3, 21, 10);
  }
  
  /**
   * Room class constructor.
   * 
   * @param roomName the name of the room.
   * @param row1 the upper left row.
   * @param column1 the upper left column.
   * @param row2 the lower left row.
   * @param column2 the lower right row.
   * @return roomObject the object of the room.
   */
  protected Room room(String roomName, int row1, int column1, int row2, int column2) {
    return new Room(roomName, row1, column1, row2, column2);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomParameters() {
    room("Kitchen", 10, 20, -10, 30);
  }
  
  @Test
  public void testRoomName() {
    assertEquals(this.room.getRoomName(), "Kitchen");
  }
  
  @Test
  public void testRoomUpperRow() {
    assertEquals(this.room.getUpperLeftRow(), 16L);
  }
  
  @Test
  public void testRoomUpperColumn() {
    assertEquals(this.room.getUpperLeftColumn(), 3L);
  }
  
  @Test
  public void testRoomLowerRow() {
    assertEquals(this.room.getLowerRightRow(), 21L);
  }
  
  @Test
  public void testRoomLowerColumn() {
    assertEquals(this.room.getLowerRightColumn(), 10L);
  }
  
  @Test
  public void testRoomEquals() {
    assertTrue(this.room.equals(this.room));
    assertTrue(this.room.equals(room("Kitchen", 16, 3, 21, 10)));
    assertTrue(room("Kitchen", 16, 3, 21, 10).equals(room("Kitchen", 16, 3, 21, 10)));
    assertFalse(this.room.equals(null));
    assertFalse(this.room.equals(new Object()));
  }
  
  @Test
  public void testRoomHashCode() {
    assertEquals(this.room.hashCode(), 
        room("Kitchen", 16, 3, 21, 10).hashCode());
  }
  
  @Test
  public void testRoomToString() {
    assertEquals(this.room.toString(),
        "Kitchen has upper corners[16,3] and lower corners[21,10]");
  }
}
