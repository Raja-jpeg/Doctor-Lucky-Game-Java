package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.PickItem;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the PickItemTest class.
 * It tests the pick item command in the controller.
 */
public class PickItemTest {
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
   * Verify the pick item command with the mock model.
   * test pick item for mock model.
   */
  @Test
  public void testPickItemTest() {
    mansion = new PickItem("Crepe Pan");
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("playerPickItems method invoked with input Crepe PanuniqueIdentifier is 1234321");
    assertEquals(sb.toString(), message.toString()); 
  }

}
