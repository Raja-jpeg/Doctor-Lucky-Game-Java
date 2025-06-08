package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the TargetTest class.
 * This class is to test the Target character.
 */
public class TargetTest {
  private Target target;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.target = new Target(50, "Doctor Lucky", "Armory");
  }
  
  /**
   * This is the Target class constructor.
   * 
   * @param targetHealth the health value of the target character.
   * @param targetName the name of the target character.
   * @param roomName the name of the room.
   * @return Target object.
   */
  protected Target target(int targetHealth, String targetName, String roomName) {
    return new Target(targetHealth, targetName, roomName);
  }
  
  @Test
  public void testTargetHealth() {
    Assert.assertEquals(this.target.getTargetHealth(), 50L);
  }
  
  @Test
  public void testTargetName() {
    Assert.assertEquals(this.target.getTargetName(), "Doctor Lucky");
  }
  
  @Test
  public void testTargetToString() {
    Assert.assertEquals(this.target.toString(), "Target Doctor Lucky has health 50");
  }
}
