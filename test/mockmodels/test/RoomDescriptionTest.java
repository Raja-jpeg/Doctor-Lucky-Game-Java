package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.RoomDescription;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the RoomDescriptionTest class.
 * It tests the RoomDescription command in the controller.
 */
public class RoomDescriptionTest {
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
   * Verify the room description command with the mock model.
   */
  @Test
  public void testRoomDescriptionTest() {
    String in = "Kitchen";
    mansion = new RoomDescription(in, out);
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("getSpaceInformation method invoked with input Kitchen");
    assertEquals(sb.toString(), message.toString());
    assertEquals("uniqueIdentifier is 1234321\n", out.toString());
  }
}
