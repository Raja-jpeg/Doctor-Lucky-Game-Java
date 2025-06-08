package milestone;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import org.junit.Test;

/**
 * Test class for World.
 */
public class TheWorldTest {

  /**
   * Test case to verify the parsing of the world description.
   */
  @Test
  public void testParsing() {
    // Test if the parsing of world description is correct
    TheWorld world = new TheWorld();
    try (BufferedReader bufferedReader = 
        new BufferedReader(new FileReader("C:/Users/rajas/Downloads/mansion.txt"))) {
      // ... Perform parsing and assertions here
      String line = bufferedReader.readLine();
      String[] worldDescription = line.split(" ");
      int numRows = Integer.parseInt(worldDescription[0]);
      int numColumns = Integer.parseInt(worldDescription[1]);

      assertEquals(36, numRows);
      assertEquals(30, numColumns);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
     * Test case to verify the readWorld() method.
     */
  @Test
  public void testReadWorld() {
    // Define the test world description
    String worldDescription = "5 5\n" 
              + "10 Dr. Strange\n" 
              + "4\n" 
              + "0 0 1 1 Room 1\n" 
              + "1 0 2 1 Room 2\n" 
              + "0 1 1 2 Room 3\n" 
              + "1 1 2 2 Room 4\n" 
              + "3\n" 
              + "0 2 1 Item 1\n" 
              + "2 2 2 Item 2\n" 
              + "3 0 3 Item 3";

    // Create a StringReader to read the world description
    try (StringReader stringReader = new StringReader(worldDescription);
         BufferedReader bufferedReader = new BufferedReader(stringReader)) {

      // Create a new TheWorld instance
      TheWorld world = new TheWorld();
      // Call the readWorld method with the buffered reader
      world.readWorld(bufferedReader);

      // Verify the parsed world information

      // Verify the number of rows and columns
      assertEquals(5, world.getNumRows());
      assertEquals(5, world.getNumColumns());

      // Verify the target character details
      assertEquals(10, world.getTargetCharacter().getHealth());
      assertEquals("Dr. Strange", world.getTargetCharacter().getName());

      // Verify the number of spaces/rooms
      assertEquals(4, world.getSpaces().size());

      // Verify the room details
      Space room1 = world.getSpaces().get(0);
      assertEquals(0, room1.getUpperLeftRow());
      assertEquals(0, room1.getUpperLeftColumn());
      assertEquals(1, room1.getLowerRightRow());
      assertEquals(1, room1.getLowerRightColumn());
      assertEquals("Room 1", room1.getDescriptor());

      Space room2 = world.getSpaces().get(1);
      assertEquals(1, room2.getUpperLeftRow());
      assertEquals(0, room2.getUpperLeftColumn());
      assertEquals(2, room2.getLowerRightRow());
      assertEquals(1, room2.getLowerRightColumn());
      assertEquals("Room 2", room2.getDescriptor());

      Space room3 = world.getSpaces().get(2);
      assertEquals(0, room3.getUpperLeftRow());
      assertEquals(1, room3.getUpperLeftColumn());
      assertEquals(1, room3.getLowerRightRow());
      assertEquals(2, room3.getLowerRightColumn());
      assertEquals("Room 3", room3.getDescriptor());

      Space room4 = world.getSpaces().get(3);
      assertEquals(1, room4.getUpperLeftRow());
      assertEquals(1, room4.getUpperLeftColumn());
      assertEquals(2, room4.getLowerRightRow());
      assertEquals(2, room4.getLowerRightColumn());
      assertEquals("Room 4", room4.getDescriptor());

      // Verify the number of items
      assertEquals(3, world.getItems().size());

      // Verify the item details
      Item item1 = world.getItems().get(0);
      assertEquals(0, item1.getRoomIndex());
      assertEquals("Item 1", item1.getName());

      Item item2 = world.getItems().get(1);
      assertEquals(2, item2.getRoomIndex());
      assertEquals("Item 2", item2.getName());

      Item item3 = world.getItems().get(2);
      assertEquals(3, item3.getRoomIndex());
      assertEquals("Item 3", item3.getName());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
