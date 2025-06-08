package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.AddHumanPlayer;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the AddHumanPlayerTest class.
 * It tests the AddHumanPlayer command in the controller.
 *
 */
public class AddHumanPlayerTest {
  private GameControls humanPlayerMockModel;
  private StringBuilder message;
  private ActionInterface mansion;

  /**
   * Initializing the class fields.
   */
  @Before
  public void setUp() {
    message = new StringBuilder();
    humanPlayerMockModel = new HumanPlayerSimulation(message, 1234321);
  }

  /**
   * Verifies the add human player command for mock model.
   */
  @Test
  public void testAddHumanPlayerTest() {
    String playerName = "human";
    int playerItemsLimit = 0;
    String playerRoomName = "Kitchen";
    mansion = new AddHumanPlayer(playerName, playerRoomName, playerItemsLimit);
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " humanplayer item limit0player room nameKitchen\n"
            + "uniqueIdentifier is 1234321");
    assertEquals(sb.toString(), message.toString()); 
  }

}
