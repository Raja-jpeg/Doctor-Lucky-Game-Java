import static org.junit.Assert.assertEquals;

import mansion.controller.Controller;
import mockmodels.HumanPlayerSimulation;
import mockmodels.MansionViewSimulation;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the ControllerTest class.
 * It tests the working of all the controller components in the game.
 */
public class ControllerTest {
  private StringBuilder sbuilder;
  private HumanPlayerSimulation humanPlayerMockModel;
  private Controller controller;
  private MansionViewSimulation view;

  /**
   * Initializing the class fields.
   */
  @Before
  public void setUp() {
    sbuilder = new StringBuilder();
    humanPlayerMockModel = new HumanPlayerSimulation(sbuilder, 6789);
    view = new MansionViewSimulation(sbuilder, 9982);
    controller = new Controller(humanPlayerMockModel, 5, "res/mansion.txt");
  }

  @Test
  public void startNewGameTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n", sbuilder.toString());
    controller.startNewGame();
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n"
            + "startNewGame invoked\n"
            + "Unique Code: 9982\n",
        sbuilder.toString());

  }

  @Test
  public void startNewMapGame() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982"
            + "\n" + "", sbuilder.toString());
    controller.startNewMapGame("res/mansion.txt");
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "startNewGame invoked\n"
        + "Unique Code: 9982"
        + "\n" + "",
        sbuilder.toString());

  }

  @Test
  public void addHumanplayer() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "addHumanPlayer method invoked with player name,"
        + " Shelbyplayer item limit1player room nameKitchen\n"
        + "uniqueIdentifier is 6789showMessage invoked with inputHuman player added to the game\n"
        + "Unique Code: 9982"
        + "\n" + "",
        sbuilder.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addHumanplayerInvalid() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer(null, null, "1");
  }

  @Test
  public void addComputerPlayer() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addComputerPlayer();
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n"
            + "addComputerPlayer method invoked\n"
            + "uniqueIdentifier is 6789showMessage"
            + " invoked with inputComputer player added to the game\n"
            + "Unique Code: 9982"
            + "\n" + "", sbuilder.toString());

  }

  /**
   * Game cannot be started without adding players.
   */
  public void startGameTestInvalid() {
    controller.playGame(view);
    assertEquals("makeWelcomePanelVisible invoked\n" + "Unique Code: 5500\n"
        + "setFeatures invoked\n" + "Unique Code: 5500\n" + "", sbuilder.toString());
    controller.startGame();
    assertEquals("makeWelcomePanelVisible invoked\n" + "Unique Code: 5500\n"
        + "setFeatures invoked\n" + "Unique Code: 5500\n" + "", sbuilder.toString());

  }

  @Test
  public void lookAroundTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    controller.lookAround();
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "addHumanPlayer method invoked with player name,"
        + " Shelbyplayer item limit1player room nameKitchen\n"
        + "uniqueIdentifier is 6789showMessage invoked with inputHuman player added to the game\n"
        + "Unique Code: 9982\n"
        + "playerLookAround method invokedshowTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n"
        + "checkComputerPlayer method invoked\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982"
        + "\n" + "",
        sbuilder.toString());

  }

  @Test
  public void pickItemTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    controller.pickItem("Crepe Pan");
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "addHumanPlayer method invoked with player name,"
        + " Shelbyplayer item limit1player room nameKitchen\n"
        + "uniqueIdentifier is 6789showMessage invoked with inputHuman player added to the game\n"
        + "Unique Code: 9982\n"
        + "playerPickItems method invoked with input Crepe Pan"
        + "uniqueIdentifier is 6789showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n"
        + "checkComputerPlayer method invoked\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982"
        + "\n" + "",
        sbuilder.toString());

  }

  @Test(expected = IllegalArgumentException.class)
  public void pickItemTestInvalid() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    controller.pickItem(null);
  }

  @Test
  public void attackTargetTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982"
            + "\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Armory", "1");
    controller.attackTarget("Revolver");
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "addHumanPlayer method invoked with player name,"
        + " Shelbyplayer item limit1player room nameArmory\n"
        + "uniqueIdentifier is 6789showMessage invoked with inputHuman player added to the game\n"
        + "Unique Code: 9982\n"
        + "attackTarget method invoked with input RevolvershowTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n"
        + "checkComputerPlayer method invoked\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n",
        sbuilder.toString());

  }
 
  @Test(expected = IllegalArgumentException.class)
  public void attackTargettestInvalid() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    controller.attackTarget(null);
  }

  @Test
  public void movepetTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.movePet("Kitchen");
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n"
            + "movePet method invoked with input Kitchen"
            + "uniqueIdentifier is 6789showTurnMessage invoked\n"
            + "Unique Code: 9982\n"
            + "isGameOver method invoked\n"
            + "getWinner method invoked\n"
            + "showTurnMessage invoked\n"
            + "Unique Code: 9982\n"
            + "quitGame invoked\n"
            + "Unique Code: 9982\n"
            + "checkComputerPlayer method invoked\n"
            + "isGameOver method invoked\n"
            + "getWinner method invoked\n"
            + "showTurnMessage invoked\n"
            + "Unique Code: 9982\n"
            + "quitGame invoked\n"
            + "Unique Code: 9982"
            + "\n" + "", sbuilder.toString());

  }
 
  @Test(expected = IllegalArgumentException.class)
  public void movepetInvalid() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    controller.movePet(null);
  }

  @Test
  public void quitGameTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Armory", "1");
    controller.attackTarget("Revolver");
    controller.quitGame();
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "addHumanPlayer method invoked with player name,"
        + " Shelbyplayer item limit1player room nameArmory\n"
        + "uniqueIdentifier is 6789showMessage invoked with inputHuman player added to the game\n"
        + "Unique Code: 9982\n"
        + "attackTarget method invoked with input RevolvershowTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n"
        + "checkComputerPlayer method invoked\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982"
        + "\n" + "",
        sbuilder.toString());

  }

  @Test
  public void movePlayerTest() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());

    controller.addHumanPlayer("Shelby", "Armory", "1");
    controller.movePlayer(19, 23);
    assertEquals(
        "displayWelcomePanelVisible invoked\n"
        + "Unique Code: 9982\n"
        + "configureGamePlay invoked\n"
        + "Unique Code: 9982\n"
        + "addHumanPlayer method invoked with player name,"
        + " Shelbyplayer item limit1player room nameArmory\n"
        + "uniqueIdentifier is 6789showMessage invoked with inputHuman player added to the game\n"
        + "Unique Code: 9982\n"
        + "checkplayerinfo method invoked\n"
        + "movePlayer method invoked with input 1923"
        + "uniqueIdentifier is 6789showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982\n"
        + "checkComputerPlayer method invoked\n"
        + "isGameOver method invoked\n"
        + "getWinner method invoked\n"
        + "showTurnMessage invoked\n"
        + "Unique Code: 9982\n"
        + "quitGame invoked\n"
        + "Unique Code: 9982"
        + "\n" + "",
        sbuilder.toString());

  }

  @Test
  public void movePlayerInvalid() {
    controller.playGame(view);
    assertEquals("displayWelcomePanelVisible invoked\n"
            + "Unique Code: 9982\n"
            + "configureGamePlay invoked\n"
            + "Unique Code: 9982\n" + "", sbuilder.toString());
    controller.addHumanPlayer("Shelby", "Kitchen", "1");
    controller.movePlayer(-1, -4);
  }
}