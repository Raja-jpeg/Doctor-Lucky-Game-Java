package milestone;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * This is the controller class.
 *
 */
public class Controller {
  static int roomIndex = 0;
  Scanner scanner = new Scanner(System.in);
  Players player = new Players();
  List<Players> players = new ArrayList<>(); 
  List<Players> playersList = new ArrayList<>();
  ReadTextFile readFile = new ReadTextFile();
  List<Rooms> roomsList;
  Rooms[] room = new Rooms[22];
  String[] allroomNames = {
      "Armory",
      "Billiard Room",
      "Carriage House",
      "Dining Hall",
      "Drawing Room",
      "Foyer",
      "Green House",
      "Hedge Maze",
      "Kitchen",
      "Lancaster Room",
      "Library",
      "Lilac Room",
      "Master Suite",
      "Nursery",
      "Parlor",
      "Piazza",
      "Servants' Quarters",
      "Tennessee Room",
      "Trophy Room",
      "Wine Cellar",
      "Winter Garden"
  };
  int itemLimit = 0;
    
  // Create an instance of the model
  TheWorld worldModel = new TheWorld();
  
  /**
   * This is the constructor for controller class.
   */
  public Controller() {
    
    String filepath = "C:/Users/rajas/Downloads/mansion.txt";

    // Load the world from the specified file
    boolean success = worldModel.loadWorld(filepath);
    if (!success) {
      System.out.println("Failed to load the world from the specified file.");
      return;
    }

    // Perform game actions
    worldModel.moveTargetCharacter();
    System.out.println(worldModel.getAdjacentRooms(1));
    worldModel.getRoomInformation(8);
  }

  /**
   * run() method gives users the choice to place a move.
   * 
   * @param turns means the number of turns each player gets.
   */
  public void run(int turns) {
    String playerName = null;
    System.out.println("===================Milestone 2======================");
    System.out.println("===================Game Start=======================");
    while (turns > 0) {
      System.out.print("Enter no of Players: ");
      int numOfPlayers = scanner.nextInt();
      scanner.nextLine();

      for (int i = 0; i < numOfPlayers; i++) {
        System.out.println("[Press 1 to add AddHumanPlayer,"
              + " Press 2 to AddComputerPlayer] to add players");
        int command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
          case 1:
            System.out.print("Enter player name: ");
            playerName = scanner.nextLine();
            System.out.print("Enter player item limit: ");
            int itemLimit = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter player room: ");
            String roomName = scanner.nextLine();
            player = new Players(playerName, itemLimit, roomName);
            player.addHumanPlayer(playerName, itemLimit, roomName);
            playersList.add(player);
            break;
          case 2:
            System.out.print("Enter player name: ");
            playerName = scanner.nextLine();
            System.out.print("Enter player item limit: ");
            itemLimit = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter player room: ");
            roomName = scanner.nextLine();
            player = new Players(playerName, itemLimit, roomName);
            player.addComputerPlayer(playerName, itemLimit, roomName);
            playersList.add(player);
            break;
          default:
            System.out.println("Invalid command. Please try again.");
            i--; // Decrement i to repeat the iteration
            break;
        }
      }
      System.out.println("===============Players are added to the game======================");

      int currentPlayerIndex = 0;
      while (turns > 0) {
        System.out.println("Place a move for Player" + (currentPlayerIndex + 1)
              + "\n" 
              + "Select a number:" + "\n"
              + "1: WorldGraphicalRep," + "\n"
              + "2: LookAround," + "\n" 
              + "3: MovePlayer," + "\n"
              + "4: PickItem," + "\n"
              + "5: PlayerInfo," + "\n"
              + "6: SpaceInfo" + "\n");
        int command = scanner.nextInt();
        scanner.nextLine();
        switch (command) {
          case 1:
            roomsList = worldModel.getRoomsList();
            TheWorldGraphics g = new TheWorldGraphics();
            g.getWorldGraphics(roomsList, playersList);
            break;
          case 2:
            System.out.print("Enter room name: ");
            String roomName = scanner.nextLine();
            for (int i = 0; i < allroomNames.length; i++) {
              if (allroomNames[i].equalsIgnoreCase(roomName)) {
                roomIndex = i;
                break;
              }
            }
            worldModel.moveTargetCharacter();
            ArrayList<Integer> adj = worldModel.getAdjacentRooms(roomIndex);
            System.out.println("\n\nAdjacent Rooms: " + adj);
            worldModel.getRoomInformation(roomIndex);
            turns--;
            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers; 
            break;
          case 3:
            System.out.print("Enter room name: ");
            roomName = scanner.nextLine();
            for (int i = 0; i < allroomNames.length; i++) {
              if (allroomNames[i].equalsIgnoreCase(roomName)) {
                roomIndex = i;
                break;
              }
            }
            worldModel.movePlayer(roomName, roomIndex, player);
            turns--;
            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers; 
            break;
          case 4:
            System.out.print("Enter item name: ");
            String itemName = scanner.nextLine();
            worldModel.pickItem(currentPlayerIndex, itemName, roomIndex, itemLimit);
            turns--;
            // Switch to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % numOfPlayers; 
            break;
          case 5:
            System.out.print("Enter player name: ");
            String playerName1 = scanner.nextLine();
            worldModel.printPlayerInfo(playersList, playerName1);
            break;
          case 6:
            System.out.print("Enter room name: ");
            roomName = scanner.nextLine();
            worldModel.spaceInfo(roomName);
            break;
          default:
            System.out.println("Invalid command. Please try again.");
            break;
        }
      }
    }
  }
}
