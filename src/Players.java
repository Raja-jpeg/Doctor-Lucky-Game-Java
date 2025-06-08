package milestone;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the game.
 *
 */
public class Players {
  
  int healthValue;
  String roomName;
  List<Players> players = new ArrayList<>(); // List to store all rooms
  private String playerName;
  private int playerPosition;

  /**
   * Creates a new instance of the Players class with the given player name.
   *
   * @param playerName the name of the player
   * @param healthValue the health value of the player.
   * @param roomName the name of the room.
   */
  public Players(String playerName, int healthValue, String roomName) {
    this.playerName = playerName;
    this.healthValue = 100; // Initial health value
    //    this.playerPosition = 0; // Initial player position
    this.roomName = roomName;
    players.add(this);

  }

  /**
   * The default constructor for Players class.
   */
  public Players() {
    // TODO Auto-generated constructor stub
    int a = 10;
  }

  /**
   * Updates the health value of the player.
   *
   * @param value the value to update the health by
   */
  public void updateHealth(int value) {
    // Implementation for updateHealth method
    this.healthValue = value;
  }

  /**
   * addHumanPlayer() method adds the human controlled player.
   * @param playerName the name of the player.
   * @param healthValue the health value of the player.
   * @param roomName the name of the room.
   */
  public void addHumanPlayer(String playerName, int healthValue, String roomName) {
    System.out.println("Human Player Added");
    Players humanPlayer = new Players(playerName, healthValue, roomName);
    humanPlayer.updateHealth(healthValue);
  }
  
  
  public void setRoom(String roomName) {
    this.roomName = roomName;
  }

  public String getName() {
    return playerName;
  }

  /**
   * addComputerPlayer() method adds the computer controlled player.
   * @param playerName the name of the player.
   * @param healthValue the health value of the player.
   * @param roomName the name of the room.
   */
  public void addComputerPlayer(String playerName, int healthValue, String roomName) {
    System.out.println("Computer Player Added");

    Players compPlayer = new Players(playerName, healthValue, roomName);
    compPlayer.updateHealth(healthValue);
  }

  /**
   * Returns the current health value of the player.
   *
   * @return the health value of the player
  */
  public int getHealthValue() {
    return healthValue;
  }

  public int currentPosition() {
    return playerPosition;
  }

  public void assignPosition(int pos) {
    playerPosition = pos;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getItemLimit() {
    // TODO Auto-generated method stub
    return healthValue;
  }

  public String getRoomName() {
    // TODO Auto-generated method stub
    return roomName;
  }

}