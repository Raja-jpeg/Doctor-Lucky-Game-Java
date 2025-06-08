package mansion.view;

import mansion.controller.PlayerCommandInterface;

/**
 * This is the MansionBoardGameViewInterface.
 * It represents the view component for the game.
 *
 */
public interface MansionBoardGameViewInterface {

  /**
   * This method is used to make the welcome panel visible when the game starts.
   */
  void displayWelcomePanel();

  /**
   * Configures the gameplay by applying various components.
   * 
   * @param component The component to be configured.
   */
  void configureGamePlay(PlayerCommandInterface component);

  /**
   * Shows a message box for various actions.
   * 
   * @param message The message to display.
   */
  void showMessage(String value);

  /**
   * Displays a message box after updating each player's turn.
   * 
   * @param message The message to display.
   */
  void showTurnMessage(String value);

  /**
   * Shows an error message box.
   * 
   * @param errorMessage The error message to display.
   */
  void showErrorMessage(String value);

  /**
   * Starts a new game.
   */
  void startGame();

  /**
   * Adds players to the game.
   */
  void addPlayers();

  /**
   * Shows the dialog box to enable players to pick an item from the list of items.
   */
  void showPickItemBox();

  /**
   * Shows the dialog box after a player attempts an attack.
   */
  void showAttackTargetBox();

  /**
   * Shows the dialog box after the move pet method is invoked.
   */
  void showMovePetBox();

  /**
   * Quits the game.
   */
  void quitGame();

  /**
   * Starts a new game.
   */
  void startNewGame();

  /**
   * This method is used to start a new game with a new world specification.
   * 
   * @param component PlayerCommandInterface object.
   */
  void startNewMapGame(PlayerCommandInterface component);
}