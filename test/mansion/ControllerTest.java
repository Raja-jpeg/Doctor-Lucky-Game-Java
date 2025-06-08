package mansion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import simulation.ComputerPlayerSimulation;
import simulation.HumanPlayerSimulation;
import simulation.RandomImplSimulation;

/**
 * This is ControllerTest class.
 */
public class ControllerTest {
  private ControllerInterface control;
  
  private GameControls modelComputer;
  
  private GameControls modelHuman;
  
  private StringBuilder log;
  
  private StringBuffer out;
  
  private StringBuilder input;
  
  private StringBuilder output;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.log = new StringBuilder();
    this.modelComputer = (GameControls) new ComputerPlayerSimulation(this.log, 1234321);
    this.modelHuman = (GameControls) new HumanPlayerSimulation(this.log, 1234321);
    this.out = new StringBuffer();
  }
  
  
  @Test
  public void testInvalidPlayerRoom() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Image in created in res folder\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter a valid command\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 asd\n1\nHuman 2 Kitchen\n2\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name, "
            + "Humanplayer item limit2player room nameasd\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedCreate world "
            + "graphical representation method invoked\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerLookAround"
            + " method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerItem() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output
      .append("===================Killing Doctor Lucky Game Controller======================\n");
    this.output
      .append("===============================Game Start====================================\n");
    this.output.append("Enter no of Players\n");
    this.output.append("Choose a command[AddHumanPlayer,AddComputerPlayer] to add players\n");
    this.output.append("Enter player name,player item limit and player room\n");
    this.output.append("Human player added to the game\n");
    this.output.append(
        "======================All Players added to the game=============================\n");
    this.output.append(
        "Choose a command[WorldGraphicalRep,LookAround,MovePlayer,"
        + "PickItem,PlayerInfo,SpaceInfo] for Player 1\n");
    this.output.append("Human player added to the game\n");
    this.output.append(
        "Choose a command[WorldGraphicalRep,LookAround,MovePlayer,"
        + "PickItem,PlayerInfo,SpaceInfo] for Player 1\n");
    this.output.append("UniqueCode is 1234321\n");
    this.output.append("======================Game Over=============================\n");
    this.input.append("1\n1\nHuman -2 asd\n1\nHuman 2 Kitchen\n2");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit-2player room nameasd\n");
    sb.append("UniqueCode is 1234321checkComputerPlayer method invoked\n");
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n");
    sb.append("UniqueCode is 1234321checkComputerPlayer method invoked\n");
    sb.append("playerLookAround method invoked");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testInvalidCommand() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter a valid command\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\ndasdaf\n2\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerLookAround"
            + " method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testInvalidMoveRoom() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter room name\n"
            + "Player moved to another room\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter a valid command\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter a valid command\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n3\nasd\nKitchen\nParlor\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invoked"
            + "movePlayer method invoked with input asdUniqueCode is 1234321"
            + "isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invoked"
            + "isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invoked"
            + "isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invoked"
            + "getWinner method invoked\n");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testLookAround() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n2\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invoked"
            + "playerLookAround method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testItemPick() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "Player picked item\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n4\nCrepe Pan\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerPickItems method"
            + " invoked with input Crepe PanUniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testPlayerInfo() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter player name\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n5\nHuman\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetPlayerDescription method"
            + " invoked with input HumanisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testSpaceInfo() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter Space name\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n6\nKitchen\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetSpaceInformation"
            + " method invoked with input KitchenisGameNotOver"
            + " method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testCreateGraphicalRep() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Image in created in res folder\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n1\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with"
             + " player name, Humanplayer item limit2player room nameKitchen\n"
             + "UniqueCode is 1234321isGameNotOver method invoked\n"
             + "checkComputerPlayer method invoked\n"
             + "getPlayerNameTurn method invoked\n"
             + "getHintsAboutWorld method invoked"
             + "Create world graphical representation method invoked\n"
             + "UniqueCode is 1234321isGameNotOver method invoked\n"
             + "checkComputerPlayer method invoked\n"
             + "getPlayerNameTurn method invoked\n"
             + "getHintsAboutWorld method invokedgetWinner method invoked\n"
             + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testMovePlayer() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter room name\n"
            + "Player moved to another room\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n3\nParlor\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invoked"
            + "movePlayer method invoked with input ParlorUniqueCode "
            + "is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());;
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testMovePet() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter room name\n"
            + "Pet moved to another room\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Kitchen\n7\nParlor\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameKitchen\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedmovePet method invoked"
            + " with input ParlorUniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testAttackTarget() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "Player picked item\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Carriage House\n4\nChain Saw\n2\n8\nChain Saw\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameCarriage House\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerPickItems"
            + " method invoked with input Chain SawUniqueCode is"
            + " 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerLookAround"
            + " method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedcheckIfPlayerHasItems method invoked\n"
            + "attackTarget method invoked with input Chain SawisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testGetWinnerAfterAttackTarget() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "Player picked item\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Carriage House\n4\nChain Saw\n2\n8\nChain Saw\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameCarriage House\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerPickItems method "
            + "invoked with input Chain SawUniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerLookAround"
            + " method invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedcheckIfPlayerHasItems method invoked\n"
            + "attackTarget method invoked with input Chain SawisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void isGameOver() {
    this.input = new StringBuilder();
    this.output = new StringBuilder();
    this.output.append("===================Milestone 3======================\n"
            + "====================Play it to win it====================\n"
            + "Enter the number of players\n"
            + "Choose a command option to add players\n"
            + "1---->AddHumanPlayer\n"
            + "2---->AddComputerPlayer\n"
            + "Enter player name,player item limit and player room\n"
            + "Human player added to the game\n"
            + "=================All Players added to the game========================\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "Player picked item\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "Enter item name\n"
            + "UniqueCode is 1234321\n"
            + "=========Perform an action for Player UniqueCode is 1234321==============\n"
            + "1.) WorldGraphicalRep\n"
            + "2.) LookAround\n"
            + "3.) MovePlayer\n"
            + "4.) PickItem\n"
            + "5.) PlayerInfo\n"
            + "6.) SpaceInfo\n"
            + "7.) MovePet\n"
            + "8.) AttackTarget\n"
            + "==========Hints===========\n"
            + "UniqueCode is 1234321\n"
            + "=================================================================\n"
            + "UniqueCode is 1234321\n"
            + "======================Game Over=============================\n"
            + "");
    this.input.append("1\n1\nHuman 2 Carriage House\n4\nChain Saw\n2\n8\nChain Saw\nq");
    Readable in = new StringReader(this.input.toString());
    this.control = (ControllerInterface) new Controller(in, this.out);
    this.control.playGame(this.modelHuman);
    StringBuilder sb = new StringBuilder();
    sb.append("addHumanPlayer method invoked with player name,"
            + " Humanplayer item limit2player room nameCarriage House\n"
            + "UniqueCode is 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerPickItems method"
            + " invoked with input Chain SawUniqueCode is"
            + " 1234321isGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedplayerLookAround method"
            + " invokedisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedcheckIfPlayerHasItems method invoked\n"
            + "attackTarget method invoked with input Chain SawisGameNotOver method invoked\n"
            + "checkComputerPlayer method invoked\n"
            + "getPlayerNameTurn method invoked\n"
            + "getHintsAboutWorld method invokedgetWinner method invoked\n"
            + "");
    Assert.assertEquals(this.output.toString(), this.out.toString());
    Assert.assertEquals(sb.toString(), this.log.toString());
  }
  
  @Test
  public void testPlayComputer() {
    Reader input;
    try {
      input = new FileReader("res/mansion.txt");
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("invalid input");
    } 
    this.output = new StringBuilder();
    this.modelComputer = (GameControls) new TheWorld(input, 
            (RandomInterface) new RandomImplSimulation(this.output, 
                    1234, new int[] { 1, 2, 3 }), 5);
    this.modelComputer.addComputerPlayer();
    Assert.assertEquals(
        "Target attack is successful by poking him in the eye and Target health decreased to 3", 
        this.modelComputer.playComputerPlayer());
    Assert.assertEquals("Computer player picked an item", this.modelComputer.playComputerPlayer());
    Assert.assertEquals("Computer player Looked Around the space", 
            this.modelComputer.playComputerPlayer());
    Assert.assertEquals("Computer player moved Pet to another room",
            this.modelComputer.playComputerPlayer());
  }
}
