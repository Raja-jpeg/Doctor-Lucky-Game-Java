package mockmodels;

import java.awt.image.BufferedImage;
import mansion.GameControls;

/**
 * This is the HumanPlayerSimulation class which implements the GameControls interface.
 * A simulated representation of a human player used to separate the controller.
 * This model class solely records the given inputs and responds with a 
 * distinct number assigned during its creation. It verifies the accuracy
 * of transmitting inputs from the controller to the model and ensuring 
 * that the model's outcomes are accurately conveyed to the 
 * Appendable object by the controller.
 */
public class HumanPlayerSimulation implements GameControls {
  private StringBuilder message;
  private final int uniqueIdentifier;

  /**
   * This is the HumanPlayerSimulation class constructor.
   * It is helpful in initializing the HumanPlayerSimulation class fields.
   * 
   * @param message the message to be displayed.
   * @param uniqueIdentifier checks if the correct method is invoked or not.
   */
  public HumanPlayerSimulation(StringBuilder message, int uniqueIdentifier) {
    this.message = message;
    this.uniqueIdentifier = uniqueIdentifier;
  }

  @Override
  public String getSpaceInformation(String roomName) throws IllegalArgumentException {
    message.append("getSpaceInformation method invoked with input " + roomName);
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public void createWorldGraphicalRep() {
    message.append("createWorldGraphicalRep method invoked" + "\n");
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public void addHumanPlayer(String playerName, int playerItemsLimit, String playerRoomName) {
    message.append("addHumanPlayer method invoked with player name, " + playerName 
        + "player item limit" + playerItemsLimit + "player room name" + playerRoomName + "\n");
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public void addComputerPlayer() {
    message.append("addComputerPlayer method invoked" + "\n");
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public void movePlayer(int x, int y) {
    message.append("movePlayer method invoked with input " + x + y);
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public void playerPickItems(String itemName) {
    message.append("playerPickItems method invoked with input " + itemName);
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public String playerLookAround() {
    message.append("playerLookAround method invoked");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public boolean checkComputerPlayer() {
    message.append("checkComputerPlayer method invoked" + "\n");
    return false;
  }

  @Override
  public void movePet(String roomName) {
    message.append("movePet method invoked with input " + roomName);
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public String attackTarget(String itemName) {
    message.append("attackTarget method invoked with input " + itemName);
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }


  @Override
  public String playComputerPlayer() {
    message.append("playComputerPlayer method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public boolean checkIfPlayerHasItems() {
    message.append("checkIfPlayerHasItems method invoked" + "\n");
    return true;
  }

  @Override
  public String pokeTheTargetCharacter() {
    message.append("pokeTheTargetCharacter method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public String getPlayerNameTurn() {
    message.append("getPlayerNameTurn method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public boolean isGameOver() {
    message.append("isGameOver method invoked" + "\n");
    return true;
  }

  @Override
  public String getWinner() {
    message.append("getWinner method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public String[] getRooms() {
    message.append("getRooms method invoked" + "\n");
    String[] arr = { Integer.toString(uniqueIdentifier) };
    return arr;
  }

  @Override
  public String[] getRoomItems() {
    message.append("getRoomItemss method invoked" + "\n");
    String[] arr = { Integer.toString(uniqueIdentifier) };
    return arr;
  }

  @Override
  public String[] getPlayerItems() {
    message.append("getPlayerItems method invoked" + "\n");
    String[] arr = { Integer.toString(uniqueIdentifier) };
    return arr;
  }

  @Override
  public String getPlayers() {
    message.append("getPlayers method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public BufferedImage getMansionBoardStatusImage() {
    message.append("getMansionBoardStatusImage method invoked" + "\n");
    return null;
  }

  @Override
  public void startNewGame() {
    message.append("startNewGame method invoked" + "\n");
    message.append(String.format("uniqueIdentifier is " + uniqueIdentifier));
  }

  @Override
  public String getPlayerDescription() {
    message.append("getPlayerDescription method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }

  @Override
  public boolean checkPlayerInfo(int y, int x) throws IllegalArgumentException {
    message.append("checkplayerinfo method invoked" + "\n");
    return false;
  }

  @Override
  public String getHintsAboutWorld() {
    message.append("getHintsAboutWorld method invoked" + "\n");
    return String.format("uniqueIdentifier is " + uniqueIdentifier);
  }


}
