package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.TheWorldGraphics;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the TheWorldGraphicsTest class.
 * It tests the generation of graphical representation command in the controller.
 *
 */
public class TheWorldGraphicsTest {
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
   * Verify the graphical image representation command with the mock model.
   * test if graphical mage is created.
   */
  @Test
  public void testGetGraphicalRep() {
    mansion = new TheWorldGraphics(out);
    mansion.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("createWorldGraphicalRep method invoked\n"
            + "uniqueIdentifier is 1234321");
    assertEquals(sb.toString(), message.toString());
  }

}
