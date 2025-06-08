package mansion;

import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This is the TheWorldTest class.
 */
public class TheWorldTest {
  private GameControls world;
  private File exampleRunFile;
  private StringBuilder out;
  
  /**
   * Initializing values.
   */
  @Before
  public void setUp() {
    this.exampleRunFile = new File("res/ExampleRun.txt");
    this.out = new StringBuilder();
    this.out.append("36 30 Doctor Lucky's Mansion\n");
    this.out.append("4 Doctor Lucky\n");
    this.out.append("Fortune the Cat\n");
    this.out.append("21\n");
    this.out.append("22 19 23 26 Armory\n");
    this.out.append("16 21 21 28 Billiard Room\n");
    this.out.append("28  0 35  5 Carriage House\n");
    this.out.append("12 11 21 20 Dining Hall\n");
    this.out.append("22 13 25 18 Drawing Room\n");
    this.out.append("26 13 27 18 Foyer\n");
    this.out.append("28 26 35 29 Green House\n");
    this.out.append("30 20 35 25 Hedge Maze\n");
    this.out.append("16  3 21 10 Kitchen\n");
    this.out.append(" 0  3  5  8 Lancaster Room\n");
    this.out.append(" 4 23  9 28 Library\n");
    this.out.append(" 2  9  7 14 Lilac Room\n");
    this.out.append(" 2 15  7 22 Master Suite\n");
    this.out.append(" 0 23  3 28 Nursery\n");
    this.out.append("10  5 15 10 Parlor\n");
    this.out.append("28 12 35 19 Piazza\n");
    this.out.append(" 6  3  9  8 Servants' Quarters\n");
    this.out.append(" 8 11 11 20 Tennessee Room\n");
    this.out.append("10 21 15 26 Trophy Room\n");
    this.out.append("22  5 23 12 Wine Cellar\n");
    this.out.append("30  6 35 11 Winter Garden\n");
    this.out.append("20\n");
    this.out.append("8 3 Crepe Pan\n");
    this.out.append("4 2 Letter Opener\n");
    this.out.append("12 2 Shoe Horn\n");
    this.out.append("8 3 Sharp Knife\n");
    this.out.append("0 3 Revolver\n");
    this.out.append("15 3 Civil War Cannon\n");
    this.out.append("2 4 Chain Saw\n");
    this.out.append("16 2 Broom Stick\n");
    this.out.append("1 2 Billiard Cue\n");
    this.out.append("19 2 Rat Poison\n");
    this.out.append("6 2 Trowel\n");
    this.out.append("2 4 Big Red Hammer\n");
    this.out.append("6 2 Pinking Shears\n");
    this.out.append("18 3 Duck Decoy\n");
    this.out.append("13 2 Bad Cream\n");
    this.out.append("18 2 Monkey Hand\n");
    this.out.append("11 2 Tight Hat\n");
    this.out.append("19 2 Piece of Rope\n");
    this.out.append("9 3 Silken Cord\n");
    this.out.append("7 2 Loud Noise");
    Reader input = new StringReader(this.out.toString());
    this.world = (GameControls) new TheWorld(input, (RandomInterface) new RandomImpl(), 5);
  }
  
  @Test
  public void testGenerateGraphicalRepresesntaion() {
    Assert.assertTrue((new File("res", "image.png")).exists());
  }
  
