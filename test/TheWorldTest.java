import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import mansion.GameControls;
import mansion.RandomImpl;
import mansion.TheWorld;
import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing World Class.
 */
public class TheWorldTest {

  private GameControls world;
  private File exampleRunFile;
  private StringBuilder out;

  /**
   * Setting up file value and world object before calling test class.
   */
  @Before
  public void setUp() {
    exampleRunFile = new File("res/ExampleRun.txt");
    out = new StringBuilder();
    out.append("36 30 Doctor Lucky's Mansion\n");
    out.append("4 Doctor Lucky\n");
    out.append("Fortune the Cat\n");
    out.append("21\n");
    out.append("22 19 23 26 Armory\n");
    out.append("16 21 21 28 Billiard Room\n");
    out.append("28  0 35  5 Carriage House\n");
    out.append("12 11 21 20 Dining Hall\n");
    out.append("22 13 25 18 Drawing Room\n");
    out.append("26 13 27 18 Foyer\n");
    out.append("28 26 35 29 Green House\n");
    out.append("30 20 35 25 Hedge Maze\n");
    out.append("16  3 21 10 Kitchen\n");
    out.append(" 0  3  5  8 Lancaster Room\n");
    out.append(" 4 23  9 28 Library\n");
    out.append(" 2  9  7 14 Lilac Room\n");
    out.append(" 2 15  7 22 Master Suite\n");
    out.append(" 0 23  3 28 Nursery\n");
    out.append("10  5 15 10 Parlor\n");
    out.append("28 12 35 19 Piazza\n");
    out.append(" 6  3  9  8 Servants' Quarters\n");
    out.append(" 8 11 11 20 Tennessee Room\n");
    out.append("10 21 15 26 Trophy Room\n");
    out.append("22  5 23 12 Wine Cellar\n");
    out.append("30  6 35 11 Winter Garden\n");
    out.append("20\n");
    out.append("8 3 Crepe Pan\n");
    out.append("4 2 Letter Opener\n");
    out.append("12 2 Shoe Horn\n");
    out.append("8 3 Sharp Knife\n");
    out.append("0 3 Revolver\n");
    out.append("15 3 Civil War Cannon\n");
    out.append("2 4 Chain Saw\n");
    out.append("16 2 Broom Stick\n");
    out.append("1 2 Billiard Cue\n");
    out.append("19 2 Rat Poison\n");
    out.append("6 2 Trowel\n");
    out.append("2 4 Big Red Hammer\n");
    out.append("6 2 Pinking Shears\n");
    out.append("18 3 Duck Decoy\n");
    out.append("13 2 Bad Cream\n");
    out.append("18 2 Monkey Hand\n");
    out.append("11 2 Tight Hat\n");
    out.append("19 2 Piece of Rope\n");
    out.append("9 3 Silken Cord\n");
    out.append("7 2 Loud Noise");
    Reader input = new StringReader(out.toString());
    world = new TheWorld(input, new RandomImpl(), 5);
  }

  /**
   * test if we are creating an image for world.
   */
  @Test
  public void testGenerateGraphicalRepresesntaion() {
    assertTrue(new File("res", "image.png").exists());
  }

  /**
   * Checks if the example run file contains data.
   */
  @Test
  public void testExampleRunFile() {
    assertTrue(exampleRunFile.length() != 0);
  }

  /**
   * test if room name is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpaceInfo() {
    world.getSpaceInformation(null);
  }

  /**
   * test the space information.
   */
  @Test
  public void testSpaceInfoWithNoPlayer() {
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players []", world.getSpaceInformation("Kitchen"));
  }

  /**
   * test if room name is invalid in space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSpaceInfoWithNullPlayer() {
    world.getSpaceInformation(null);
  }

  /**
   * test space info for one player.
   */
  @Test
  public void testSpaceInfoWithOnePlayer() {
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players []", world.getSpaceInformation("Kitchen"));
    world.addHumanPlayer("Human", 3, "Kitchen");
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players [Human]", world.getSpaceInformation("Kitchen"));
  }

