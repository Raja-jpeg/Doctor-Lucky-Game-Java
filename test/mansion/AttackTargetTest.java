package mansion;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.HumanPlayerSimulation;
import simulation.NoWeaponAttackSimulation;

/**
 * This is the AttackTargetTest class.
 * The class tests by attacking the target character.
 */
public class AttackTargetTest {
  private GameControls model;
  private GameControls noWeaponWorld;
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
    this.noWeaponWorld = (GameControls) new NoWeaponAttackSimulation(this.message, 1234321);
    this.out = new StringBuffer();
  }
  
  @Test
  public void testAttackTarget() {
    Reader in = new StringReader("Crepe Pan");
    Scanner sc = new Scanner(in);
    this.world = (ActionInterface) new AttackTarget(sc, this.out);
    this.world.execute(this.model);
    StringBuilder sb = new StringBuilder();
    sb.append("checkIfPlayerHasItems method invoked\nattackTarget method"
        + " invoked with input Crepe Pan");
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
  
  @Test
  public void testAttackTargetNoItem() {
    Reader in = new StringReader("Crepe Pan");
    Scanner sc = new Scanner(in);
    this.world = (ActionInterface) new AttackTarget(sc, this.out);
    this.world.execute(this.noWeaponWorld);
    StringBuilder sb = new StringBuilder();
    sb.append(
        "checkIfPlayerHasItems method invoked\nattackTargetWithoutItems method invoked\n");
    Assert.assertEquals(sb.toString(), this.message.toString());
  }
}
