package mansion;

import java.io.IOException;

/**
 * This is the PlayerDescription class which implements ActionInterface.
 */
public class PlayerDescription implements ActionInterface {
  private final String playerName;
  private final Appendable out;
  
  /**
   * This is the PlayerDescription class constructor.
   * 
   * @param playerName the name of the player.
   * @param out the output.
   */
  public PlayerDescription(String playerName, Appendable out) {
    if (playerName == null || playerName.trim().isEmpty() || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid player name");
    }
    if (out == null) {
      throw new IllegalArgumentException("Invalid Appendable Parameter");
    }
    this.playerName = playerName;
    this.out = out;
  }
  
  @Override
  public void execute(GameControls worldObject) throws IllegalArgumentException,
      IllegalArgumentException {
    if (worldObject == null) {
      throw new IllegalArgumentException("Invalid Object");
    }
    try {
      this.out.append(String.valueOf(worldObject.getPlayerDescription(playerName)) + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Append failed", e);
    } 
  }
}