  /**
   * test space info for multiple player.
   */
  @Test
  public void testSpaceInfoMultiplePlayer() {
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players []", world.getSpaceInformation("Kitchen"));
    world.addHumanPlayer("Human", 3, "Kitchen");
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players [Human]", world.getSpaceInformation("Kitchen"));
    world.addHumanPlayer("Human1", 5, "Kitchen");
    assertEquals(
        "Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
        + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
        + "it has players [Human, Human1]",
        world.getSpaceInformation("Kitchen"));
  }

  /**
   * test invalid player items limit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerLimitAdded() {
    world.addHumanPlayer("Human", -3, "Kitchen");
  }

  /**
   * test invalid player room name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerRoomNameAdded() {
    world.addHumanPlayer("Human", 3, "asd");
  }

  /**
   * test if human player is added.
   */
  @Test
  public void testSinglePlayerAdded() {
    world.addHumanPlayer("Human", 3, "Kitchen");
  }

  /**
   * test if computer player is added.
   */
  @Test
  public void testSingleComputerPlayerAdded() {
    world.addComputerPlayer();
  }

  /**
   * test if multiple players are added.
   */
  @Test
  public void testMultiplePlayerAdded() {
    world.addHumanPlayer("Human", 3, "Kitchen");
    world.addHumanPlayer("Venky", 5, "Parlor");
    world.addComputerPlayer();
    world.addHumanPlayer("Naveen", 5, "Parlor");
  }

