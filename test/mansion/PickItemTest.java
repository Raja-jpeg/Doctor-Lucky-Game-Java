package mansion;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is the PickItemTest class.
 * This class tests the picking item functionality.
 */
public class PickItemTest {
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
  public void testPickItemTest() {
    Reader in = new StringReader("Crepe Pan");
    Scanner sc = new Scanner(in);
    this.world = (ActionInterface) new PickItem(sc, this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("playerPickItems method invoked with input Crepe Pan");
    sb.append("UniqueCode is 1234321");
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
}
