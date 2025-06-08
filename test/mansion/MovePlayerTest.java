package mansion;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;

/**
 * This is MovePlayerTest class.
 * This class tests by moving the specific player to a specified location.
 */
public class MovePlayerTest {
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
  public void testMovePlayer() {
    Reader in = new StringReader("Kitchen");
    Scanner sc = new Scanner(in);
    this.world = (ActionInterface) new MovePlayer(sc, this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("movePlayer method invoked with input Kitchen");
    sb.append("UniqueCode is 1234321");
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
}
