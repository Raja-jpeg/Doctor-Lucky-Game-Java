package mansion;

import java.awt.image.BufferedImage;

/**
 * This is the WorldViewInterface which has the necessary view elements information.
 */
public interface WorldViewInterface {

  /**
   * Returns the information regarding the specified player name.
   * 
   * @return string containing information related to a player.
   */
  String getPlayerDescription();

  /**
   * The user is given some hints along the moves placed to get to know about the
   * whereabouts of the mansion which includes target character, pet information
   * of their location. It also checks if the pet character is in the same room 
   * before the player places the move.
   * 
   * @return information about locations of players, pet, target character respectively.
   */
  String getHintsAboutWorld();

  /**
   * Returns the total room list.
   * 
   * @return rooms list.
   */
  String[] getRooms();

  /**
   * Returns the turn of the specified player name.
   * 
   * @return the turn of the specified player.
   */
  String getPlayerNameTurn();

  /**
   * Returns the list of all items present in that specified room.
   * 
   * @return the list of items present in the room.
   */
  String[] getRoomItems();

  /**
   * Returns the items list possessed by the specified player.
   * 
   * @return the items which are in the possession of the player.
   */
  String[] getPlayerItems();

  /**
   * Returns all the players involved in the mansion board game.
   * 
   * @return all players.
   */
  String getPlayers();

  /**
   * Returns the updated graphical representation of the board game.
   * The image contains information about the players, pet and target.
   * 
   * @return the graphical representation of the status of the game.
   */
  BufferedImage getMansionBoardStatusImage();
}
