package simulation;

import mansion.GameControls;

/**
 * This is HumanPlayerSimulation class.
 * It simulates the human controlled player behavior.
 */
public class HumanPlayerSimulation implements GameControls {
  
  private StringBuilder message;
  private final int uniqueCode;
  
  /**
   * This is the HumanPlayerSimulation class constructor.
   * 
   * @param message the message.
   * @param uniqueCode the unique code.
   */
  public HumanPlayerSimulation(StringBuilder message, int uniqueCode) {
    this.message = message;
    this.uniqueCode = uniqueCode;
  }
  
  @Override
  public String getSpaceInformation(String roomName) throws IllegalArgumentException {
    this.message.append("getSpaceInformation method invoked with input " + roomName);
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public void createWorldGraphicalRep() {
    this.message.append("Create world graphical representation method invoked\n");
    this.message.append(String.format("UniqueCode is " + this.uniqueCode, new Object[0]));
  }
  
  @Override
  public void addHumanPlayer(String playerName, int playerItemsLimit, String playerRoomName) {
    this.message.append("addHumanPlayer method invoked with player name, " 
          + playerName + "player item limit" 
        + playerItemsLimit + "player room name" + playerRoomName + "\n");
    this.message.append(String.format("UniqueCode is " + this.uniqueCode, new Object[0]));
  }
  
  @Override
  public void addComputerPlayer() {
    this.message.append("addComputerPlayer method invoked\n");
    this.message.append(String.format("UniqueCode is " + this.uniqueCode, new Object[0]));
  }
  
  @Override
  public void movePlayer(String roomName) {
    this.message.append("movePlayer method invoked with input " + roomName);
    this.message.append(String.format("UniqueCode is " + this.uniqueCode, new Object[0]));
  }
  
  @Override
  public void playerPickItems(String itemName) {
    this.message.append("playerPickItems method invoked with input " + itemName);
    this.message.append(String.format("UniqueCode is " + this.uniqueCode, new Object[0]));
  }
  
  @Override
  public String playerLookAround() {
    this.message.append("playerLookAround method invoked");
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public String getPlayerDescription(String playerName) {
    this.message.append("getPlayerDescription method invoked with input " + playerName);
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public boolean checkComputerPlayer() {
    this.message.append("checkComputerPlayer method invoked\n");
    return false;
  }
  
  @Override
  public void movePet(String roomName) {
    this.message.append("movePet method invoked with input " + roomName);
    this.message.append(String.format("UniqueCode is " + this.uniqueCode, new Object[0]));
  }
  
  @Override
  public String attackTarget(String itemName) {
    this.message.append("attackTarget method invoked with input " + itemName);
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public String getHintsAboutWorld() {
    this.message.append("getHintsAboutWorld method invoked");
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public String playComputerPlayer() {
    this.message.append("playComputerPlayer method invoked\n");
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public boolean checkIfPlayerHasItems() {
    this.message.append("checkIfPlayerHasItems method invoked\n");
    return Boolean.valueOf(true);
  }
  
  @Override
  public String attackTargetWithoutItems() {
    this.message.append("attackTargetWithoutItems method invoked\n");
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public String getPlayerNameTurn() {
    this.message.append("getPlayerNameTurn method invoked\n");
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
  
  @Override
  public boolean isGameNotOver() {
    this.message.append("isGameNotOver method invoked\n");
    return Boolean.valueOf(true);
  }
  
  @Override
  public String getWinner() {
    this.message.append("getWinner method invoked\n");
    return String.format("UniqueCode is " + this.uniqueCode, new Object[0]);
  }
}
