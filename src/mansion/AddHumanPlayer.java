package mansion;

import java.io.IOException;

/**
 * This is the AddHumanPlayer class which implements the ActionInterface.
 */
public class AddHumanPlayer implements ActionInterface {
  private final String playerName;
  private final int playerItemsLimit;
  private final String playerRoomName;
  private final Appendable out;
  
  /**
   * This is the AddHumanPlayer class constructor.
   * 
   * @param playerName the name of the player.
   * @param playerItemsLimit the items list of the player.
   * @param playerRoomName the lcoation of the player.
   * @param out the output.
   */
  public AddHumanPlayer(String playerName, int playerItemsLimit,
          String playerRoomName, Appendable out) {
    if (playerName == null || playerName.trim().isEmpty() || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid player name"); 
    }
    if (playerRoomName == null || playerRoomName.trim().isEmpty() 
         || "".equals(playerRoomName.trim())) {
      throw new IllegalArgumentException("Invalid room name");
    }
    if (playerItemsLimit < 0) {
      throw new IllegalArgumentException("Invalid players item limit");
    }
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.playerName = playerName;
    this.playerItemsLimit = playerItemsLimit;
    this.playerRoomName = playerRoomName;
    this.out = out;
  }
  
  @Override
  public void execute(GameControls worldObject) throws IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    }
    worldObject.addHumanPlayer(this.playerName, this.playerItemsLimit, this.playerRoomName);
    try {
      this.out.append("Human player added to the game\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    } 
  }
}
