package mansion;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 * This is the TheWorld class. It is responsible for creating the mansion's 
 * graphical representation consisting of the rooms, items within it. Moreover, 
 * it extracts information such as adjacent room information, target character
 * movements.
 */
public class TheWorld implements GameControls {
  private final Readable file;
  private int numberOfRooms;
  private int numberOfItems;
  private int numberOfRows;
  private int numberOfColumns;
  private int indexOfRoom;
  private String worldName;
  private BufferedImage img;
  private Graphics graphic;
  private Graphics graphicNew;
  private RoomInterface[] room;
  private ItemInterface[] item;
  private TargetInterface target;
  private PetInterface pet;
  private List<PlayerInterface> player;
  private int playerIndex;
  private Scanner scanner;
  private List<String> allRooms;
  private List<String> allItems;
  private List<String> allPlayers;
  private int numberOfComputerPlayers;
  private RandomInterface randomInteger;
  private int numberOfTurns;
  private int numberOfTurnsFixed;
  private List<Integer>[] neighborNode;
  private List<Integer> traversal;
  private int source;
  private int petindex;

  /**
   * This is the TheWorld class constructor.
   * It is helpful in initializing the TheWorld class fields.
   * 
   * @param file      text file with world details
   * @param randomImpl        random interface value
   * @param numberOfTurns total number of turns
   */
  public TheWorld(Readable file, RandomImpl randomImpl, int numberOfTurns)
      throws IllegalArgumentException {
    if (file == null || randomImpl == null || numberOfTurns < 0) {
      throw new IllegalArgumentException("File is invalid");
    }
    this.file = file;
    this.numberOfTurnsFixed = numberOfTurns;
    this.randomInteger = randomImpl;
    initializeFields(numberOfTurnsFixed);
  }

  private void initializeFields(int numberOfTurnsFixed) {
    this.indexOfRoom = 0;
    this.numberOfRooms = 0;
    this.numberOfItems = 0;
    this.numberOfRows = 0;
    this.numberOfColumns = 0;
    this.indexOfRoom = 0;
    this.playerIndex = 0;
    this.numberOfComputerPlayers = 0;
    allRooms = new ArrayList<>();
    allItems = new ArrayList<>();
    allPlayers = new ArrayList<>();
    player = new ArrayList<PlayerInterface>();
    scanner = new Scanner(file);
    worldDescription();
    this.numberOfTurns = numberOfTurnsFixed;
    this.numberOfTurnsFixed = numberOfTurnsFixed;
    this.traversal = new ArrayList<>();
    this.source = 0;
    movePetDfsTraversal();
    this.petindex = 0;
    scanner.close();
  }

  @Override
  public void startNewGame() {
    initializeFields(numberOfTurnsFixed);
  }

  private int getPlayerTurn() {
    if (playerIndex >= player.size() || playerIndex < 0) {
      playerIndex = 0;
      return playerIndex;
    }
    return playerIndex;
  }

  /**
   * Initializes target object and creates world map.
   * 
   * @throws IllegalArgumentException if world parameters are invalid.
   */
  private void worldDescription() throws IllegalArgumentException {
    try {
      numberOfRows = scanner.nextInt();
      numberOfColumns = scanner.nextInt();
      if (numberOfRows < 0 || numberOfColumns < 0 || (numberOfRows == 0 && numberOfColumns == 0)) {
        throw new IllegalArgumentException("Invalid world parameters");
      }
      worldName = scanner.nextLine();
      int targetHealth = scanner.nextInt();
      String targetName = scanner.nextLine().trim();
      String petName = scanner.nextLine().trim();
      String roomName = "Armory";
      target = new Target(targetHealth, targetName, roomName);
      pet = new Pet(petName, roomName);
      createGraphicalWorld(numberOfRows, numberOfColumns);
      addRoomInWorld();
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Number format exception raised");
    }
  }

  private void createGraphicalWorld(int numberOfRows, int numberOfColumns) {
    img = new BufferedImage(numberOfRows * 50, numberOfColumns * 50, BufferedImage.TYPE_INT_RGB);
  }

