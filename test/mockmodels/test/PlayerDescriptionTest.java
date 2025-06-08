package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.PlayerDescription;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the PlayerDescriptionTest class.
 * It tests the PlayerDescription command in the controller.
 *
 */
public class PlayerDescriptionTest {
  private GameControls humanPlayerMockModel;
  private StringBuilder message;
  private StringBuffer out;
  private ActionInterface mansion;

  /**
   * Initializing the class fields.
   */
  @Before
  public void setUp() {
    message = new StringBuilder();
    humanPlayerMockModel = new HumanPlayerSimulation(message, 1234321);
    out = new StringBuffer();
  }

  /**
   * Verifying the player description command with the mock model.
   */
  @Test
  public void testPlayerDescriptionTest() {
    mansion = new PlayerDescription();
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("getPlayerDescription method invoked\n");
    assertEquals(sb.toString(), message.toString());
  }

}
