package milestone;

import java.util.ArrayList;
import java.util.List;

/**
 * The World is the representation of the Doctor Lucky Mansion
 * It consists of various rooms, items, players in the world.
 */
public class TheWorld implements GameControls {

  String[] allroomNames = {
      "Armory",
      "Billiard Room",
      "Carriage House",
      "Dining Hall",
      "Drawing Room",
      "Foyer",
      "Green House",
      "Hedge Maze",
      "Kitchen",
      "Lancaster Room",
      "Library",
      "Lilac Room",
      "Master Suite",
      "Nursery",
      "Parlor",
      "Piazza",
      "Servants' Quarters",
      "Tennessee Room",
      "Trophy Room",
      "Wine Cellar",
      "Winter Garden"
  };
  private List<String> allPlayers = new ArrayList<>();
  private Players player = new Players();
  private Rooms[] room;
  private ReadTextFile readFile;
  private int totalRooms;
  private int playerIndex;
  private String roomName;
  
  /**
   * This is a constructor for TheWorld class.
   */
  public TheWorld() {

    // Initialize variables
    readFile = new ReadTextFile();
    room = new Rooms[21];
    totalRooms = 0;
  }
  
  public List<Rooms> getRoomsList() {
    return readFile.getRoomsList();
  }
  
  @Override
  public void moveTargetCharacter() {
    // Target Character moves to the first room again
    // when the current position is the last room
    int position = player.currentPosition();
    if (position != room.length - 1) {
      position++;
      player.assignPosition(position);
    } else {
      player.assignPosition(0);
    }
  }
    
  /**
   * getAdjacentRooms() method will get the neighbors of the room.
   * @param roomIndex gets the index of the room.
   * @return adjacentRooms the neighbors.
   */
  public ArrayList<Integer> getAdjacentRooms(int roomIndex) {

    validateRoomIndex(roomIndex);
    ArrayList<Integer> adjacentRooms = new ArrayList<>();

    Rooms currentRoom = room[roomIndex];
    int currentTopLeftX = currentRoom.getTopLeftX();
    int currentTopLeftY = currentRoom.getTopLeftY();
    int currentBottomRightX = currentRoom.getBottomRightX();
    int currentBottomRightY = currentRoom.getBottomRightY();

    for (int i = 0; i < totalRooms; i++) {
      if (i != roomIndex) {
        Rooms tempRoom = room[i];
        int tempTopLeftX = tempRoom.getTopLeftX();
        int tempTopLeftY = tempRoom.getTopLeftY();
        int tempBottomRightX = tempRoom.getBottomRightX();
        int tempBottomRightY = tempRoom.getBottomRightY();
        boolean isAdjacentToTop = tempBottomRightX + 1 == currentTopLeftX 
            && tempTopLeftY <= currentBottomRightY 
            && tempBottomRightY >= currentTopLeftY;

        boolean isAdjacentToRight = tempTopLeftY - 1 == currentBottomRightY 
            && tempTopLeftX <= currentBottomRightX 
            && tempBottomRightX >= currentTopLeftX;

        boolean isAdjacentToBottom = tempTopLeftX - 1 == currentBottomRightX 
            && tempTopLeftY <= currentBottomRightY
            && tempBottomRightY >= currentTopLeftY;

        boolean isAdjacentToLeft = tempBottomRightY + 1 == currentTopLeftY
            && tempTopLeftX <= currentBottomRightX 
            && tempBottomRightX >= currentTopLeftX;

        if (isAdjacentToTop || isAdjacentToRight 
            || isAdjacentToBottom || isAdjacentToLeft) {
          adjacentRooms.add(i);
        }
      }
    }

    return adjacentRooms;
  }

  private void validateRoomIndex(int roomIndex) {
    if (roomIndex < 0 || roomIndex >= totalRooms) {
      throw new IllegalArgumentException("Invalid room index: " + roomIndex);
    }
  }

  /**
   * loadWorld() method will call the file parsing method
   * and send gets the information of rooms, target character and total rooms.
   * @param filepath contains the file path of the text file.
   * @return boolean value.
   */
  public boolean loadWorld(String filepath) {
    readFile.fileParsing(filepath);
    room = readFile.getRooms();
    player = readFile.getTarget();
    totalRooms = readFile.getTotalRooms();
    return true;
  }

  /**
   * getRoomInformation() method will print out 
   * room name and items in the room and adjacent rooms.
   * @param roomIndex gives the index of the room.
   */
  public void getRoomInformation(int roomIndex) {
    System.out.println("Name of the room: " + room[roomIndex].getRoomName());
    System.out.println("Item Info:");
    List<Items> itemTemp = room[roomIndex].getItems();
    for (int i = 0; i < itemTemp.size(); i++) {
      System.out.println(itemTemp.get(i).getName());
    }
    System.out.println("Neighbor Info:");
    System.out.println(getAdjacentRooms(roomIndex));
  }

  /**
   * spaceInfo() gives the details of the room.
   * @param roomName gives the name of the room.
   */
  public void spaceInfo(String roomName) {
    int roomIndex = 0;
    for (int i = 0; i < allroomNames.length; i++) {
      if (allroomNames[i].equalsIgnoreCase(roomName)) {
        roomIndex = i;
        break;
      }
    }
    moveTargetCharacter();
    System.out.println("\n\nSpace Info requested...");
    getRoomInformation(roomIndex);
  }



  private int findRoomIndex(String roomName) {
    int i = 0;
    for (i = 0; i < 22; i++) {
      if (room[i].getRoomName().equals(roomName)) {
        break;
      }
    }
    return i;
  }

  /**
   * movePlayer() method moves the player to the other room.
   * @param roomName the name of the room.
   * @param roomIndex the index of the room.
   * @param player the player type.
   * @throws IllegalArgumentException illegal argument exception.
   */
  public void movePlayer(String roomName, int roomIndex, Players player) 
      throws IllegalArgumentException {
    
    if (roomIndex != -1) {
      player.setRoom(roomName);
      System.out.println(player.getName() + " moved to room: " + roomName);
      //          getRoomInformation(roomIndex);
    } else {
      System.out.println("Invalid room name!");
    }
  }

  /**
   * Picks an item from the room.
   * 
   * @param itemName item name.
   */
  void pickItem(int currentPlayerIndex, String itemName, int roomInd, int itemLimit) {
    if (itemLimit > 0) {
      System.out.println("player" + currentPlayerIndex + " picked up item: " + itemName);
      itemLimit--;
    } else {
      System.out.println("player" + currentPlayerIndex + " could not pick up item: " + itemName);
    }
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

    allPlayers.add(playerName);

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
    allPlayers.add(playerName);

  }

  public String getPlayers() {
    return allPlayers.toString();
  }

  /**
   * printPlayerInfo() method displays the player information.
   * @param playersList the list of the players.
   * @param playerName the name of the player.
   */
  public void printPlayerInfo(List<Players> playersList, String playerName) {
    System.out.println("\n\nPlayer Info.. results");

    for (Players player : playersList) {
      if (player.getPlayerName().equalsIgnoreCase(playerName)) {
        System.out.println("-----------------------");

        System.out.println("Player Name Matched: " + player.getName());
        System.out.println("Item Limit: " + player.getItemLimit());
        System.out.println("Room Name: " + player.getRoomName());
        System.out.println("-----------------------");
      }
    }
  }

}