  /**
   * adds the room in world map.
   * 
   * @throws IllegalArgumentException if having illegal room parameters.
   */
  private void addRoomInWorld() throws IllegalArgumentException {
    try {
      numberOfRooms = scanner.nextInt();
      room = new Room[numberOfRooms];
      neighborNode = new LinkedList[numberOfRooms];
      for (int i = 0; i < numberOfRooms; ++i) {
        neighborNode[i] = new LinkedList();
      }
      int i = 0;
      while (i < numberOfRooms) {
        int upperLeftRow = scanner.nextInt();
        int upperLeftColumn = scanner.nextInt();
        int lowerRightRow = scanner.nextInt();
        int lowerRightColumn = scanner.nextInt();
        String roomName = scanner.nextLine().trim();
        if (upperLeftRow > numberOfRows || lowerRightRow > numberOfRows 
                || upperLeftColumn > numberOfColumns
            || lowerRightColumn > numberOfColumns) {
          throw new IllegalArgumentException("Invalid Room parameters");
        }
        room[i] = new Room(roomName, upperLeftRow, upperLeftColumn, lowerRightRow,
            lowerRightColumn);
        allRooms.add(roomName);
        createGraphicalRoom(roomName, upperLeftRow, upperLeftColumn, lowerRightRow,
            lowerRightColumn);
        i++;
      }
      addItems();
      scanner.close();
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Number format exception raised");
    }
  }

  private void createGraphicalRoom(String roomName, int upperLeftRow, int upperLeftColumn,
      int lowerRightRow, int lowerRightColumn) throws IllegalArgumentException {
      
    if (roomName == null || roomName.trim().isEmpty()) {
      throw new IllegalArgumentException("Invalid Room Name");
    }
  
    graphic = img.getGraphics();
    graphic.setColor(Color.CYAN);
    graphic.fillRect(upperLeftColumn * 20, upperLeftRow * 20,
        (lowerRightColumn - upperLeftColumn + 1) * 20, (lowerRightRow - upperLeftRow + 1) * 20);
    graphic.setColor(Color.BLACK);
    graphic.drawRect(upperLeftColumn * 20, upperLeftRow * 20,
        (lowerRightColumn - upperLeftColumn + 1) * 20, (lowerRightRow - upperLeftRow + 1) * 20);
    graphic.drawString(roomName, (upperLeftColumn * 20) + 10, (upperLeftRow * 20) + 20);
    graphic.setFont(new Font("Montserrat", Font.BOLD, 10));
    createWorldGraphicalRep();
  }


  /**
   * Generates an image of world map.
   */
  @Override
  public void createWorldGraphicalRep() throws IllegalArgumentException {

    try {
      ImageIO.write(img, "png", new File("res/image.png"));

    } catch (IOException e) {
      throw new IllegalArgumentException("Cannot Print Image", e);
    }
  }

  /**
   * add items in the room.
   */
  private void addItems() {
    try {
      numberOfItems = scanner.nextInt();
      item = new Item[numberOfItems];
      int i = 0;
      while (i < numberOfItems) {
        int roomIndex = scanner.nextInt();
        int damagePower = scanner.nextInt();
        String itemName = scanner.nextLine().trim();
        item[i] = new Item(roomIndex, damagePower, itemName);
        allItems.add(itemName);
        room[roomIndex].addItem(item[i].getItemName());
        i++;
      }
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Number format exception raised");
    }
  }

  /**
   * Finds the index of the room.
   * 
   * @param roomName room name
   * @return index of the room
   * @throws IllegalArgumentException if room name is invalid
   */
  private int findRoomIndex(String roomName) {
    int i = 0;
    for (i = 0; i < numberOfRooms; i++) {
      if (room[i].getRoomName().equals(roomName)) {
        break;
      }
    }
    return i;
  }

  private int findPlayerIndex(String playerName) {
    if (playerName == null || playerName.trim().isEmpty() || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid player name");
    }
    int i = 0;
    for (i = 0; i < player.size(); i++) {
      if (player.get(i).getPlayerName().equals(playerName)) {
        break;
      }
    }
    return i;
  }