  /**
   * test invalid duplicate players added.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testDuplicatePlayerAdded() {
    world.addHumanPlayer("Human", 5, "Parlor");
    world.addHumanPlayer("Human", 5, "Parlor");
  }

  /**
   * test if player picks items.
   */
  @Test
  public void testPlayerPickItem() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players [Human]", world.getSpaceInformation("Kitchen"));
    world.playerPickItems("Crepe Pan");
    assertEquals(
        "Room Kitchen in  Doctor Lucky's Mansion has items : [Sharp Knife],\n"
        + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
        + "it has players [Human]",
        world.getSpaceInformation("Kitchen"));
    assertEquals("player Human is in room Kitchen and carrying items [Crepe Pan]",
        world.getPlayerDescription());
  }

  /**
   * test if player picks item not in the room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickItemNotInRoom() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    world.playerPickItems("Trowel");
  }

  /**
   * test if player picks invalid item.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickInvalidItem() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    world.playerPickItems("asd");
  }

  /**
   * test if player picks no item.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickNullItem() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    world.playerPickItems(null);
  }

  /**
   * test if player picks item if it crosses players item limit.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickItemBeyondLimit() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.playerPickItems("Crepe Pan");
    world.playerPickItems("Sharpe Knife");
  }

  /**
   * test if player picks item in empty room.
   */
  @Test(expected = IllegalStateException.class)
  public void testPlayerPickItemInEmptyRoom() {
    world.addHumanPlayer("Human", 1, "Parlor");
    world.playerPickItems("Crepe Pan");
  }

  /**
   * test if player looks around.
   */
  @Test
  public void testPlayerLookAround() {
    world.addHumanPlayer("Human", 1, "Parlor");
    assertEquals("Player Human is in room Parlor,\n"
            + "Room has items []\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Kitchen has items [Crepe Pan, Sharp Knife] and it has players []\n"
            + ", Room Servants' Quarters has items [Broom Stick] and it has players []\n"
            + ", Room Tennessee Room has items [] and it has players []\n"
            + "]\n"
            + "",
        world.playerLookAround());
  }

  /**
   * test player info.
   */
  @Test
  public void testPlayerInfo() {
    world.addHumanPlayer("Human", 1, "Parlor");
    assertEquals("player Human is in room Parlor and carrying items []",
        world.getPlayerDescription());
  }

  /**
   * test look around without adding any players to world.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testLookAroundWithoutPlayer() {
    assertEquals(
        "Player Human is in room Parlor and has neighbors"
            + " [Dining Hall, Kitchen, Servants' Quarters, Tennessee Room]",
        world.playerLookAround());
  }

  /**
   * test player pick items without adding any players to world.
   */
  @Test(expected = IllegalStateException.class)
  public void testPickItemWithoutPlayer() {
    world.playerPickItems("Crepe Pan");
  }

  /**
   * test computer player.
   */
  @Test
  public void testComputerPlayer() {
    world.addComputerPlayer();
    assertTrue(world.checkComputerPlayer());
  }

  /**
   * test human player.
   */
  @Test
  public void testHumanPlayer() {
    world.addHumanPlayer("Human", 1, "Parlor");
    assertFalse(world.checkComputerPlayer());
  }

  /**
   * test if player turn is updating for look around.
   */

  @Test
  public void testPlayerTurnForLookAround() {
    world.addHumanPlayer("Human", 1, "Parlor");
    world.addHumanPlayer("Human1", 2, "Kitchen");
    world.addHumanPlayer("Human2", 3, "Dining Hall");
    assertEquals("Player's Turn: Human", world.getPlayerNameTurn());
    world.playerLookAround();
    assertEquals("Player's Turn: Human1", world.getPlayerNameTurn());
    world.playerLookAround();
    assertEquals("Player's Turn: Human2", world.getPlayerNameTurn());
    world.playerLookAround();
    assertEquals("Player's Turn: Human", world.getPlayerNameTurn());
  }

  /**
   * test if player turn is updating for item pick.
   */
  @Test
  public void testPlayerTurnForItemPick() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human1", 2, "Parlor");
    assertEquals("Player's Turn: Human", world.getPlayerNameTurn());
    world.playerPickItems("Crepe Pan");
    assertEquals("Player's Turn: Human1", world.getPlayerNameTurn());
  }

  /**
   * test the location of the target.
   */
  @Test
  public void testTargetLocation() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human1", 2, "Parlor");
    assertEquals("player Human is in room Kitchen and carrying [].\n"
            + "Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players [Human].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + " Pet is not in the current player's room.", world.getHintsAboutWorld());
    world.playerLookAround();
    assertEquals("player Human1 is in room Parlor and carrying [].\n"
            + "Room Parlor in  Doctor Lucky's Mansion has items : [],\n"
            + "its neighbours are : [Dining Hall, Kitchen, Servants' Quarters, Tennessee Room],\n"
            + "it has players [Human1].\n"
            + "Target is in room Billiard Room and Targets health's is 4.\n"
            + " Pet is not in the current player's room.", world.getHintsAboutWorld());
  }

  /**
   * test look around the space when another player is in same room.
   */
  @Test
  public void testLookAroundWithOnePlayerInCurrentRoom() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human1", 2, "Kitchen");
    assertEquals("Player Human is in room Kitchen,\n"
            + "Room has items [Crepe Pan, Sharp Knife]\n"
            + "and other players in room [Human1].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Parlor has items [] and it has players []\n"
            + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] and it has players []\n"
            + "]\n"
            + "",
        world.playerLookAround());
  }

  /**
   * test look around the space when no player is in same room.
   */
  @Test
  public void testLookAroundWithNoPlayerInCurrentRoom() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human2", 2, "Armory");
    assertEquals(
        "Player Human is in room Kitchen,\n"
        + "Room has items [Crepe Pan, Sharp Knife]\n"
        + "and other players in room [].\n"
        + "Player's neighbor space information "
        + "[Room Dining Hall has items [] and it has players []\n"
        + ", Room Parlor has items [] and it has players []\n"
        + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] and it has players []\n"
        + "]\n"
        + "",
        world.playerLookAround());
  }

  /**
   * test look around the space when more than one player is in same room.
   */
  @Test
  public void testLookAroundWithMorePlayerInCurrentRoom() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human2", 2, "Kitchen");
    world.addHumanPlayer("Human1", 2, "Kitchen");
    assertEquals(
        "Player Human is in room Kitchen,\n"
        + "Room has items [Crepe Pan, Sharp Knife]\n"
        + "and other players in room [Human2, Human1].\n"
        + "Player's neighbor space information "
        + "[Room Dining Hall has items [] and it has players []\n"
        + ", Room Parlor has items [] and it has players []\n"
        + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] and it has players []\n"
        + "]\n"
        + "",
        world.playerLookAround());
  }

  /**
   * test look around the space when no items is in current room.
   */
  @Test
  public void testLookAroundWithNoItemInCurrentRoom() {
    world.addHumanPlayer("Human", 1, "Parlor");
    assertEquals("Player Human is in room Parlor,\n"
            + "Room has items []\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Kitchen has items [Crepe Pan, Sharp Knife] and it has players []\n"
            + ", Room Servants' Quarters has items [Broom Stick] and it has players []\n"
            + ", Room Tennessee Room has items [] and it has players []\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test look around the space when one item is in current room.
   */
  @Test
  public void testLookAroundOneItemInCurrentRoom() {
    world.addHumanPlayer("Human", 1, "Armory");
    assertEquals("Player Human is in room Armory,\n"
            + "Room has items [Revolver]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Billiard Room has items [Billiard Cue] "
            + "and it has players []\n"
            + ", Room Dining Hall has items [] and it has players []\n"
            + ", Room Drawing Room has items [Letter Opener] and it has players []\n"
            + "]\n"
            + "",
        world.playerLookAround());
  }

  /**
   * test look around the space when items are in current room.
   */
  @Test
  public void testLookAroundItemsInCurrentRoom() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    assertEquals("Player Human is in room Kitchen,\n"
            + "Room has items [Crepe Pan, Sharp Knife]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Parlor has items [] and it has players []\n"
            + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] "
            + "and it has players []\n"
            + "]\n", world.playerLookAround());
  }

  /**
   * test look around the space when no player is in neighbor rooms.
   */
  @Test
  public void testLookAroundNoPlayerInNeighbours() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    assertEquals("Player Human is in room Kitchen,\n"
            + "Room has items [Crepe Pan, Sharp Knife]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Parlor has items [] and it has players []\n"
            + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] and it has players []\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test look around the space when a player is in neighbor rooms.
   */
  @Test
  public void testLookAroundSinglePlayerInNeighbours() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("venky", 1, "Kitchen");
    assertEquals("Player Human is in room Kitchen,\n"
            + "Room has items [Crepe Pan, Sharp Knife]\n"
            + "and other players in room [venky].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Parlor has items [] and it has players []\n"
            + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] and it has players []\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test look around the space when players are in neighbor rooms.
   */
  @Test
  public void testLookAroundPlayersInNeighbours() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human1", 2, "Dining Hall");
    world.addHumanPlayer("Human2", 3, "Parlor");
    world.addHumanPlayer("Human3", 2, "Wine Cellar");
    assertEquals("Player Human is in room Kitchen,\n"
            + "Room has items [Crepe Pan, Sharp Knife]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players [Human1]\n"
            + ", Room Parlor has items [] and it has players [Human2]\n"
            + ", Room Wine Cellar has items "
            + "[Rat Poison, Piece of Rope] and it has players [Human3]\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test look around the space when items are in neighbor rooms.
   */
  @Test
  public void testLookAroundItemsInNeighbours() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    assertEquals("Player Human is in room Kitchen,\n"
            + "Room has items [Crepe Pan, Sharp Knife]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Parlor has items [] and it has players []\n"
            + ", Room Wine Cellar has items [Rat Poison, Piece of Rope] and it has players []\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test if user is provided with information at the start of the turn.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testUserInformationAtBeginingTurn() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Venky", 2, "Parlor");
    assertEquals("player Human is in room Kitchen and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Crepe Pan");
    assertEquals("player Venky is in room Parlor and carrying [].\n"
        + "Target is in room Billiard Room and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
  }

  /**
   * test space information when pet is in the room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSpaceInfoWithPet() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Venky", 2, "Parlor");
    assertEquals("Room Armory in  Doctor Lucky's Mansion has items : [Revolver],\n"
            + "its neighbours are : [Billiard Room, Dining Hall, Drawing Room],\n"
            + "it has players []", world.getSpaceInformation("Armory"));
    assertEquals("player Human is in room Kitchen and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
  }

  /**
   * test space information when pet is not in same room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testSpaceInfoWithoutPet() {
    world.addHumanPlayer("Human", 1, "Kitchen");
    world.addHumanPlayer("Human1", 2, "Parlor");
    assertEquals("Room Kitchen in  Doctor Lucky's Mansion has items : [Crepe Pan, Sharp Knife],\n"
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],\n"
            + "it has players [Human]", world.getSpaceInformation("Kitchen"));
    assertEquals("player Human is in room Kitchen and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
  }

  /**
   * test if the pet room is visible to another player if looking around.
   */
  @Test
  public void testPetRoomVisibility() {
    world.addHumanPlayer("Human", 5, "Armory");
    world.addHumanPlayer("Human1", 2, "Billiard Room");
    world.movePet("Armory");
    assertEquals(
        "Room Armory in  Doctor Lucky's Mansion has items : [Revolver],\n"
        + "its neighbours are : [Billiard Room, Dining Hall, Drawing Room],\n"
        + "it has players [Human]",
        world.getSpaceInformation("Armory"));
    assertEquals("Player Human1 is in room Billiard Room,\n"
            + "Room has items [Billiard Cue]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Trophy Room has items [Duck Decoy, Monkey Hand]"
            + " and it has players []\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test if the pet room and players in that room is visible to another player if
   * looking around.
   */
  @Test
  public void testPetPlayerVisibility() {
    world.addHumanPlayer("Human", 5, "Armory");
    world.addHumanPlayer("Human1", 2, "Billiard Room");
    world.movePet("Armory");
    assertEquals(
        "Room Armory in  Doctor Lucky's Mansion has items : [Revolver],\n"
        + "its neighbours are : [Billiard Room, Dining Hall, Drawing Room],\n"
        + "it has players [Human]",
        world.getSpaceInformation("Armory"));
    assertEquals("Player Human1 is in room Billiard Room,\n"
            + "Room has items [Billiard Cue]\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Dining Hall has items [] and it has players []\n"
            + ", Room Trophy Room has items [Duck Decoy, Monkey Hand] and it has players []\n"
            + "]\n"
            + "", world.playerLookAround());
  }

  /**
   * test move pet to invalid space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMovePetToInvalidSpace() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    world.movePet("asd");
  }

  /**
   * test move pet to no space.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMovePetToNoSpace() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    world.movePet(null);
  }

  /**
   * test move pet to valid space.
   */
  @Test
  public void testMovePet() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    world.movePet("Parlor");
  }

  /**
   * test attack target when player does not have any items, i.e, checking if
   * player is poking target
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithoutItem() {
    world.addHumanPlayer("Human", 5, "Armory");
    assertEquals("Target attack is successful by poking him in the eye "
            + "and Target health decreased by 1", world.pokeTheTargetCharacter());
    assertEquals(
        "Target attack is successful by poking him in the eye and Target health decreased by 1",
        world.pokeTheTargetCharacter());
    assertEquals("player Human is in room Armory and carrying [].\n"
        + "Target is in room Billiard Room and Targets health's is 3.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
  }

  /**
   * test attack target when player has an item, i.e, checking if player is able
   * to choose the item to attack target.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTarget() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
    world.playerPickItems("Chain Saw");
    assertEquals("player Human is in room Carriage House and carrying "
        + "[Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack is successfull and Target health decreased by 4",
        world.attackTarget("Chain Saw"));
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Dining Hall and Targets health's is 0.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
  }

  /**
   * test whether item is removed after player attempts to attack the target.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testItemRemoveAfterAttackTarget() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
    world.playerPickItems("Chain Saw");
    assertEquals("player Human is in room Carriage House and carrying items [Chain Saw]",
        world.getPlayerDescription());
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack is successfull and Target health decreased by 4",
        world.attackTarget("Chain Saw"));
    assertEquals("player Human is in room Carriage House and carrying items []",
        world.getPlayerDescription());
  }

  /**
   * test whether item is removed after player attempts to attack the target,
   * though the attack is not successfull.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testItemRemoveAfterUnsuccessfulAttackTarget() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Chain Saw");
    assertEquals("player Human is in room Carriage House and carrying items [Chain Saw]",
        world.getPlayerDescription());
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Billiard Room and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessful, as target is not in player's room",
        world.attackTarget("Chain Saw"));
    assertEquals("player Human is in room Carriage House and carrying items []",
        world.getPlayerDescription());
  }

  /**
   * test attack target when another player is in the same room as current player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithAnotherPlayerinSameRoom() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.addHumanPlayer("Venky", 2, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Chain Saw");
    world.movePet("Kitchen");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessfull, as player can be seen by other players",
        world.attackTarget("Chain Saw"));
  }

  /**
   * test attack target when another player is in the neighbor room of current
   * player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithAnotherPlayerinNeighbour() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.addHumanPlayer("Venky", 2, "Winter Garden");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Chain Saw");
    world.movePet("Kitchen");
    assertEquals("player Human is in room Carriage House and carrying "
        + "[Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessfull, as player can be seen by other players",
        world.attackTarget("Chain Saw"));
  }

  /**
   * test attack target when target is not in same room as player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithTargetinAnotherRoom() {
    world.addHumanPlayer("Human", 5, "Kitchen");
    assertEquals("player Human is in room Kitchen and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Crepe Pan");
    assertEquals("Target attack unsuccessfull, as target is not in player's room",
        world.attackTarget("Crepe Pan"));
  }

  /**
   * test attack target when pet is in neighbor room of current player.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithPetinNeighborRoom() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.addHumanPlayer("Venky", 2, "Winter Garden");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Chain Saw");
    assertEquals("player Venky is in room Winter Garden and carrying [].\n"
        + "Target is in room Billiard Room and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.movePet("Winter Garden");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessfull, as player can be seen by other players",
        world.attackTarget("Chain Saw"));
  }

  /**
   * test attack target when pet is in same room of current player.
   */
  @Test
  public void testAttackTargetWithPetInSameRoom() {
    world.addHumanPlayer("Human", 5, "Drawing Room");
    world.addHumanPlayer("Venky", 2, "Armory");
    world.playerPickItems("Letter Opener");
    world.playerLookAround();
    world.playerLookAround();
    world.movePet("Drawing Room");
    assertEquals("Target attack is successful and Target health decreased by 2",
        world.attackTarget("Letter Opener"));
  }

  /**
   * test attack target when peet is present in neighbor room.
   */
  @Test(expected = IllegalStateException.class)
  public void testAttackWhenPetIsPresentInNeighborRoom() {
    world.addHumanPlayer("Human", 5, "Drawing Room");
    world.addHumanPlayer("Venky", 2, "Foyer");
    world.playerPickItems("Letter Opener");
    world.playerLookAround();
    world.playerLookAround();
    world.playerLookAround();
    assertEquals(
        "Room Foyer in  Doctor Lucky's Mansion has items : [],\n"
            + "its neighbours are : [Drawing Room, Piazza],\n" + "it has players [Venky]",
        world.getSpaceInformation("Foyer"));
    assertEquals("Target attack unsuccessfull, as player can be seen by " + "other players",
        world.attackTarget("Letter Opener"));
  }

  /**
   * test attack target with an invalid item name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithInvalidItem() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.attackTarget("aasfs");
  }

  /**
   * test attack target with a null item name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithNullItem() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.attackTarget(null);
  }

  /**
   * test attack target with an item player does not have.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithItemPlayerDoesNotHave() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.attackTarget("Sharpe Knife");
  }

  /**
   * test whether player can be seen by other players in same room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerVisibilityWithPlayersinSameRoom() {
    world.addHumanPlayer("Human", 5, "Dining Hall");
    world.addHumanPlayer("Venky", 2, "Dining Hall");
    world.addHumanPlayer("Naveen", 3, "Dining Hall");
    assertEquals("player Human is in room Dining Hall and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.movePet("Dining Hall");
    world.playerLookAround();
    world.playerLookAround();
    assertEquals("player Human is in room Dining Hall and carrying [].\n"
        + "Target is in room Dining Hall and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessfull, as player can be seen by other players",
        world.pokeTheTargetCharacter());
  }

  /**
   * test whether player can be seen by other players, when pet is in same room.
   */
  @Test
  public void testPlayerVisibilityWithPetInSameRoom() {
    world.addHumanPlayer("Human", 5, "Armory");
    world.addHumanPlayer("Human1", 2, "Foyer");
    world.movePet("Armory");
    assertEquals("Player Human1 is in room Foyer,\n"
            + "Room has items []\n"
            + "and other players in room [].\n"
            + "Player's neighbor space information "
            + "[Room Drawing Room has items [Letter Opener] and it has players []\n"
            + ", Room Piazza has items [Civil War Cannon] and it has players []\n"
            + "]\n"
            + "",
        world.playerLookAround());
  }

  /**
   * test whether player can be seen by other players in neighbor room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerVisibilityWithAnotherPlayerinNeighbour() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.addHumanPlayer("Venky", 2, "Winter Garden");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Chain Saw");
    world.movePet("Kitchen");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessfull, as player can be seen by other players",
        world.attackTarget("Chain Saw"));
  }

  /**
   * test whether player can be seen by other players in neighbor room and pet in
   * neighbor room.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerVisibilityWithPetinNeighborRoom() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    world.addHumanPlayer("Venky", 2, "Winter Garden");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerPickItems("Chain Saw");
    assertEquals("player Venky is in room Winter Garden and carrying [].\n"
        + "Target is in room Billiard Room and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.movePet("Winter Garden");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack unsuccessfull, as player can be seen by other players",
        world.attackTarget("Chain Saw"));
  }

  /**
   * test if player wins after successfully killing target.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerWins() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
    world.playerPickItems("Chain Saw");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack is successfull and Target health decreased by 4",
        world.attackTarget("Chain Saw"));
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Dining Hall and Targets health's is 0.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Player Human killed the target and he is the winner.", world.getWinner());
  }

  /**
   * test if player wins after he fails to kill target.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoPlayerWins() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
    world.playerLookAround();
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals(
        "Target attack is successfull by poking him in the eye and Target health decreased by 1",
        world.pokeTheTargetCharacter());
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Dining Hall and Targets health's is 3.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target is not killed and no one wins the game.", world.getWinner());
  }

  /**
   * test if game is over when target is dead.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIsGameOver() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
    world.playerPickItems("Chain Saw");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Target attack is successfull and Target health decreased by 4",
        world.attackTarget("Chain Saw"));
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Dining Hall and Targets health's is 0.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertEquals("Player Human killed the target and he is the winner.", world.getWinner());
    assertFalse(world.isGameOver());
  }

  /**
   * test if game is not over, when target is not dead and player still have
   * turns.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIsGameNotOver() {
    world.addHumanPlayer("Human", 5, "Carriage House");
    assertEquals("player Human is in room Carriage House and carrying [].\n"
        + "Target is in room Armory and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    world.playerLookAround();
    world.playerPickItems("Chain Saw");
    assertEquals("player Human is in room Carriage House and carrying"
        + " [Item Chain Saw with damage power 4].\n"
        + "Target is in room Carriage House and Targets health's is 4.\n"
        + "Pet is not in the current player's room.", world.pokeTheTargetCharacter());
    assertTrue(world.isGameOver());
  }

  /**
   * test move pet through depth first search traversal around the world.
   */
  @Test
  public void testMovePetDfs() {
    world.addHumanPlayer("Human", 5, "Drawing Room");
    world.playerLookAround();
    assertEquals(
        "Room Billiard Room in  Doctor Lucky's Mansion has items : [Billiard Cue],\n"
        + "its neighbours are : [Armory, Dining Hall, Trophy Room],\n"
        + "it has players []",
        world.getSpaceInformation("Billiard Room"));
    world.playerLookAround();
    assertEquals(
        "Room Dining Hall in  Doctor Lucky's Mansion has items : [],\n"
        + "its neighbours are : [Armory, Billiard Room, Drawing Room,"
        + " Kitchen, Parlor, Tennessee Room, Trophy Room, Wine Cellar],\n"
        + "it has players []",
        world.getSpaceInformation("Dining Hall"));
    world.playerLookAround();
    world.movePet("Billiard Room");
    assertEquals("Room Billiard Room in  Doctor Lucky's Mansion has items : [Billiard Cue],\n"
            + "its neighbours are : [Armory, Dining Hall, Trophy Room],\n"
            + "it has players []", world.getSpaceInformation("Billiard Room"));
    world.playerLookAround();
    assertEquals("Room Armory in  Doctor Lucky's Mansion has items : [Revolver],\n"
            + "its neighbours are : [Billiard Room, Dining Hall, Drawing Room],\n"
            + "it has players []", world.getSpaceInformation("Armory"));
    world.playerLookAround();
    assertEquals(
        "Room Dining Hall in  Doctor Lucky's Mansion has items : [],\n"
        + "its neighbours are : [Armory, Billiard Room, Drawing Room,"
        + " Kitchen, Parlor, Tennessee Room, Trophy Room, Wine Cellar],\n"
        + "it has players []",
        world.getSpaceInformation("Dining Hall"));
  }
}
