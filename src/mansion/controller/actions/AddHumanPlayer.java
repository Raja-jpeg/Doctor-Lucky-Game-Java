package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the AddHumanPlayer class which implements ActionInterface.
 * It acts as adding human-controlled player command.
 *
 */
public class AddHumanPlayer implements ActionInterface {
  private final String playerName;
  private final int playerItemsLimit;
  private final String playerRoomName;

  /**
   * This is the AddHumanPlaer class constructor.
   * It is helpful in initializing the AddHumanPlayer class fields.
   * 
   * @param playerName the name of the player.
   * @param playerItemsLimit the item limit of the player.
   * @param playerRoomName the location of the player.
   */
  public AddHumanPlayer(String playerName, String playerRoomName, int playerItemsLimit) {
    if (playerName == null || playerName.trim().isEmpty() || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("The name of the player is invalid.");
    }
    if (playerRoomName == null || playerRoomName.trim().isEmpty()
        || "".equals(playerRoomName.trim())) {
      throw new IllegalArgumentException("The room name does not exists or invalid.");
    }
    if (playerItemsLimit < 0) {
      throw new IllegalArgumentException("Player items limit is invalid: Cannot be negative.");
    }
    this.playerName = playerName;
    this.playerItemsLimit = playerItemsLimit;
    this.playerRoomName = playerRoomName;
  }

  @Override
  public void execute(GameControls worldInstance) throws IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    try {
      worldInstance.addHumanPlayer(playerName, playerItemsLimit, playerRoomName);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Player input parameters are invalid.");
    }
  }

}