  @Override
  public BufferedImage getMansionBoardStatusImage() {
    Raster raster = img.getRaster();
    int minX = Integer.MAX_VALUE;
    int minY = 0;
    int maxY = 0;
    int maxX = 0;
    boolean isBlank;
    boolean minyIsDefined = false;
   
    for (int y = 0; y < img.getHeight(); y++) {
      isBlank = true;
      for (int x = 0; x < img.getWidth(); x++) {
        if (raster.getPixel(x, y, (int[]) null)[2] != 0) {
          isBlank = false;
          if (x < minX) {
            minX = x;
          }
          if (x > maxX) {
            maxX = x;
          }
        }
      }
      if (!isBlank) {
        if (!minyIsDefined) {
          minY = y;
          minyIsDefined = true;
        } else {
          if (y > maxY) {
            maxY = y;
          }
        }
      }
    }

    BufferedImage imgNew = null;
    try {
      imgNew = ImageIO.read(ClassLoader.getSystemResource("image.png"));
    } catch (IOException e) {
      System.out.println("exception raised here");
      e.printStackTrace();
    }
    graphicNew = imgNew.getGraphics();
    int x1 = 0;
    int x2 = 0;
    int y1 = 0;
    int y2 = 0;
    for (RoomInterface r : room) {
      if (r.getRoomName().equals(target.getTargetRoomName())) {
        x1 = r.getUpperLeftRow();
        y1 = r.getUpperLeftColumn();
        x2 = r.getLowerRightRow();
        y2 = r.getLowerRightColumn();
      }
    }
    int markerSize = 15; // Size of the X marker
    int lineWidth = 5;   // Thickness of the lines

    graphicNew.setColor(Color.RED);

    // Draw multiple lines to create the appearance of a thicker line
    for (int i = 0; i < lineWidth; i++) {
      graphicNew.drawLine((y1 * 20) + 60, (x1 * 20) + 20 + i,
            (y1 * 20) + 60 + markerSize, (x1 * 20) + 20 + markerSize + i);
      graphicNew.drawLine((y1 * 20) + 60 + markerSize,
            (x1 * 20) + 20 + i, (y1 * 20) + 60, (x1 * 20) + 20 + markerSize + i);
    }
    for (int k = 0; k < player.size(); k++) {
      for (RoomInterface r : room) {
        if (player.get(k).getPlayerRoomName().equals(r.getRoomName())) {
          x1 = r.getUpperLeftRow();
          y1 = r.getUpperLeftColumn();
          x2 = r.getLowerRightRow();
          y2 = r.getLowerRightColumn();
          break;
        }
      }

      for (int i = 0; i < player.size(); i++) {
        int ovalX;
        int ovalY;
        for (RoomInterface r : room) {
          if (player.get(k).getPlayerRoomName().equals(r.getRoomName())) {
            x1 = r.getUpperLeftRow();
            y1 = r.getUpperLeftColumn();
            x2 = r.getLowerRightRow();
            y2 = r.getLowerRightColumn();
            break;
          }
        }

        switch (k) {
          case 0:
            graphicNew.setColor(new Color(15, 189, 10));
            ovalX = (y1 * 20) + 15;
            ovalY = (x1 * 20) + 5;
            break;
          case 1:
            graphicNew.setColor(new Color(213, 215, 100));
            ovalX = (y2 * 20) - 3;
            ovalY = (x2 * 20) - 3;
            break;
          case 2:
            graphicNew.setColor(new Color(157, 99, 71));
            ovalX = (y1 * 20) + 5;
            ovalY = (x2 * 20) - 3;
            break;
          case 3:
            graphicNew.setColor(new Color(244, 56, 178));
            ovalX = (y2 * 20) - 15;
            ovalY = (x1 * 20) + 5;
            break;
          case 4:
            graphicNew.setColor(new Color(110, 132, 45));
            ovalX = (y1 * 20) + 25;
            ovalY = (x1 * 20) + 20;
            break;
          case 5:
            graphicNew.setColor(new Color(0, 204, 139));
            ovalX = (y1 * 20) + 40;
            ovalY = (x1 * 20) + 20;
            break;
          case 6:
            graphicNew.setColor(new Color(138, 130, 238));
            ovalX = (y1 * 20) + 15;
            ovalY = (x2 * 20) - 3;
            break;
          case 7:
            graphicNew.setColor(new Color(128, 128, 178));
            ovalX = (y2 * 20) - 30;
            ovalY = (x1 * 20) + 5;
            break;
          case 8:
            graphicNew.setColor(new Color(179, 189, 199));
            ovalX = (y2 * 20) - 25;
            ovalY = (x2 * 20) - 3;
            break;
          case 9:
            graphicNew.setColor(new Color(129, 139, 186));
            ovalX = (y2 * 20) - 45;
            ovalY = (x1 * 20) + 20;
            break;
          default:
            ovalX = ovalY = 0;
        }
    
        player.get(i).updateHorizontalAxisPosition(x1 + ovalX - ovalX);
        player.get(i).updateVerticalAxisPosition(y1 + ovalY - ovalY);
        graphicNew.fillOval(ovalX, ovalY, 10, 10);
      }
    }
    return imgNew.getSubimage(minX, minY, maxX - minX + 1, maxY - minY + 1);
  }

