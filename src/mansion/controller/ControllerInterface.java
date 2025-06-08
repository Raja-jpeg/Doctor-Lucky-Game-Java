package mansion.controller;

import mansion.view.MansionBoardGameViewInterface;

/**
 * This is the ControllerInterface. It is useful in getting the users to 
 * interact with the model. It takes user input and acts according to it.
 */
public interface ControllerInterface {

  /**
   * Game play starts here by giving control to the controller.
   * 
   * @param view MansionBoardGameViewInterface instance.
   * @throws IllegalArgumentException if provided parameter is invalid.
   */
  void playGame(MansionBoardGameViewInterface view) throws IllegalArgumentException;
}
