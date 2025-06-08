package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.MovePetCharacter;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the MovePetCharacterTest class.
 * It tests the MovePetCharacter command in the controller.
 *
 */
public class MovePetTest {
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
   * Verify the move pet character command for the mock model.
   */
  @Test
  public void testMovePet() {
    mansion = new MovePetCharacter("Kitchen");
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("movePet method invoked with input KitchenuniqueIdentifier is 1234321");
    assertEquals(sb.toString(), message.toString());
  }

}