  /**
   * Finds the neighbors of the room.
   * 
   * @param i index of the room.
   * @return list of neighbors of the room.
   */
  private List<String> findRoomNeighbours(int i) {
    List<String> neighbourInfo = new ArrayList<>();
    String currentRoomName = room[i].getRoomName();
    int x1 = room[i].getUpperLeftRow();
    int y1 = room[i].getUpperLeftColumn();
    int x2 = room[i].getLowerRightRow();
    int y2 = room[i].getLowerRightColumn();
    for (int j = 0; j < numberOfRooms; j++) {
      if ((room[j].getRoomName() != currentRoomName)
          && ((room[j].getUpperLeftRow() == x2 + 1 || room[j].getLowerRightRow() == x1 - 1)
              && ((room[j].getUpperLeftColumn() >= y1 && room[j].getUpperLeftColumn() <= y2)
                  || (room[j].getLowerRightColumn() >= y1 && room[j].getLowerRightColumn() <= y2)
                  || (room[j].getUpperLeftColumn() <= y1 && room[j].getLowerRightColumn() >= y2)))
          || ((room[j].getLowerRightColumn() == y1 - 1 || room[j].getUpperLeftColumn() == y2 + 1)
              && ((room[j].getUpperLeftRow() >= x1 && room[j].getUpperLeftRow() <= x2)
                  || (room[j].getLowerRightRow() >= x1 && room[j].getLowerRightRow() <= x2)
                  || (room[j].getUpperLeftRow() <= x1 && room[j].getLowerRightRow() >= x2)))) {
        neighbourInfo.add(room[j].getRoomName());
      }
    }
    return neighbourInfo;
  }

