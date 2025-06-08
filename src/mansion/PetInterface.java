package mansion;

/**
 * This is a Pet interface.
 * This interface deals with the information of name and location of the pet.
 */
public interface PetInterface {

  /**
   * Returns the name of the pet.
   * 
   * @return name of the pet.
   */
  String getPetName();

  /**
   * Returns the location of the pet.
   * 
   * @return room name of the pet.
   */
  String getPetRoomName();

  /**
   * Updates the pet's location to a new one.
   * 
   * @param petRoomName the room name of the pet.
   */
  void updatePetRoomName(String petRoomName);
}
