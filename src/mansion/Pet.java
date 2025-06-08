package mansion;

/**
 * This is the Pet class. 
 * The Pet class implements the Pet interface.
 * It deals with information about the name and location of the pet.
 */
public class Pet implements PetInterface {
  private String petName;
  private String petRoomName;

  /**
   * This is the Pet class constructor. It is useful in creating Pet class objects.
   * 
   * @param petName the name of the pet character.
   * @param petRoomName the specified location of the pet character.
   */
  public Pet(String petName, String petRoomName) throws IllegalArgumentException {
    if (petName == null || petName.trim().isEmpty() || "".equals(petName.trim())) {
      throw new IllegalArgumentException("Invalid pet name");
    }
    if (petRoomName == null || petRoomName.trim().isEmpty() || "".equals(petRoomName.trim())) {
      throw new IllegalArgumentException("Invalid pet room name");
    }
    this.petName = petName;
    this.petRoomName = petRoomName;
  }

  @Override
  public String getPetName() {
    return petName;
  }

  @Override
  public String getPetRoomName() {
    return petRoomName;
  }

  @Override
  public String toString() {
    return String.format("Pet %s is in %s", this.getPetName(), this.getPetRoomName());
  }

  @Override
  public void updatePetRoomName(String petRoomName) {
    if (petRoomName == null || petRoomName.trim().isEmpty() || "".equals(petRoomName.trim())) {
      throw new IllegalArgumentException("Invalid pet room name");
    }
    this.petRoomName = petRoomName;
  }

}