  /**
   * Finds the neighbors of the room.
   * 
   * @param roomName room name.
   * @return list of neighbors of the room.
   */
  private List<String> getNeighbourSpace(String roomName) throws IllegalArgumentException {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("Invalid Room Name");
    }
    List<String> neighbourInfo = new ArrayList<>();
    int i = findRoomIndex(roomName);
    neighbourInfo = findRoomNeighbours(i);
    List<String> neighbourInfoCopy = new ArrayList<>(neighbourInfo);
    return neighbourInfoCopy;
  }

  private List<String> getPlayerInRoom(String roomName) {
    List<String> players = new ArrayList<>();
    for (int i = 0; i < player.size(); i++) {
      if (player.get(i).getPlayerRoomName().equals(roomName)) {
        players.add(player.get(i).getPlayerName());
      }
    }
    return players;
  }

  /**
   * Finds the space information.
   * 
   * @param roomName room name.
   * @return string space information.
   */
  @Override
  public String getSpaceInformation(String roomName) throws IllegalArgumentException {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("Invalid Room Name");
    }
    if (!allRooms.contains(roomName)) {
      throw new IllegalArgumentException("Entered room is not present in the World");
    }
    int i = findRoomIndex(roomName);
    return String.format(
        "Room %s in %s has items : %s,\nits neighbours are : %s,\nit has players %s",
        room[i].getRoomName(), worldName, room[i].getRoomItems(), getNeighbourSpace(roomName),
        getPlayerInRoom(roomName));
  }

  @Override
  public boolean checkPlayerInfo(int y, int x) throws IllegalArgumentException {
    int turn = getPlayerTurn();
    if (player.get(turn).getHorizontalAxisPosition() == x 
            && player.get(turn).getVerticalAxisPosition() == y) {
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * Player looks around his neighbors.
   * 
   * @return neighbors.
   */
  @Override
  public String playerLookAround() throws IllegalArgumentException {
    if (allPlayers.size() == 0) {
      throw new IllegalArgumentException("No Player added");
    }
    List<String> currentRoomPlayers = new ArrayList<>();
    int turn = getPlayerTurn();
    String playerRoom = player.get(turn).getPlayerRoomName();
    int roomIndex = findRoomIndex(playerRoom);
    List<String> playersInCurrentSpace = getPlayerInRoom(playerRoom);
    for (int i = 0; i < playersInCurrentSpace.size(); i++) {
      if (!playersInCurrentSpace.get(i).equals(player.get(turn).getPlayerName())) {
        currentRoomPlayers.add(playersInCurrentSpace.get(i));
      }
    }
    playerIndex++;
    this.numberOfTurns--;
    String output = String.format(
        "Player %s is in room %s,\nRoom has items %s\nand other players in room %s.\n"
            + "Player's neighbor space information %s",
        player.get(turn).getPlayerName(), room[roomIndex].getRoomName(),
        room[roomIndex].getRoomItems(), currentRoomPlayers, 
        getSpaceInfoLookAround(playerRoom) + "\n");
    moveTarget();
    movePetDfs();
    return output;
  }

  private List<String> getSpaceInfoLookAround(String roomName) throws IllegalArgumentException {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("Invalid Room Name");
    }
    if (!allRooms.contains(roomName)) {
      throw new IllegalArgumentException("Entered room is not present in the World");
    }
    List<String> neighborSpaceInfo = new ArrayList<>();
    List<String> neighborSpaces = getNeighbourSpace(roomName);
    for (int j = 0; j < neighborSpaces.size(); j++) {
      int i = findRoomIndex(neighborSpaces.get(j));
      if (!neighborSpaces.get(j).equals(pet.getPetRoomName())) {
        neighborSpaceInfo.add(String.format("Room %s has items %s and it has players %s",
            room[i].getRoomName(), room[i].getRoomItems(), 
            getPlayerInRoom(room[i].getRoomName()) + "\n"));
      }
    }
    return neighborSpaceInfo;
  }

  private void moveTarget() {
    if (indexOfRoom == numberOfRooms - 1) {
      indexOfRoom = 0;
    } else {
      indexOfRoom = indexOfRoom + 1;
    }
    target.updateTargetRoomName(room[indexOfRoom].getRoomName());
  }

  @Override
  public void movePet(String roomName) throws IllegalArgumentException {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("Invalid Room Name");
    }
    if (!allRooms.contains(roomName)) {
      throw new IllegalArgumentException("Entered room is not present in the World");
    }
    getPlayerTurn();
    playerIndex++;
    this.numberOfTurns--;
    moveTarget();
    source = findRoomIndex(roomName);
    traversal = new ArrayList<>();
    petindex = 0;
    movePetDfsTraversal();
    pet.updatePetRoomName(roomName);
  }

  @Override
  public boolean checkIfPlayerHasItems() {
    int turn = getPlayerTurn();
    return player.get(turn).getPlayerItems().size() != 0;
  }

  @Override
  public String pokeTheTargetCharacter() throws IllegalArgumentException, IllegalStateException {
    String attackInfo = "";
    int turn = getPlayerTurn();
    if (!target.getTargetRoomName().equals(player.get(turn).getPlayerRoomName())) {
      playerIndex++;
      this.numberOfTurns--;
      moveTarget();
      movePetDfs();
      throw new IllegalArgumentException(
          "Target attack unsuccessful, as target is not in player's room");
    } else {
      if (getPlayerVisibility().size() == 0) {
        target.updateTargetHealth(target.getTargetHealth() - 1);
        attackInfo = String.format("Target attack is successful by poking him in the"
            + " eye and Target health decreased by 1");
      } else { 
        playerIndex++;
        this.numberOfTurns--;
        moveTarget();
        movePetDfs();
        throw new IllegalStateException(
            "Target attack unsuccessful, as player can be seen by other players");
      }
    }
    playerIndex++;
    this.numberOfTurns--;
    moveTarget();
    movePetDfs();
    return attackInfo;
  }

  @Override
  public String attackTarget(String itemName)
      throws IllegalArgumentException, IllegalStateException {
    String attackInfo = "";
    if (itemName == null || itemName.trim().isEmpty() || "".equals(itemName.trim())) {
      throw new IllegalArgumentException("Invalid Item Name");
    }
    if (!allItems.contains(itemName)) {
      throw new IllegalArgumentException("Entered item is not present in the World");
    }
    int turn = getPlayerTurn();
    if (!player.get(turn).getPlayerItems().contains(itemName)) {
      throw new IllegalArgumentException("Entered item is not present with player");
    }
    int i = getItemIndex(itemName);
    if (!target.getTargetRoomName().equals(player.get(turn).getPlayerRoomName())) {
      player.get(turn).removeItem(itemName);
      playerIndex++;
      this.numberOfTurns--;
      moveTarget();
      movePetDfs();
      throw new IllegalArgumentException(
          "Target attack unsuccessful, as target is not in player's room");
    } else {
      if (getPlayerVisibility().size() == 0) {
        target.updateTargetHealth(target.getTargetHealth() - item[i].getItemDamageValue());
        player.get(turn).removeItem(itemName);
        attackInfo = String.format("Target attack is successful and Target health decreased by %d",
            item[i].getItemDamageValue());
      } else {
        player.get(turn).removeItem(itemName);
        playerIndex++;
        this.numberOfTurns--;
        moveTarget();
        movePetDfs();
        throw new IllegalStateException(
            "Target attack unsuccessful, as player can be seen by other players");
      }
    }
    playerIndex++;
    this.numberOfTurns--;
    moveTarget();
    movePetDfs();
    return attackInfo;
  }

  @Override
  public String getHintsAboutWorld() {
    int turn = getPlayerTurn();
    List<String> playerItems = player.get(turn).getPlayerItems();
    List<String> itemsAndDamage = new ArrayList<>();
    String playerRoomName = player.get(turn).getPlayerRoomName();
    String petInfo = "";
    if (pet.getPetRoomName().equals(playerRoomName)) {
      petInfo = "Pet is in the current player's room";
    } else {
      petInfo = "Pet is not in the current player's room";
    }
    for (int i = 0; i < playerItems.size(); i++) {
      for (int j = 0; j < allItems.size(); j++) {
        if (playerItems.get(i).equals(allItems.get(j))) {
          itemsAndDamage.add(String.format("Item %s with damage power %s", playerItems.get(i),
              item[j].getItemDamageValue()));
          break;
        }
      }
    }
    return String.format(
        "player %s is in room %s and carrying %s.\n%s.\nTarget is in room %s "
            + "and Targets health's is %s.\n %s.",
        player.get(turn).getPlayerName(), player.get(turn).getPlayerRoomName(), itemsAndDamage,
        getSpaceInformation(player.get(turn).getPlayerRoomName()), target.getTargetRoomName(),
        target.getTargetHealth(), petInfo);
  }

  private List<String> getPlayerVisibility() {
    List<String> neighborPlayer = new ArrayList<>();
    int turn = getPlayerTurn();
    String playerRoomName = player.get(turn).getPlayerRoomName();
    String playerName = player.get(turn).getPlayerName();
    List<String> playersInCurrentSpace = getPlayerInRoom(playerRoomName);
    for (int i = 0; i < playersInCurrentSpace.size(); i++) {
      if (!playersInCurrentSpace.get(i).equals(playerName)) {
        neighborPlayer.add(playersInCurrentSpace.get(i));
      }
    }
    if (!pet.getPetRoomName().equals(playerRoomName)) {
      List<String> neighborSpace = getNeighbourSpace(playerRoomName);
      for (int i = 0; i < neighborSpace.size(); i++) {
        List<String> playersInSpace = getPlayerInRoom(neighborSpace.get(i));
        for (int j = 0; j < playersInSpace.size(); j++) {
          neighborPlayer.add(playersInSpace.get(j));
        }
      }
    }
    return neighborPlayer;
  }

  /**
   * Adding Human player to the world.
   * 
   * @param playerName       player name.
   * @param playerItemsLimit player item limit.
   * @param playerRoomName   player room name.
   */
  @Override
  public void addHumanPlayer(String playerName, int playerItemsLimit, String playerRoomName)
      throws IllegalArgumentException, IllegalStateException {
    if (playerName == null || playerName.trim().isEmpty() || "".equals(playerName.trim())) {
      throw new IllegalArgumentException("Invalid Player Name");
    }
    if (playerRoomName == null || playerRoomName.trim().isEmpty()
        || "".equals(playerRoomName.trim())) {
      throw new IllegalArgumentException("Invalid Player Room Name");
    }
    if (allPlayers.size() >= 10) {
      throw new IllegalStateException("Players exceeded");
    }
    if (allPlayers.contains(playerName)) {
      throw new IllegalArgumentException("player already added.Re-enter details");
    }
    if (!allRooms.contains(playerRoomName)) {
      throw new IllegalArgumentException("Entered room is not present in the World");
    }
    player.add(
        new Player(playerName, PlayerType.HUMAN, playerItemsLimit, playerRoomName));
    allPlayers.add(playerName);
  }

  /**
   * Adding computer player to the world.
   */
  @Override
  public void addComputerPlayer() throws IllegalArgumentException, IllegalStateException {
    if (allPlayers.size() >= 10) {
      throw new IllegalStateException("Players exceeded");
    }
    numberOfComputerPlayers++;
    String playerName = "Computer" + String.valueOf(numberOfComputerPlayers);
    Random randomInteger = new Random();
    int playerItemsLimit = randomInteger.nextInt((allItems.size()));
    int playerRoomIndex = randomInteger.nextInt((allRooms.size()));
    String playerRoomName = room[playerRoomIndex].getRoomName();
    if (playerItemsLimit < 0) {
      throw new IllegalArgumentException("Invalid players item limit");
    }
    if (!allRooms.contains(playerRoomName)) {
      throw new IllegalArgumentException("Entered room is not present in the World");
    }
    player
        .add(new Player(playerName, PlayerType.COMPUTER, playerItemsLimit, "Armory"));
    allPlayers.add(playerName);
  }

  /**
   * Get the player information.
   * 
   * @return string player information.
   */
  @Override
  public String getPlayerDescription() throws IllegalArgumentException {
    int turn = getPlayerTurn();
    int playerIndex = findPlayerIndex(player.get(turn).getPlayerName());
    return String.format("player %s is in room %s and carrying items %s",
        player.get(playerIndex).getPlayerName(), player.get(playerIndex).getPlayerRoomName(),
        player.get(playerIndex).getPlayerItems());
  }

  /**
   * Move Player to another room.
   * 
   */
  @Override
  public void movePlayer(int y, int x) throws IllegalArgumentException {
    if (y < 0 || x < 0) {
      throw new IllegalArgumentException("invalid coordinates");
    }
    String roomName = null;
    for (int i = 0; i < allRooms.size(); i++) {
      if (y >= room[i].getUpperLeftColumn() && y <= room[i].getLowerRightColumn() + 1
          && x >= room[i].getUpperLeftRow() && x <= room[i].getLowerRightRow()) {
        roomName = room[i].getRoomName();
        break;
      }
    }
    if (allPlayers.size() == 0) {
      throw new IllegalStateException("No Player added");
    }
    int turn = getPlayerTurn();
    String currentPlayerRoom = player.get(turn).getPlayerRoomName();
    if (roomName == null) {
      throw new IllegalStateException("Entered room is not a room");
    }
    if (!getNeighbourSpace(currentPlayerRoom).contains(roomName)) {
      throw new IllegalArgumentException("Entered room is not a neighbor of player");
    }
    player.get(turn).updatePlayerRoomName(roomName);
    playerIndex++;
    this.numberOfTurns--;
    moveTarget();
    movePetDfs();
  }

  /**
   * Picks an item from the room.
   * 
   * @param itemName item name.
   */
  @Override
  public void playerPickItems(String itemName)
      throws IllegalArgumentException, IllegalStateException {
    if (itemName == null || itemName.trim().isEmpty() || "".equals(itemName.trim())) {
      throw new IllegalArgumentException("Invalid Item Name");
    }
    if (!allItems.contains(itemName)) {
      throw new IllegalArgumentException("No such item is present");
    }
    if (allPlayers.size() == 0) {
      throw new IllegalStateException("No Player added");
    }
    int turn = getPlayerTurn();
    String playerRoomName = player.get(turn).getPlayerRoomName();
    int i = findRoomIndex(playerRoomName);
    if (player.get(turn).getPlayerItems().size() >= player.get(turn).getPlayerItemsLimit()) {
      throw new IllegalArgumentException("Can't pick item, as it extends player's item limit");
    }
    if (room[i].getRoomItems().size() == 0) {
      throw new IllegalStateException("Room doesn't contain any items.Can't pick item");
    }
    if (!room[i].getRoomItems().contains(itemName)) {
      throw new IllegalArgumentException("Entered item is not in the room");
    }
    player.get(turn).pickItem(itemName);
    room[i].removeItem(itemName);
    playerIndex++;
    this.numberOfTurns--;
    moveTarget();
    movePetDfs();
  }


  /**
   * Checking if player is computer.
   * 
   * @return boolean.
   */
  @Override
  public boolean checkComputerPlayer() {
    int turn = getPlayerTurn();
    return player.get(turn).getPlayerType() == PlayerType.COMPUTER;
  }

  private int getItemIndex(String itemName) {
    int i = 0;
    for (i = 0; i < allItems.size(); i++) {
      if (item[i].getItemName().equals(itemName)) {
        break;
      }
    }
    return i;
  }

  /**
   * Computer Plays the game.
   */
  @Override
  public String playComputerPlayer() {
    String str = null;
    int turn = getPlayerTurn();
    String playerRoomName = player.get(turn).getPlayerRoomName();
    int indexOfRoom = findRoomIndex(playerRoomName);
    List<String> neighbors = getNeighbourSpace(playerRoomName);
    List<String> items = room[indexOfRoom].getRoomItems();
    if (getPlayerVisibility().size() == 0 && target.getTargetRoomName().equals(playerRoomName)) {
      str = computerAttackTarget();
    } else {
      int playerTurn = randomInteger.generateRandomInteger() % 4;
      if (playerTurn == 0) {
        int moveroom = randomInteger.generateRandomInteger() % (neighbors.size());
        player.get(turn).updatePlayerRoomName(neighbors.get(moveroom));
        str = String.format("Computer player moved");
      } else if (playerTurn == 1) {
        if (room[indexOfRoom].getRoomItems().size() != 0) {
          player.get(turn).pickItem(items.get(0));
          room[indexOfRoom].removeItem(items.get(0));
        }
        str = String.format("Computer player picked an item");
      } else if (playerTurn == 2) {
        str = String.format("Computer player Looked Around the space");
      } else if (playerTurn == 3) {
        int moveroom = randomInteger.generateRandomInteger() % (allRooms.size());
        pet.updatePetRoomName(allRooms.get(moveroom));
        str = String.format("Computer player moved Pet to another room");
      }
    }
    playerIndex++;
    this.numberOfTurns--;
    moveTarget();
    movePetDfs();
    return str;
  }

  private String computerAttackTarget() {
    String str = null;
    int maxItemIndexHealth = 0;
    int maxHealth = 0;
    int turn = getPlayerTurn();
    List<String> playerItems = player.get(turn).getPlayerItems();
    if (playerItems.size() != 0) {
      for (int i = 0; i < playerItems.size(); i++) {
        int intemIndex = getItemIndex(playerItems.get(i));
        if (item[intemIndex].getItemDamageValue() > maxHealth) {
          maxHealth = item[intemIndex].getItemDamageValue();
          maxItemIndexHealth = intemIndex;
        }
      }
      target.updateTargetHealth(
          target.getTargetHealth() - item[maxItemIndexHealth].getItemDamageValue());
      str = String.format(
          "Target attack is successful with item %s and damage power %d"
              + " and Target health decreased by %d",
          item[maxItemIndexHealth].getItemName(), item[maxItemIndexHealth].getItemDamageValue(),
          target.getTargetHealth());
    } else {
      target.updateTargetHealth(target.getTargetHealth() - 1);
      str = String.format(
          "Target attack is successful by poking him in the eye and Target health decreased to %d",
          target.getTargetHealth());
    }
    return str;
  }

  @Override
  public boolean isGameOver() {
    if (this.numberOfTurns == 0 || target.getTargetHealth() <= 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String getWinner() {
    playerIndex--;
    int turn = getPlayerTurn();
    if (target.getTargetHealth() <= 0) {
      return String.format("Game Over.Player %s killed the" + " target and he is the winner.",
          player.get(turn).getPlayerName());
    } else {
      return String.format("Game Over as total number of turns reached.Target"
          + " is not killed and no one wins the game.");
    }
  }

  @Override
  public String getPlayerNameTurn() {
    int turn = getPlayerTurn();
    return String.format("Player's Turn: %s", player.get(turn).getPlayerName());
  }

  /**
   * Moving pet in DFS order.
   */
  private void movePetDfsTraversal() {
    boolean[] visitedNode = new boolean[numberOfRooms];
    List<String> neighbourSpaces;
    for (int i = 0; i < numberOfRooms; i++) {
      neighbourSpaces = this.findRoomNeighbours(i);
      for (String roomName : neighbourSpaces) {
        neighborNode[i].add(findRoomIndex(roomName));
      }
    }
    dfsUtil(source, visitedNode);
  }

  private void dfsUtil(int vertex, boolean[] visitedNode) {
    visitedNode[vertex] = true;
    traversal.add(vertex);
    Iterator<Integer> i = neighborNode[vertex].listIterator();
    while (i.hasNext()) {
      int node = i.next();
      if (!visitedNode[node]) {
        dfsUtil(node, visitedNode);
        traversal.add(vertex);
      }
    }
  }

  private void movePetDfs() {
    petindex++;
    petindex = petindex % traversal.size();
    String petroomname = room[traversal.get(petindex)].getRoomName();
    pet.updatePetRoomName(petroomname);
  }

  @Override
  public String[] getRooms() {
    List<String> roomsCopy = new ArrayList<>(allRooms);
    String[] stringRoomArray = roomsCopy.toArray(new String[0]);
    return stringRoomArray;
  }

  @Override
  public String[] getRoomItems() {
    int turn = getPlayerTurn();
    String playerRoom = player.get(turn).getPlayerRoomName();
    int i = findRoomIndex(playerRoom);
    List<String> roomItems = room[i].getRoomItems();
    String[] stringRoomItemsArray = roomItems.toArray(new String[0]);
    return stringRoomItemsArray;
  }

  @Override
  public String[] getPlayerItems() {
    int turn = getPlayerTurn();
    List<String> playerItems = player.get(turn).getPlayerItems();
    String[] stringPlayerItemsArray = playerItems.toArray(new String[0]);
    return stringPlayerItemsArray;
  }

  @Override
  public String getPlayers() {
    return allPlayers.toString();
  }

}