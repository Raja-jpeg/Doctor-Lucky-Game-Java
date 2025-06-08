package milestone;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


/**
 * This is the controller test class.
 *
 */
public class ControllerTest {
  
  private Controller controller;

  @Before
  public void setUp() {
    controller = new Controller();
  }

  @Test
  public void testAddHumanPlayer() {
    String input = "1\nJohn\n5\nDining Hall\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  
    controller.run(1);
  
    List<Players> playersList = controller.playersList;
    assertEquals(1, playersList.size());
  
    Players player = playersList.get(0);
    assertEquals("John", player.getName());
    assertEquals(5, player.getItemLimit());
    assertEquals("Dining Hall", player.getRoomName());
  }
  
  @Test
  public void testAddComputerPlayer() {
    String input = "1\nTom\n10\nLibrary\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  
    controller.run(1);
  
    List<Players> playersList = controller.playersList;
    assertEquals(1, playersList.size());
  
    Players player = playersList.get(0);
    assertEquals("Tom", player.getName());
    assertEquals(10, player.getItemLimit());
    assertEquals("Library", player.getRoomName());
  }
  
  @Test
  public void testMoveToAdjacentRoom() {
    // Assume the player is initially in the Foyer
    controller.playersList.add(new Players("John", 5, "Foyer"));
  
    String input = "3\nDining Hall\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  
    controller.run(1);
  
    List<Players> playersList = controller.playersList;
    Players player = playersList.get(0);
    assertEquals("Dining Hall", player.getRoomName());
  }
  
  
  @Test
  public void testDisplayPlayerInformation() {
    // Assume the players list contains a player named "John"
    controller.playersList.add(new Players("John", 5, "Dining Hall"));
  
    String input = "5\nJohn\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
  
    controller.run(1);
  }
  
  @Test
  public void testDisplayRoomInformation() {
    String input = "6\nDining Hall\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    controller.run(1);
  }
  
  @Test
  public void testDisplayWorldGraphicalRepresentation() {
    String input = "1\n2\n";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    controller.run(1);
  }
}

