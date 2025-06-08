package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.ComputerPlayerSimulation;


/**
 * This is AddComputerPlayerTest class.
 * This class tests by adding the computer controlled player to the game.
 */
public class AddComputerPlayerTest {
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
    this.model = (GameControls) new ComputerPlayerSimulation(this.message, 1234321);
    this.out = new StringBuffer();
  }
  
  @Test
  public void testAddComputerPlayerTest() {
    this.world = (ActionInterface) new AddComputerPlayer(this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("addComputerPlayer method invoked\n");
    sb.append(String.format("UniqueCode is 1234321", new Object[0]));
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
}
