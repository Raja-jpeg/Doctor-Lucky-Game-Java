package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is LookAroundTest class.
 * This class tests whether the rooms displayed are neighbors or not.
 */
public class LookAroundTest {
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
  public void testLookAroundTestt() {
    this.world = (ActionInterface) new LookAround(this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("playerLookAround method invoked");
    Assert.assertEquals(sb.toString(), this.message.toString());
    Assert.assertEquals("UniqueCode is 1234321\n", this.out.toString());
  }
}
