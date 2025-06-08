package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is PetTest class.
 * This class tests the Pet character in the game.
 */
public class PetTest {
  private PetInterface pet;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.pet = (PetInterface) new Pet("Fortune the Cat", "Armory");
  }
  
  /**
   * PetInterface constructor.
   * 
   * @param petname name of the pet.
   * @param petRoomName the room of the pet.
   * @return petInterface object.
   */
  protected PetInterface pets(String petname, String petRoomName) {
    return (PetInterface) new Pet(petname, petRoomName);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPetName() {
    pets(null, "Armory");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPetRoomName() {
    pets("Human", null);
  }
  
  @Test
  public void testPetName() {
    Assert.assertEquals(this.pet.getPetName(), "Fortune the Cat");
  }
  
  @Test
  public void testPetRoomName() {
    Assert.assertEquals(this.pet.getPetRoomName(), "Armory");
  }
  
  @Test
  public void testPettoString() {
    Assert.assertEquals(this.pet.toString(), "Pet Fortune the Cat is in Armory");
  }
}
