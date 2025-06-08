package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is the AddHumanPlayerTest class.
 * This class tests by adding the human controlled player to the game.
 */
public class AddHumanPlayerTest {
  private GameControls model;
  private StringBuilder message;
  private StringBuffer out;
  private ActionInterface world;
  
  /**
   * Initializing the values.
   */
  @Before
  public void setUp() {
    this.message = new StringBuilder();
    this.model = (GameControls) new HumanPlayerSimulation(this.message, 1234321);
    this.out = new StringBuffer();
  }
  
  @Test
  public void testAddHumanPlayerTest() {
    String playerName = "sahith";
    int playerItemsLimit = 0;
    String playerRoomName = "Kitchen";
    this.world = (ActionInterface) new AddHumanPlayer(playerName, playerItemsLimit,
          playerRoomName, this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name, " + playerName 
          + "player item limit" 
          + playerItemsLimit + "player room name" + playerRoomName + "\n");
    sb.append(String.format("UniqueCode is 1234321", new Object[0]));
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
}
