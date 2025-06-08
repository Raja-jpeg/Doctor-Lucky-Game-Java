package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is the TheWorldGraphicsTest class.
 * This class tests the display of graphical representation of the world.
 */
public class TheWorldGraphicsTest {
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
  public void testGetGraphicalRep() {
    this.world = (ActionInterface) new TheWorldGraphics(this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("Create world graphical representation method invoked\n");
    sb.append("UniqueCode is 1234321");
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
}
