package mockmodels;

import mansion.controller.PlayerCommandInterface;
import mansion.view.MansionBoardGameViewInterface;

/**
 * This is the MansionViewSimulation class which implements MansionBoardGameViewInterface.
 * It simulates the view component of the game.
 */
public class MansionViewSimulation implements MansionBoardGameViewInterface {
  private StringBuilder message;
  private final int uniqueIdentifier;

  /**
   * This is the MansionViewSimulation class constructor.
   * It is helpful in initializing the MansionViewSimulation class fields.
   * 
   * @param message the message.
   * @param uniqueIdentifier checks if the methods are invoked correctly.
   */
  public MansionViewSimulation(StringBuilder message, int uniqueIdentifier) {
    this.message = message;
    this.uniqueIdentifier = uniqueIdentifier;
  }

  @Override
  public void displayWelcomePanel() {
    message.append("displayWelcomePanelVisible invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void configureGamePlay(PlayerCommandInterface component) {
    message.append("configureGamePlay invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void showMessage(String value) {
    message.append("showMessage invoked with input" + value + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void showTurnMessage(String value) {
    message.append("showTurnMessage invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void showErrorMessage(String value) {
    message.append("showErrorMessage invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void startGame() {
    message.append("startGame invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void addPlayers() {
    message.append("addPlayers invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void showPickItemBox() {
    message.append("showPickItemBox invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void showAttackTargetBox() {
    message.append("showAttackTargetBox invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void showMovePetBox() {
    message.append("showMovePetBox invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void quitGame() {
    message.append("quitGame invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void startNewGame() {
    message.append("startNewGame invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

  @Override
  public void startNewMapGame(PlayerCommandInterface f) {
    message.append("startNewMapGame invoked" + "\n");
    message.append(String.format("Unique Code: %s\n", uniqueIdentifier));
  }

}