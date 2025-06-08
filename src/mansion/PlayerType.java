package mansion;

/**
 * This is enumeration of the player control.
 * Human-controlled player or Computer-controller player.
 */
public enum PlayerType {
  /**
   * Computer enumeration.
   */
  COMPUTER("COMPUTER"),
  /**
   * Human enumeration.
   */
  HUMAN("HUMAN");

  private final String playerType;

  private PlayerType(String playerType) {
    this.playerType = playerType;
  }

  @Override
  public String toString() {
    return playerType;
  }
}
