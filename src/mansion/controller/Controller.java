package mansion.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.InputMismatchException;
import mansion.GameControls;
import mansion.RandomImpl;
import mansion.TheWorld;
import mansion.controller.actions.ActionInterface;
import mansion.controller.actions.AddComputerPlayer;
import mansion.controller.actions.AddHumanPlayer;
import mansion.controller.actions.MovePetCharacter;
import mansion.controller.actions.PickItem;
import mansion.view.MansionBoardGameViewImpl;
import mansion.view.MansionBoardGameViewInterface;


/**
 * This is the Controller class. It is implemented such that it 
 * Implements World controller to interact with the model.
 */
public class Controller implements PlayerCommandInterface, ControllerInterface {

  private MansionBoardGameViewInterface view;
  private GameControls model;
  private final int numberOfTurns;
  private String file;

  /**
   * This is the Controller class constructor.
   * It is useful in initializing all the controller class fields.
   * 
   * @param model mansion world object.
   * @param numberOfTurns maximum number of turns.
   * @param file input file location.
   */
  public Controller(GameControls model, int numberOfTurns, String file) {
    if (model == null) {
      throw new IllegalArgumentException("Model is invalid");
    }
    if (numberOfTurns < 0) {
      throw new IllegalArgumentException("Input for number of turns is invalid");
    }
    if (file == null || file.trim().isEmpty() || "".equals(file.trim())) {
      throw new IllegalArgumentException("The file type is invalid or not supported");
    }
    this.model = model;
    this.numberOfTurns = numberOfTurns;
    this.file = file;
  }

  @Override
  public void playGame(MansionBoardGameViewInterface view) throws IllegalArgumentException {
    if (view == null) {
      throw new IllegalArgumentException("Invalid view");
    }
    this.view = view;
    view.displayWelcomePanel();
    view.configureGamePlay(this);
  }

  @Override
  public void startNewGame() throws IllegalArgumentException {
    Readable fr = null;
    try {
      fr = new FileReader(this.file);
    } catch (FileNotFoundException e) {
      view.showErrorMessage("File not Found");
    }
    view.startNewGame();
    model = new TheWorld(fr, new RandomImpl(), numberOfTurns);
    MansionBoardGameViewInterface gameview = new MansionBoardGameViewImpl(model);
    ControllerInterface gamecontroller = new Controller(model, numberOfTurns, this.file);
    gamecontroller.playGame(gameview);
  }

  @Override
  public void startNewMapGame(String map) {
    if (map == null || map.trim().isEmpty() || "".equals(map.trim())) {
      throw new IllegalArgumentException("Invalid file");
    }
    Readable fr = null;
    try {
      fr = new FileReader(map);
    } catch (FileNotFoundException e) {
      view.showErrorMessage("File not Found");
    }
    ControllerInterface gamecontroller;
    try {
      model = new TheWorld(fr, new RandomImpl(), numberOfTurns);
      MansionBoardGameViewInterface gameview = new MansionBoardGameViewImpl(model);
      view.startNewGame();
      gamecontroller = new Controller(model, numberOfTurns, map);
      gamecontroller.playGame(gameview);
    } catch (InputMismatchException e) {
      view.showErrorMessage("File format is wrong");
      gamecontroller = new Controller(model, numberOfTurns, this.file);
      gamecontroller.playGame(this.view);
    }
  }

  @Override
  public void addHumanPlayer(String playerName, String playerRoomName, String playerItemsLimit) {
    if (playerName == null || playerName.trim().isEmpty() || "".equals(playerName.trim())) {
      view.showErrorMessage("player name cannot be null");
      throw new IllegalArgumentException("Invalid player name");
    }
    if (playerRoomName == null || playerRoomName.trim().isEmpty()
        || "".equals(playerRoomName.trim())) {
      throw new IllegalArgumentException("Invalid room name");
    }
    if (playerItemsLimit == null || playerItemsLimit.trim().isEmpty()
        || "".equals(playerItemsLimit.trim())) {
      throw new IllegalArgumentException("Invalid items");
    }
    int items = Integer.parseInt(playerItemsLimit);
    ActionInterface obj = new AddHumanPlayer(playerName, playerRoomName, items);
    try {
      obj.execute(model);
      view.showMessage("Human player added to the game");
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Player details invalid");
    } catch (IllegalStateException e) {
      view.showErrorMessage("Players limit exceeded");
    }
  }

