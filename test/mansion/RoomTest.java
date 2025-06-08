package mansion;

import org.junit.Assert;
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
    Assert.assertEquals(this.room.getRoomName(), "Kitchen");
  }
  
  @Test
  public void testRoomUpperRow() {
    Assert.assertEquals(this.room.getUpperLeftRow(), 16L);
  }
  
  @Test
  public void testRoomUpperColumn() {
    Assert.assertEquals(this.room.getUpperLeftColumn(), 3L);
  }
  
  @Test
  public void testRoomLowerRow() {
    Assert.assertEquals(this.room.getLowerRightRow(), 21L);
  }
  
  @Test
  public void testRoomLowerColumn() {
    Assert.assertEquals(this.room.getLowerRightColumn(), 10L);
  }
  
  @Test
  public void testRoomEquals() {
    Assert.assertTrue(this.room.equals(this.room));
    Assert.assertTrue(this.room.equals(room("Kitchen", 16, 3, 21, 10)));
    Assert.assertTrue(room("Kitchen", 16, 3, 21, 10).equals(room("Kitchen", 16, 3, 21, 10)));
    Assert.assertFalse(this.room.equals(null));
    Assert.assertFalse(this.room.equals(new Object()));
  }
  
  @Test
  public void testRoomHashCode() {
    Assert.assertEquals(this.room.hashCode(), 
        room("Kitchen", 16, 3, 21, 10).hashCode());
  }
  
  @Test
  public void testRoomToString() {
    Assert.assertEquals(this.room.toString(),
        "Kitchen has upper corners[16,3] and lower corners[21,10]");
  }
}
