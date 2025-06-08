package mansion;

import java.util.List;

/**
 * This is the Player class interface.
 * It deals with the information of player name, location, items limit and type.
 */
public interface PlayerInterface {
  /**
   * Returns the name of the player.
   * 
   * @return the player name
   */
  String getPlayerName();

  /**
   * Returns the location of the player if exists.
   * 
   * @return the location of the player.
   */
  String getPlayerRoomName();

  /**
   * Returns the player's item limit.
   * 
   * @return the limit of the player's items.
   */
  int getPlayerItemsLimit();

  /**
   * Returns the items list of the player.
   * 
   * @return list of the items for the player.
   */
  List<String> getPlayerItems();

  /**
   * Returns the control type of the player.
   * Either computer-controlled or human-controlled.
   * 
   * @return player type.
   */
  PlayerType getPlayerType();

  /**
   * Update the room name.
   * 
   * @param playerRoomName updated room name for player
   */
  void updatePlayerRoomName(String playerRoomName);

  /**
   * The item will be picked if exists in the room.
   * 
   * @param itemName name of the item.
   */
  void pickItem(String itemName);

  /**
   * The item will be removed after the attack.
   * 
   * @param itemName name of the item.
   */
  void removeItem(String itemName);

  /**
   * Returns the player's location in the horizontal axis.
   * 
   * @return horizontal axis position.
   */
  int getHorizontalAxisPosition();

  /**
   * Returns the player's location in the vertical axis.
   * 
   * @return vertical axis position.
   */
  int getVerticalAxisPosition();

  /**
   * Update the player's location on the horizontal axis.
   * 
   * @param horizontal axis position to be updated.
   */
  void updateHorizontalAxisPosition(int updatedHorizontalAxisPosition);

  /**
   * Update the player's location on the vertical axis.
   * 
   * @param vertical axis position to be updated.
   */
  void updateVerticalAxisPosition(int updatedVerticalAxisPosition);
}