  @Test
  public void testExampleRunFile() {
    Assert.assertTrue((this.exampleRunFile.length() != 0L));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSpaceInfo() {
    this.world.getSpaceInformation(null);
  }
  
  @Test
  public void testSpaceInfoWithNoPlayer() {
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion " 
          + "has items : [Crepe Pan, Sharp Knife]," 
          + " its neighbours are : [Dining Hall, Parlor, Wine Cellar]," 
          + " it has players [] and Pet is not in room Kitchen",

          this.world.getSpaceInformation("Kitchen"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testSpaceInfoWithNullPlayer() {
    this.world.getSpaceInformation(null);
  }
  
  @Test
  public void testSpaceInfoWithOnePlayer() {
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion "
          + "has items : [Crepe Pan, Sharp Knife],"
          + " its neighbours are : [Dining Hall, Parlor, Wine Cellar],"
          + " it has players [] and Pet is not in room Kitchen", 
        this.world.getSpaceInformation("Kitchen"));
    this.world.addHumanPlayer("Human", 3, "Kitchen");
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion" 
          + " has items : [Crepe Pan, Sharp Knife],"
          + " its neighbours are : [Dining Hall, Parlor, Wine Cellar],"
          + " it has players [Human] and Pet is not in room Kitchen", 
        
        this.world.getSpaceInformation("Kitchen"));
  }
  
  @Test
  public void testSpaceInfoMultiplePlayer() {
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion has"
          + " items : [Crepe Pan, Sharp Knife], " 
          + "its neighbours are : [Dining Hall, Parlor, Wine Cellar]," 
          + " it has players [] and Pet is not in room Kitchen",

          this.world.getSpaceInformation("Kitchen"));
    this.world.addHumanPlayer("Human", 3, "Kitchen");
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion has" 
          + " items : [Crepe Pan, Sharp Knife], its neighbours are :" 
          + " [Dining Hall, Parlor, Wine Cellar]," 
          + " it has players [Human] and Pet is not in room Kitchen",
          this.world.getSpaceInformation("Kitchen"));
    this.world.addHumanPlayer("Human1", 5, "Kitchen");
    Assert.assertEquals(
          "Room Kitchen in  Doctor Lucky's Mansion has" 
          + " items : [Crepe Pan, Sharp Knife], " 
          + "its neighbours are : [Dining Hall, Parlor, Wine Cellar]," 
          + " it has players [Human, Human1] and Pet is not in room Kitchen",

          this.world.getSpaceInformation("Kitchen"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerLimitAdded() {
    this.world.addHumanPlayer("Human", -3, "Kitchen");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerRoomNameAdded() {
    this.world.addHumanPlayer("Human", 3, "asd");
  }
  
  @Test
  public void testSinglePlayerAdded() {
    this.world.addHumanPlayer("Human", 3, "Kitchen");
  }
  
  @Test
  public void testSingleComputerPlayerAdded() {
    this.world.addComputerPlayer();
  }
  
  @Test
  public void testMultiplePlayerAdded() {
    this.world.addHumanPlayer("Human", 3, "Kitchen");
    this.world.addHumanPlayer("Human1", 5, "Parlor");
    this.world.addComputerPlayer();
    this.world.addHumanPlayer("Human2", 5, "Parlor");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testDuplicatePlayerAdded() {
    this.world.addHumanPlayer("Human", 5, "Parlor");
    this.world.addHumanPlayer("Human", 5, "Parlor");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerToInvalidSpace() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePlayer("asd");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerToNoSpace() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePlayer(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerToCurrentSpace() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePlayer("Kitchen");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMovePlayerToNonNeighborSpace() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePlayer("Foyer");
  }
  
  @Test
  public void testMovePlayerToNeighborSpace() {
    this.world.addHumanPlayer("Human", 5, "Parlor");
    this.world.movePlayer("Kitchen");
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion"
            + " has items : [Crepe Pan, Sharp Knife], its neighbours "
            + "are : [Dining Hall, Parlor, Wine Cellar], "
            + "it has players [Human] and Pet is not in room Kitchen", 
        
        this.world.getSpaceInformation("Kitchen"));
  }
  
  @Test
  public void testPlayerPickItem() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's Mansion "
            + "has items : [Crepe Pan, Sharp Knife], "
            + "its neighbours are : [Dining Hall, Parlor, Wine Cellar],"
            + " it has players [Human] and Pet is not in room Kitchen", 
        
        this.world.getSpaceInformation("Kitchen"));
    this.world.playerPickItems("Crepe Pan");
    Assert.assertEquals(
        "Room Kitchen in  Doctor Lucky's Mansion has items :"
        + " [Sharp Knife], its neighbours are : [Dining Hall,"
        + " Parlor, Wine Cellar], it has players [Human] "
        + "and Pet is not in room Kitchen", 
        
        this.world.getSpaceInformation("Kitchen"));
    Assert.assertEquals("player Human is in room Kitchen and carrying items [Crepe Pan]", 
        this.world.getPlayerDescription("Human"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickItemNotInRoom() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.playerPickItems("Trowel");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickInvalidItem() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.playerPickItems("asd");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickNullItem() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.playerPickItems(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerPickItemBeyondLimit() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.playerPickItems("Crepe Pan");
    this.world.playerPickItems("Sharpe Knife");
  }
  
  @Test(expected = IllegalStateException.class)
  public void testPlayerPickItemInEmptyRoom() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    this.world.playerPickItems("Crepe Pan");
  }
  
  @Test
  public void testPlayerLookAround() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    Assert.assertEquals("Player Human is in room Parlor, "
            + "has items [] and other players in room []. "
            + "Player's neighbor space information [Room Dining Hall "
            + "has items [] and it has players [], Room Kitchen "
            + "has items [Crepe Pan, Sharp Knife] and it has players [], "
            + "Room Servants' Quarters has items [Broom Stick] and "
            + "it has players [], Room Tennessee Room has items [] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testPlayerInfo() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    Assert.assertEquals("player Human is in room Parlor and carrying items []", 
        this.world.getPlayerDescription("Human"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerInfoInvalidName() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    Assert.assertEquals("player name Human is in room Parlor and carrying items []", 
        this.world.getPlayerDescription("asd"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerInfoNullName() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    Assert.assertEquals("player name Human is in room Parlor and carrying items []", 
        this.world.getPlayerDescription(null));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testPlayerInfoWithoutPlayer() {
    Assert.assertEquals("player name Human is in room Parlor and carrying items []", 
        this.world.getPlayerDescription("asd"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testLookAroundWithoutPlayer() {
    Assert.assertEquals(
        "Player Human is in room Parlor and has neighbors "
        + "[Dining Hall, Kitchen, Servants' Quarters, Tennessee Room]", 
        
        this.world.playerLookAround());
  }
  
  @Test(expected = IllegalStateException.class)
  public void testMovePlayerWithoutPlayer() {
    this.world.movePlayer("Foyer");
  }
  
  @Test(expected = IllegalStateException.class)
  public void testPickItemWithoutPlayer() {
    this.world.playerPickItems("Crepe Pan");
  }
  
  @Test
  public void testComputerPlayer() {
    this.world.addComputerPlayer();
    Assert.assertTrue(this.world.checkComputerPlayer());
  }
  
  @Test
  public void testHumanPlayer() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    Assert.assertFalse(this.world.checkComputerPlayer());
  }
  
  @Test
  public void testPlayerTurnForLookAround() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    this.world.addHumanPlayer("Human1", 2, "Kitchen");
    this.world.addHumanPlayer("Human2", 3, "Dining Hall");
    Assert.assertEquals("Human", this.world.getPlayerNameTurn());
    this.world.playerLookAround();
    Assert.assertEquals("Human1", this.world.getPlayerNameTurn());
    this.world.playerLookAround();
    Assert.assertEquals("Human2", this.world.getPlayerNameTurn());
    this.world.playerLookAround();
    Assert.assertEquals("Human", this.world.getPlayerNameTurn());
  }
  
  @Test
  public void testPlayerTurnForMove() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    this.world.addHumanPlayer("Human1", 2, "Kitchen");
    this.world.addHumanPlayer("Human2", 3, "Dining Hall");
    Assert.assertEquals("Human", this.world.getPlayerNameTurn());
    this.world.movePlayer("Dining Hall");
    Assert.assertEquals("Human1", this.world.getPlayerNameTurn());
    this.world.movePlayer("Parlor");
    Assert.assertEquals("Human2", this.world.getPlayerNameTurn());
    this.world.movePlayer("Kitchen");
    Assert.assertEquals("Human", this.world.getPlayerNameTurn());
  }
  
  @Test
  public void testPlayerTurnForItemPick() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Parlor");
    Assert.assertEquals("Human", this.world.getPlayerNameTurn());
    this.world.playerPickItems("Crepe Pan");
    Assert.assertEquals("Human1", this.world.getPlayerNameTurn());
  }
  
  @Test
  public void testTargetLocation() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Parlor");
    Assert.assertEquals("player Human is in room Kitchen "
            + "and carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    Assert.assertEquals("player Human1 is in room Parlor and carrying [].\n"
            + "Target is in room Billiard Room and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
  }
  
  @Test
  public void testLookAroundWithOnePlayerInCurrentRoom() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Kitchen");
    Assert.assertEquals("Player Human is in room Kitchen,"
            + " has items [Crepe Pan, Sharp Knife] and"
            + " other players in room [Human1]. Player's neighbor "
            + "space information [Room Dining Hall has items []"
            + " and it has players [], Room Parlor has items [] "
            + "and it has players [], Room Wine Cellar has "
            + "items [Rat Poison, Piece of Rope] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundWithNoPlayerInCurrentRoom() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Armory");
    Assert.assertEquals("Player Human is in room Kitchen,"
            + " has items [Crepe Pan, Sharp Knife] and"
            + " other players in room []. Player's neighbor space"
            + " information [Room Dining Hall has items [] and"
            + " it has players [], Room Parlor has items [] and"
            + " it has players [], Room Wine Cellar "
            + "has items [Rat Poison, Piece of Rope] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundWithMorePlayerInCurrentRoom() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Kitchen");
    this.world.addHumanPlayer("Human2", 2, "Kitchen");
    Assert.assertEquals(
        "Player Human is in room Kitchen,"
        + " has items [Crepe Pan, Sharp Knife] and other players in room"
        + " [Human1, Human2]. Player's neighbor space information"
        + " [Room Dining Hall has items [] and it has players [],"
        + " Room Parlor has items [] and it has players [],"
        + " Room Wine Cellar has items [Rat Poison, Piece of Rope] "
        + "and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundWithNoItemInCurrentRoom() {
    this.world.addHumanPlayer("Human", 1, "Parlor");
    Assert.assertEquals("Player Human is in room Parlor, "
            + "has items [] and other players in room []. "
            + "Player's neighbor space information [Room Dining Hall "
            + "has items [] and it has players [], Room Kitchen has "
            + "items [Crepe Pan, Sharp Knife] and it has players [],"
            + " Room Servants' Quarters has items [Broom Stick] and it "
            + "has players [], Room Tennessee Room has items [] "
            + "and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundOneItemInCurrentRoom() {
    this.world.addHumanPlayer("Human", 1, "Armory");
    Assert.assertEquals("Player Human is in room Armory,"
            + " has items [Revolver] and other players in room []."
            + " Player's neighbor space information [Room Billiard Room "
            + "has items [Billiard Cue] and it has players [], Room Dining "
            + "Hall has items [] and it has players [], "
            + "Room Drawing Room has items [Letter Opener] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundItemsInCurrentRoom() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    Assert.assertEquals("Player Human is in room Kitchen,"
            + " has items [Crepe Pan, Sharp Knife] and"
            + " other players in room []. Player's neighbor space"
            + " information [Room Dining Hall has items [] and it"
            + " has players [], Room Parlor has items [] and "
            + "it has players [], Room Wine Cellar has "
            + "items [Rat Poison, Piece of Rope] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundNoPlayerInNeighbours() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    Assert.assertEquals("Player Human is in room Kitchen,"
            + " has items [Crepe Pan, Sharp Knife] and"
            + " other players in room []. Player's neighbor"
            + " space information [Room Dining Hall has items []"
            + " and it has players [], Room Parlor has items []"
            + " and it has players [], Room Wine Cellar has items"
            + " [Rat Poison, Piece of Rope] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundSinglePlayerInNeighbours() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 1, "Kitchen");
    Assert.assertEquals("Player Human is in room Kitchen, "
            + "has items [Crepe Pan, Sharp Knife] and"
            + " other players in room [Human1]. "
            + "Player's neighbor space information [Room Dining Hall has"
            + " items [] and it has players [], Room Parlor has items []"
            + " and it has players [], Room Wine Cellar has "
            + "items [Rat Poison, Piece of Rope] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundPlayersInNeighbours() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Dining Hall");
    this.world.addHumanPlayer("Human2", 3, "Parlor");
    this.world.addHumanPlayer("bala", 2, "Wine Cellar");
    Assert.assertEquals("Player Human is in room Kitchen,"
            + " has items [Crepe Pan, Sharp Knife] and"
            + " other players in room []. Player's neighbor space"
            + " information [Room Dining Hall has items []"
            + " and it has players [Human1], Room Parlor has items []"
            + " and it has players [Human2], Room Wine Cellar "
            + "has items [Rat Poison, Piece of Rope] and it has players [bala]]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testLookAroundItemsInNeighbours() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    Assert.assertEquals("Player Human is in room Kitchen,"
            + " has items [Crepe Pan, Sharp Knife] and"
            + " other players in room []. Player's neighbor"
            + " space information [Room Dining Hall has items []"
            + " and it has players [], Room Parlor has items [] "
            + "and it has players [], Room Wine Cellar has "
            + "items [Rat Poison, Piece of Rope] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test
  public void testUserInformationAtBeginingTurn() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Parlor");
    Assert.assertEquals("player Human is in room Kitchen"
            + " and carrying [].\nTarget is in room Armory"
            + " and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Crepe Pan");
    Assert.assertEquals("player Human1 is in room Parlor"
            + " and carrying [].\nTarget is in room Billiard Room"
            + " and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
  }
  
  @Test
  public void testSpaceInfoWithPet() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Parlor");
    Assert.assertEquals("Room Armory in  Doctor Lucky's Mansion "
            + "has items : [Revolver], its neighbours are : [Billiard Room,"
            + " Dining Hall, Drawing Room], it has players [] "
            + "and Pet is in room Armory", 
        
        this.world.getSpaceInformation("Armory"));
    Assert.assertEquals("player Human is in room Kitchen and carrying [].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
  }
  
  @Test
  public void testSpaceInfoWithoutPet() {
    this.world.addHumanPlayer("Human", 1, "Kitchen");
    this.world.addHumanPlayer("Human1", 2, "Parlor");
    Assert.assertEquals("Room Kitchen in  Doctor Lucky's "
            + "Mansion has items : [Crepe Pan, Sharp Knife], its neighbours "
            + "are : [Dining Hall, Parlor, Wine Cellar], it has "
            + "players [Human] and Pet is not in room Kitchen", 
        
        this.world.getSpaceInformation("Kitchen"));
    Assert.assertEquals("player Human is in room Kitchen and carrying [].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
  }
  
  @Test
  public void testPetRoomVisibility() {
    this.world.addHumanPlayer("Human", 5, "Armory");
    this.world.addHumanPlayer("Human1", 2, "Billiard Room");
    this.world.movePet("Armory");
    Assert.assertEquals(
        "Room Armory in  Doctor Lucky's Mansion has items :"
        + " [Revolver], its neighbours are : [Billiard Room, Dining Hall,"
        + " Drawing Room], it has players [Human] and Pet is in room Armory", 
        
        this.world.getSpaceInformation("Armory"));
    Assert.assertEquals("Player Human1 is in room Billiard Room,"
            + " has items [Billiard Cue] and other players in room []."
            + " Player's neighbor space information [Room Dining Hall "
            + "has items [] and it has players [], Room Trophy Room has "
            + "items [Duck Decoy, Monkey Hand] and it has players []]", 
        this.world.playerLookAround());
  }
  
  @Test
  public void testPetPlayerVisibility() {
    this.world.addHumanPlayer("Human", 5, "Armory");
    this.world.addHumanPlayer("Human1", 2, "Billiard Room");
    this.world.movePet("Armory");
    Assert.assertEquals(
        "Room Armory in  Doctor Lucky's Mansion has items : [Revolver], its neighbours are :"
        + " [Billiard Room, Dining Hall, Drawing Room], "
        + "it has players [Human] and Pet is in room Armory", 
        
        this.world.getSpaceInformation("Armory"));
    Assert.assertEquals("Player Human1 is in room Billiard Room,"
            + " has items [Billiard Cue] and other players in room []."
            + " Player's neighbor space information [Room Dining Hall has items []"
            + " and it has players [], Room Trophy Room has "
            + "items [Duck Decoy, Monkey Hand] and it has players []]", 
        
        this.world.playerLookAround());
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMovePetToInvalidSpace() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePet("asd");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testMovePetToNoSpace() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePet(null);
  }
  
  @Test
  public void testMovePet() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    this.world.movePet("Parlor");
  }
  
  @Test
  public void testAttackTargetWithoutItem() {
    this.world.addHumanPlayer("Human", 5, "Armory");
    Assert.assertEquals("player Human is in room Armory "
            + "and carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4.\n"
            + "Pet is in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals(
        "Target attack is successful by poking him in the eye and Target health decreased by 1", 
        this.world.attackTargetWithoutItems());
    Assert.assertEquals("player Human is in room Armory and"
            + " carrying [].\nTarget is in room Billiard Room and"
            + " Targets health's is 3.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
  }
  
  @Test
  public void testAttackTarget() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House and"
            + " carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack is successful and Target health decreased by 4", 
        this.world.attackTarget("Chain Saw"));
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [].\nTarget is in room Dining Hall "
            + "and Targets health's is 0.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
  }
  
  @Test
  public void testItemRemoveAfterAttackTarget() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House and"
            + " carrying [].\nTarget is in room Armory and"
            + " Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human is in room Carriage House and carrying items [Chain Saw]", 
        this.world.getPlayerDescription("Human"));
    Assert.assertEquals("player Human is in room Carriage House and "
            + "carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack is successful and Target health decreased by 4", 
        this.world.attackTarget("Chain Saw"));
    Assert.assertEquals("player Human is in room Carriage House and carrying items []", 
        this.world.getPlayerDescription("Human"));
  }
  
  @Test
  public void testItemRemoveAfterUnsuccessfulAttackTarget() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House and carrying [].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human is in room Carriage House and carrying items [Chain Saw]", 
        this.world.getPlayerDescription("Human"));
    Assert.assertEquals("player Human is in room Carriage House"
            + " and carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Billiard Room and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as target is not in player's room", 
        this.world.attackTarget("Chain Saw"));
    Assert.assertEquals("player Human is in room Carriage House and carrying items []", 
        this.world.getPlayerDescription("Human"));
  }
  
  @Test
  public void testAttackTargetWithAnotherPlayerinSameRoom() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.addHumanPlayer("Human1", 2, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Chain Saw");
    this.world.movePet("Kitchen");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Chain Saw"));
  }
  
  @Test
  public void testAttackTargetWithAnotherPlayerinNeighbour() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.addHumanPlayer("Human1", 2, "Winter Garden");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [].\nTarget is in room Armory and "
            + "Targets health's is 4.\nPet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Chain Saw");
    this.world.movePet("Kitchen");
    Assert.assertEquals("player Human is in room Carriage House and"
            + " carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Chain Saw"));
  }
  
  @Test
  public void testAttackTargetWithTargetinAnotherRoom() {
    this.world.addHumanPlayer("Human", 5, "Kitchen");
    Assert.assertEquals("player Human is in room Kitchen and"
            + " carrying [].\nTarget is in room Armory and"
            + " Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Crepe Pan");
    Assert.assertEquals("Target attack unsuccessful, as target is not in player's room", 
        this.world.attackTarget("Crepe Pan"));
  }
  
  @Test
  public void testAttackTargetWithPetinNeighborRoom() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.addHumanPlayer("Human1", 2, "Winter Garden");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human1 is in room Winter Garden"
            + " and carrying [].\nTarget is in room Billiard Room "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    this.world.movePet("Winter Garden");
    Assert.assertEquals("player Human is in room Carriage House and"
            + " carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Chain Saw"));
  }
  
  @Test
  public void testAttackTargetWithPetInSameRoom() {
    this.world.addHumanPlayer("Human", 5, "Drawing Room");
    this.world.addHumanPlayer("Human1", 2, "Armory");
    this.world.playerPickItems("Letter Opener");
    this.world.playerLookAround();
    this.world.playerLookAround();
    this.world.movePet("Drawing Room");
    Assert.assertEquals("Target attack is successful and Target health decreased by 2", 
        this.world.attackTarget("Letter Opener"));
  }
  
  @Test
  public void testAttackTargetWithPetPlayersinSameRoom() {
    this.world.addHumanPlayer("Human", 5, "Drawing Room");
    this.world.addHumanPlayer("Human1", 2, "Drawing Room");
    this.world.playerPickItems("Letter Opener");
    this.world.playerLookAround();
    this.world.playerLookAround();
    this.world.movePet("Drawing Room");
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Letter Opener"));
  }
  
  @Test
  public void testAttackWhenPetIsPresentInNeighborRoom() {
    this.world.addHumanPlayer("Human", 5, "Drawing Room");
    this.world.addHumanPlayer("Human1", 2, "Foyer");
    this.world.playerPickItems("Letter Opener");
    this.world.playerLookAround();
    this.world.playerLookAround();
    this.world.playerLookAround();
    Assert.assertEquals("Room Foyer in  Doctor Lucky's Mansion "
            + "has items : [], its neighbours are : [Drawing Room, Piazza],"
            + " it has players [Human1] and Pet is in room Foyer", 
        this.world.getSpaceInformation("Foyer"));
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Letter Opener"));
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithInvalidItem() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.attackTarget("aasfs");
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithNullItem() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.attackTarget(null);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testAttackTargetWithItemPlayerDoesNotHave() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.attackTarget("Sharpe Knife");
  }
  
  @Test
  public void testPlayerVisibilityWithPlayersinSameRoom() {
    this.world.addHumanPlayer("Human", 5, "Dining Hall");
    this.world.addHumanPlayer("Human1", 2, "Dining Hall");
    this.world.addHumanPlayer("Human2", 3, "Dining Hall");
    Assert.assertEquals("player Human is in room Dining Hall "
            + "and carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.",  
        this.world.getHintsAboutWorld());
    this.world.movePet("Dining Hall");
    this.world.playerLookAround();
    this.world.playerLookAround();
    Assert.assertEquals("player Human is in room Dining Hall "
            + "and carrying [].\nTarget is in room Dining Hall "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTargetWithoutItems());
  }
  
  @Test
  public void testPlayerVisibilityWithPetInSameRoom() {
    this.world.addHumanPlayer("Human", 5, "Armory");
    this.world.addHumanPlayer("Human1", 2, "Foyer");
    this.world.movePet("Armory");
    Assert.assertEquals("Player Human1 is in room Foyer, "
            + "has items [] and other players in room []. "
            + "Player's neighbor space information [Room Drawing Room "
            + "has items [Letter Opener] and it has players [], "
            + "Room Piazza has items [Civil War Cannon] and it has players []]", 
        this.world.playerLookAround());
  }
  
  @Test
  public void testPlayerVisibilityWithAnotherPlayerinNeighbour() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.addHumanPlayer("Human1", 2, "Winter Garden");
    Assert.assertEquals("player Human is in room Carriage House and carrying [].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Chain Saw");
    this.world.movePet("Kitchen");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Chain Saw"));
  }
  
