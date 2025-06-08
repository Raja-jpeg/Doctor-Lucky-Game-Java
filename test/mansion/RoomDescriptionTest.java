package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is the RoomDescriptionTest class.
 * This class tests the getSpaceInformation method.
 */
public class RoomDescriptionTest {
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
  public void testSpaceInfoTest() {
    String in = "Kitchen";
    this.world = (ActionInterface) new RoomDescription(in, this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("getSpaceInformation method invoked with input Kitchen");
    Assert.assertEquals(sb.toString(), this.message.toString());
    Assert.assertEquals("UniqueCode is 1234321\n", this.out.toString());
  }
}
