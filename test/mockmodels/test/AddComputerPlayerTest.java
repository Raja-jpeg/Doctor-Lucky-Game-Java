package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.AddComputerPlayer;
import mockmodels.ComputerPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the AddComputerPlayerTest class.
 * It tests the AddComputerPlayer command in the controller.
 *
 */
public class AddComputerPlayerTest {
  private GameControls computerPlayerMockModel;
  private StringBuilder message;
  private ActionInterface mansion;

  /**
   * Initializing the class fields.
   */
  @Before
  public void setUp() {
    message = new StringBuilder();
    computerPlayerMockModel = new ComputerPlayerSimulation(message, 1234321);
  }

  /**
   * Verify the add computer player command for mock model.
   */
  @Test
  public void testAddComputerPlayerTest() {
    mansion = new AddComputerPlayer();
    mansion.execute(computerPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("addComputerPlayer method invoked\n"
            + "uniqueIdentifier is 1234321");
    assertEquals(sb.toString(), message.toString());
  }

}
