package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is the PlayerInfo test class.
 * This class tests the getPlayerDescription method.
 */
public class PlayerInfoTest {
  private GameControls model;
  private StringBuilder message;
  private StringBuffer out;
  private ActionInterface world;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.message = new StringBuilder();
    this.model = (GameControls) new HumanPlayerSimulation(this.message, 1234321);
    this.out = new StringBuffer();
  }
  
  @Test
  public void testPlayerInfoTest() {
    String in = "sahith";
    this.world = (ActionInterface) new PlayerDescription(in, this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("getPlayerDescription method invoked with input sahith");
    Assert.assertEquals(sb.toString(), this.message.toString());
    Assert.assertEquals("UniqueCode is 1234321\n", this.out.toString());
  }
}
