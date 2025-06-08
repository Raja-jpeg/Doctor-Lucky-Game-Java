package mansion.controller;

/**
 * This is the PlayerCommandInterface.
 * It is used by the view component to connect to the controller.
 */
public interface PlayerCommandInterface {
  /**
   * Adds human-controlled player to the game.
   * 
   * @param playerName the name of the player.
   * @param playerRoomName  the initial location of the player. 
   * @param playerItemsLimit the item limit of the player.
   */
  void addHumanPlayer(String playerName, String playerRoomName, String playerItemsLimit);

  /**
   * Adds computer-controlled player to the game.
   */
  void addComputerPlayer();

  /**
   * Starts the game.
   */
  void startGame();

  /**
   * Adds players to the game either human-controlled or computer-controlled.
   */
  void addPlayers();

  /**
   * Enables to look around the neighboring space of the specified player.
   */
  void lookAround();

  /**
   * Enables to pick up a valid item present in the same location as the player.
   * 
   * @param itemName the name of the item.
   */
  void pickItem(String itemName);

  /**
   * Enables to attack the target character with an item.
   * 
   * @param itemName name of the item as input.
   */
  void attackTarget(String itemName);

  /**
   * Moves the pet character to the specified location in the mansion.
   * 
   * @param roomName the name of the location clicked.
   */
  void movePet(String roomName);

  /**
   * Returns the list of the items in the player's hands.
   */
  void playerItems();

  /**
   * Quits the game.
   */
  void quitGame();

  /**
   * Moves the player to the valid adjacent room.
   * 
   * @param i horizontal axis coordinate.
   * @param j vertical axis coordinate.
   */
  void movePlayer(int i, int j);

  /**
   * Start a new game. It will restart the game from the beginning.
   * 
   * @throws IllegalArgumentException if the model or view instance is null.
   */
  void startNewGame() throws IllegalArgumentException;

  /**
   * Start a new game with custom input file location.
   * 
   * @param map newly added file path.
   */
  void startNewMapGame(String map);
}