package mansion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is PlayerTest class.
 * This class tests about the player.
 */
public class PlayerTest {
  private PlayerInterface player;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.player = (PlayerInterface) new Player("sahith", PlayerType.HUMAN, 2, "Kitchen");
  }
  
  /**
   * PlayerInterface constructor.
   * 
   * @param playerName the name of the player.
   * @param playerType the type of the player.
   * @param itemsLimit the limit of the list of items.
   * @param roomName the name of the room.
   * @return playerInterface object.
   */
  protected PlayerInterface player(String playerName,
      PlayerType playerType, int itemsLimit, String roomName) {
    return (PlayerInterface) new Player(playerName, playerType, itemsLimit, roomName);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidRoomName() {
    player("Human", PlayerType.HUMAN, 3, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerItemLimit() {
    player("Human", PlayerType.HUMAN, -3, null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerName() {
    player(null, PlayerType.HUMAN, -3, "Kitchen");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerType() {
    player("Human", null, -3, "Kitchen");
  }
  
  @Test
  public void testPlayerName() {
    Assert.assertEquals(this.player.getPlayerName(), "Human");
  }
  
  @Test
  public void testPlayerType() {
    Assert.assertEquals(this.player.getPlayerType(), PlayerType.HUMAN);
  }
  
  @Test
  public void testPlayerItemLimit() {
    Assert.assertEquals(this.player.getPlayerItemsLimit(), 2L);
  }
  
  @Test
  public void testPlayerRoomName() {
    Assert.assertEquals(this.player.getPlayerRoomName(), "Kitchen");
  }
}
