package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.LookAround;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;


/**
 * This is the LookAroundTest class.
 * It tests the LookAround command in the controller.
 *
 */
public class LookAroundTest {

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
   * Verify look around command for the mock model.
   */
  @Test
  public void testLookAroundTestt() {
    mansion = new LookAround();
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("playerLookAround method invoked");
    assertEquals(sb.toString(), message.toString());
  }

}
