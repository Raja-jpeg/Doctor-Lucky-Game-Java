package mockmodels.test;

import static org.junit.Assert.assertEquals;

import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;
import mansion.GameControls;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.AttackTargetCharacter;
import mockmodels.AttackWithoutWeaponSimulation;
import mockmodels.HumanPlayerSimulation;
import org.junit.Before;
import org.junit.Test;


/**
 * This is the AttackTargetTest class.
 * It tests the AttackTargetCharacter command in the controller.
 *
 */
public class AttackTargetTest {
  private GameControls mockModelWithWeapon;
  private GameControls mockModelWithoutWeapon;
  private StringBuilder message;
  private StringBuffer out;
  private ActionInterface mansion;

  /**
   * sets up message,model and out fields.
   */
  @Before
  public void setUp() {
    message = new StringBuilder();
    mockModelWithWeapon = new HumanPlayerSimulation(message, 1234321);
    mockModelWithoutWeapon = new AttackWithoutWeaponSimulation(message, 1234321);
    out = new StringBuffer();
  }

  /**
   * test attack target with item for mock model.
   */
  @Test
  public void testAttackTarget() {
    Reader in = new StringReader("Crepe Pan");
    Scanner sc = new Scanner(in);
    mansion = new AttackTargetCharacter(sc, out);
    mansion.execute(mockModelWithWeapon);
    StringBuilder sb = new StringBuilder();
    sb.append("checkIfPlayerHasItems method invoked\n"
        + "attackTarget method invoked with input Crepe Pan");
    assertEquals(sb.toString(), message.toString()); 
  }

  /**
   * test attack target without item for mock model.
   */
  @Test
  public void testAttackTargetWithoutItem() {
    Reader in = new StringReader("Crepe Pan");
    Scanner sc = new Scanner(in);
    mansion = new AttackTargetCharacter(sc, out);
    mansion.execute(mockModelWithoutWeapon);
    StringBuilder sb = new StringBuilder();
    sb.append("checkIfPlayerHasItems method invoked\n"
            + "attackTarget method invoked with input Crepe Pan");
    assertEquals(sb.toString(), message.toString()); 
  }

}