  @Test
  public void testPlayerVisibilityWithPetinNeighborRoom() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    this.world.addHumanPlayer("Human1", 2, "Winter Garden");
    Assert.assertEquals("player Human is in room Carriage House and carrying [].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human1 is in room Winter Garden and carrying [].\n"
            + "Target is in room Billiard Room and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.movePet("Winter Garden");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack unsuccessful, as player can be seen by other players", 
        this.world.attackTarget("Chain Saw"));
  }
  
  @Test
  public void testPlayerWins() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House and carrying [].\n"
            + "Target is in room Armory and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [Item Chain Saw with damage power 4].\n"
            + "Target is in room Carriage House and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack is successful and Target health decreased by 4", 
        this.world.attackTarget("Chain Saw"));
    Assert.assertEquals("player Human is in room Carriage House and carrying []."
            + "\nTarget is in room Dining Hall and Targets health's is 0."
            + "\nPet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Player Human killed the target and he is the winner.",
            this.world.getWinner());
  }
  
  @Test
  public void testNoPlayerWins() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [].\nTarget is in room Armory "
            + "and Targets health's is 4."
            + "\nPet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    this.world.playerLookAround();
    Assert.assertEquals("player Human is in room Carriage House "
            + "and carrying [].\nTarget is in room Carriage House "
            + "and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals(
        "Target attack is successful by poking him in the eye and Target health decreased by 1", 
        this.world.attackTargetWithoutItems());
    Assert.assertEquals("player Human is in room Carriage House and carrying [].\n"
            + "Target is in room Dining Hall and Targets health's is 3."
            + "\nPet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target is not killed and no one wins the game.", this.world.getWinner());
  }
  
  @Test
  public void testIsGameOver() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House and carrying []."
            + "\nTarget is in room Armory and Targets health's is 4."
            + "\nPet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human is in room Carriage House and"
            + " carrying [Item Chain Saw with damage power 4]."
            + "\nTarget is in room Carriage House and Targets health's is 4."
            + "\nPet is not in the current player's room.", 
        
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Target attack is successful and Target health decreased by 4", 
        this.world.attackTarget("Chain Saw"));
    Assert.assertEquals("player Human is in room Carriage House and"
            + " carrying [].\nTarget is in room Dining Hall and"
            + " Targets health's is 0.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertEquals("Player Human killed the target and he is the winner.",
            this.world.getWinner());
    Assert.assertFalse(this.world.isGameNotOver());
  }
  
  @Test
  public void testIsGameNotOver() {
    this.world.addHumanPlayer("Human", 5, "Carriage House");
    Assert.assertEquals("player Human is in room Carriage House"
            + " and carrying [].\nTarget is in room Armory"
            + " and Targets health's is 4.\n"
            + "Pet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    this.world.playerLookAround();
    this.world.playerPickItems("Chain Saw");
    Assert.assertEquals("player Human is in room Carriage House"
            + " and carrying [Item Chain Saw with damage power 4]."
            + "\nTarget is in room Carriage House and Targets health's is 4."
            + "\nPet is not in the current player's room.", 
        this.world.getHintsAboutWorld());
    Assert.assertTrue(this.world.isGameNotOver());
  }
  
  @Test
  public void testMovePetDfs() {
    this.world.addHumanPlayer("Human", 5, "Drawing Room");
    this.world.playerLookAround();
    Assert.assertEquals(
        "Room Billiard Room in  Doctor Lucky's Mansion has items : "
        + "[Billiard Cue], its neighbours are : [Armory, Dining Hall, Trophy Room], "
        + "it has players [] and Pet is in room Billiard Room", 
        this.world.getSpaceInformation("Billiard Room"));
    this.world.playerLookAround();
    Assert.assertEquals(
        "Room Dining Hall in  Doctor Lucky's Mansion has items "
        + ": [], its neighbours are : [Armory, Billiard Room, Drawing Room, "
        + "Kitchen, Parlor, Tennessee Room, Trophy Room, Wine Cellar], "
        + "it has players [] and Pet is in room Dining Hall", 
        this.world.getSpaceInformation("Dining Hall"));
    this.world.playerLookAround();
    this.world.movePet("Billiard Room");
    Assert.assertEquals("Room Billiard Room in  Doctor Lucky's Mansion "
            + "has items : [Billiard Cue], its neighbours are : [Armory, "
            + "Dining Hall, Trophy Room], it has players [] "
            + "and Pet is in room Billiard Room", 
        this.world.getSpaceInformation("Billiard Room"));
    this.world.playerLookAround();
    Assert.assertEquals("Room Armory in  Doctor Lucky's Mansion "
            + "has items : [Revolver], its neighbours are : [Billiard Room, "
            + "Dining Hall, Drawing Room], it has players [] "
            + "and Pet is in room Armory", 
        this.world.getSpaceInformation("Armory"));
    this.world.playerLookAround();
    Assert.assertEquals(
        "Room Dining Hall in  Doctor Lucky's Mansion has items : [],"
        + " its neighbours are : [Armory, Billiard Room, Drawing Room, Kitchen, Parlor,"
        + " Tennessee Room, Trophy Room, Wine Cellar], it has players [] "
        + "and Pet is in room Dining Hall", 
        
        this.world.getSpaceInformation("Dining Hall"));
  }
}
