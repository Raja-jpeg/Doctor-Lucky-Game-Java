package mansion;

/**
 * This is the GameControls interface which gives controls to 
 * the Kill Doctor Lucky's Mansion which contains corresponding
 * rooms, items along with the target character moving around.
 */
public interface GameControls extends WorldViewInterface {

  /**
   * Returns the information regarding the specific space in the mansion.
   * 
   * @param roomName the name of the room.
   * @return list of items in a room and its adjacent rooms.
   * @throws IllegalArgumentException if room does not exist.
   */
  String getSpaceInformation(String roomName) throws IllegalArgumentException;

  /**
   * Create the graphical representation of the mansion with provided map inputs.
   */
  void createWorldGraphicalRep();

  /**
   * Adds a human controlled player to the mansion board game.
   * 
   * @param playerName the name of the player.
   * @param playerItemsLimit the item limit of the player.
   * @param playerRoomName the name of the room that the player is in.
   */
  void addHumanPlayer(String playerName, int playerItemsLimit, String playerRoomName);

  /**
   * Adds a computer-controlled player to the mansion board game.
   */
  void addComputerPlayer();

  /**
   * Move the player to a valid specified space in the mansion board game.
   * 
   * @param x position horizontal coordinate;
   * @param y position vertical coordinate;
   */
  void movePlayer(int x, int y);

  /**
   * Picks up the specified valid item present in the room.
   * 
   * @param itemName name of the item.
   */
  void playerPickItems(String itemName);

  /**
   * The player looks around the place they are in right now.
   * They know the name of the place, who else is there, what things
   * are in the place, and also details about nearby places that they can see.
   * The player can also see into nearby places to figure out 
   * what place it is, and who and what are there.
   * 
   * @return the adjacent rooms of the specified location.
   */
  String playerLookAround();

  /**
   * Verifying if the player is computer-controlled or not.
   * 
   * @return boolean value of the player type.
   */
  boolean checkComputerPlayer();

  /**
   * The computer-controlled player places the moves based on the random functionality.
   * 
   * @return the computer-controlled player moves.
   */
  String playComputerPlayer();

  /**
   * Move the pet character to the valid specified room location in the mansion.
   * 
   * @param roomName the name of the room.
   */
  void movePet(String roomName);

  /**
   * When someone tries to harm the target, if another person or a computer sees the attack,
   * it's stopped and doesn't cause any harm. However, if the attack 
   * goes unnoticed, it always works. In that situation, the target loses a certain amount
   * of health points, which is determined by the item used for the attack.
   * 
   * @param itemName name of the item.
   * @return the end result of the attack.
   */
  String attackTarget(String itemName);

  /**
   * This checks if the player has any items. To attack another player, 
   * the attacking player selects an item if they have at least one item,
   * otherwise they poke the target character in the eye.
   * 
   * @return true if player has at least one valid item.
   */
  boolean checkIfPlayerHasItems();

  /**
   * The player pokes the target in the eye as they don't possess any items for the attack.
   * 
   * @return string with information after attacking target.
   */
  String pokeTheTargetCharacter();

  /**
   * The game concludes under these conditions: If a player manages to eliminate 
   * the target character, they secure victory. Alternatively, if the maximum 
   * number of turns is reached, the target character escapes and flees,
   * leading to no winner.
   * 
   * @return true if the game is over else false.
   */
  boolean isGameOver();

  /**
   * Returns the end result of the game.
   * If the target character is killed then the function returns the winner
   * else the no winner upon reaching the maximum number of turns.
   * 
   * @return the name of the player who killed the target character.
   */
  String getWinner();

  /**
   * Starts a new game.
   */
  void startNewGame();

  /**
   * To verify whether the player description is called by the user input.
   * 
   * @param y vertical coordinate.
   * @param x horizontal coordinate.
   * @return true if the player's location is correctly clicked.
   * @throws IllegalArgumentException if the specified location does not exists.
   */
  boolean checkPlayerInfo(int y, int x) throws IllegalArgumentException;
}
