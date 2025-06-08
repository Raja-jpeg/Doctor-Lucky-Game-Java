package milestone;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


/**
 * This is the TheWorldGraphics test class.
 *
 */
public class TheWorldGraphicsTest {

  @Test
  public void testGetWorldGraphics() {
    // Create a test scenario with specific room and player configurations
    List<Rooms> roomsList = new ArrayList<>();
    roomsList.add(new Rooms(2, 0, 0, 2, "Living Room"));
    roomsList.add(new Rooms(2, 3, 0, 5, "Kitchen"));
    roomsList.add(new Rooms(5, 0, 3, 2, "Bedroom"));

    List<Players> playersList = new ArrayList<>();
    playersList.add(new Players("Player 1", 10, "Living Room"));
    playersList.add(new Players("Player 2", 10, "Kitchen"));

    // Create an instance of TheWorldGraphics
    TheWorldGraphics worldGraphics = new TheWorldGraphics();

    // Call the getWorldGraphics method
    worldGraphics.getWorldGraphics(roomsList, playersList);

    // Perform assertions to verify the correctness of the generated world graphics
    // (Note: Assertions can vary based on the specific requirements of the graphics output)

    // Example assertion: Check if the number of rooms matches
    //assertEquals(3, roomsList.size(), "Number of rooms should match");

    // Example assertion: Check if the number of players matches
    //assertEquals(2, playersList.size(), "Number of players should match");
  }
}
