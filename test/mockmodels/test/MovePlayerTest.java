package mockmodels.test;

import static org.junit.Assert.assertEquals;

import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.MovePlayer;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the MovePlayerTest class. 
 * It tests the MovePlayer command in the controller.
 *
 */
public class MovePlayerTest {
  private GameControls humanPlayerMockModel;
  private StringBuilder message;
  private ActionInterface world;

  /**
   * Initializing the class fields.
   */
  @Before
  public void setUp() {
    message = new StringBuilder();
    humanPlayerMockModel = new HumanPlayerSimulation(message, 1234321);
  }

  /**
   * Verify the move player command with the mock model.
   * test move player for mock model.
   */
  @Test
  public void testMovePlayer() {
    world = new MovePlayer(20, 19);
    world.execute(humanPlayerMockModel);
    StringBuilder sb = new StringBuilder();
    sb.append("movePlayer method invoked with input 2019uniqueIdentifier is 1234321");
    assertEquals(sb.toString(), message.toString());
  }

}