  @Override
  public void addComputerPlayer() {
    ActionInterface obj = new AddComputerPlayer();
    try {
      obj.execute(model);
      view.showMessage("Computer player added to the game");
    } catch (IllegalStateException e) {
      view.showErrorMessage("Players limit exceeded");
    }
  }

  @Override
  public void startGame() {
    if (model.getPlayers().length() == 2) {
      view.showErrorMessage("Atleast one player needs to be added to play game");
    } else {
      view.startGame();
      checkComputerPlayer();
    }
  }

  private void checkComputerPlayer() {
    checkIfGameOver();
    while (model.checkComputerPlayer()) {
      view.showTurnMessage(model.playComputerPlayer());
      checkIfGameOver();
    }
  }

  private void checkIfGameOver() {
    if (model.isGameOver()) {
      view.showTurnMessage(model.getWinner());
      view.quitGame();
    }
  }

  @Override
  public void addPlayers() {
    view.addPlayers();
  }

  @Override
  public void lookAround() {
    view.showTurnMessage(model.playerLookAround());
    checkComputerPlayer();
    checkIfGameOver();
  }

  @Override
  public void pickItem(String itemName) {
    if (itemName == null || itemName.trim().isEmpty() || "".equals(itemName.trim())) {
      throw new IllegalArgumentException("The name of the item is invalid");
    }
    ActionInterface obj = new PickItem(itemName);
    try {
      obj.execute(model);
      view.showTurnMessage("Player picks up the Item.");
      checkComputerPlayer();
      checkIfGameOver();
    } catch (IllegalStateException e) {
      view.showErrorMessage("Room is empty: does not have any items.");
    } catch (IllegalArgumentException e) {
      view.showErrorMessage("Player's item limit at capacity. Cannot pick item");
    }
  }

  @Override
  public void attackTarget(String itemName) {
    if (itemName == null || itemName.trim().isEmpty() || "".equals(itemName.trim())) {
      throw new IllegalArgumentException("The name of the item is invalid");
    }
    try {
      model.attackTarget(itemName);
      view.showTurnMessage("Successful attack on Target.");
    } catch (IllegalArgumentException e) {
      view.showTurnMessage("Unsuccessful attack on Target: Target is not in the same room");
    } catch (IllegalStateException e) {
      view.showTurnMessage(
          "Unsuccessful attack on Target: Player is visible to other players");
    }
    checkComputerPlayer();
    checkIfGameOver();
  }

  @Override
  public void playerItems() {
    try {
      model.pokeTheTargetCharacter();
      view.showTurnMessage("Successful attack on Target");
    } catch (IllegalArgumentException e) {
      view.showTurnMessage("Unsuccessful attack on Target: Player is visible to other players");
    } catch (IllegalStateException e) {
      view.showTurnMessage(
          "Unsuccessful attack on Target: Player is visible to other players");
    }
    checkComputerPlayer();
    checkIfGameOver();
  }

  @Override
  public void movePet(String roomName) {
    if (roomName == null || roomName.trim().isEmpty() || "".equals(roomName.trim())) {
      throw new IllegalArgumentException("The name of the room is invalid");
    }
    ActionInterface obj = new MovePetCharacter(roomName);
    obj.execute(model);
    view.showTurnMessage("Pet moved to " + roomName);
    checkComputerPlayer();
    checkIfGameOver();
  }

  @Override
  public void quitGame() {
    view.quitGame();
  }

  @Override
  public void movePlayer(int i, int j) {
    try {
      if (model.checkPlayerInfo(i, j)) {
        view.showMessage(model.getPlayerDescription());
      } else {
        model.movePlayer(i, j);
        view.showTurnMessage("Moved Player to another room");
        checkComputerPlayer();
        checkIfGameOver();
      }
    } catch (IllegalArgumentException e) {
      view.showTurnMessage("Player cannot be moved: Not an adjacent space.");
    } catch (IllegalStateException e) {
      view.showTurnMessage("Player cannot be moved: Invalid space.");
    }
  }

}
