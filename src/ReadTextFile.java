package milestone;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  ReadTextFile will read the input text from the file.
 *
 */

public class ReadTextFile {
  
  int numRows;
  int numColumns;
  int totalRooms;
  String worldName;
  String targetName;
  Rooms[] room;
  Players player;
  Items[] item;
  String[] roomNamess;
  List<Rooms> roomsList = new ArrayList<>();


  
  /**
   * fileParsing method will parse the file from the text file.
   * @param filepath the path of the text file.
   */
  public void fileParsing(String filepath) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath))) {
      // Parse general world description
      String line = bufferedReader.readLine();
      String[] worldDescription = line.split(" ");
      numRows = Integer.parseInt(worldDescription[0]);
      numColumns = Integer.parseInt(worldDescription[1]);
      System.out.println("Number of rows: " + numRows);
      System.out.println("Number of columns: " + numColumns);
      worldName = worldDescription[2];
      

      // Parse target character details
      line = bufferedReader.readLine();
      String[] targetDetails = line.split(" ", 2);
      int targetHealth = Integer.parseInt(targetDetails[0]);
      String targetName = targetDetails[1];
      System.out.println("Target Character:");
      System.out.println("Health: " + targetHealth);
      System.out.println("Name of the target character: " + targetName);
      player = new Players(targetName, targetHealth, "Green House");

      // Parse spaces/rooms
      line = bufferedReader.readLine();
      totalRooms = Integer.parseInt(line);
      System.out.println("\nSpaces/Rooms:");
      
      roomNamess = new String[totalRooms];
      room = new Rooms[totalRooms];
      // Parse the room details
      for (int i = 0; i < totalRooms; i++) {
        line = bufferedReader.readLine();

        if (line.charAt(0) == ' ') {
          line = line.substring(1);
        }

        String[] roomDetails = line.split("\\s+");
        int upperLeftRow = Integer.parseInt(roomDetails[0]);
        int upperLeftColumn = Integer.parseInt(roomDetails[1]);
        int lowerRightRow = Integer.parseInt(roomDetails[2]);
        int lowerRightColumn = Integer.parseInt(roomDetails[3]);
        String roomDescriptor = roomDetails[4];

        // Print the room details or perform any other desired operations
        System.out.println("Room " + i + ":");
        System.out.println("Name of the room: " + roomDescriptor);

        room[i] = new Rooms(upperLeftRow, upperLeftColumn, lowerRightRow, 
            lowerRightColumn, roomDescriptor);
        roomNamess[i] = roomDescriptor;
        roomsList.add(room[i]);
        System.out.println("Roomslist" + roomsList);

      }
      // Parse items
      line = bufferedReader.readLine();
      int totalItems = Integer.parseInt(line);
      
      item = new Items[totalItems];
      System.out.println("\nItems:");
      for (int i = 0; i < totalItems; i++) {
        line = bufferedReader.readLine();
        String[] itemDetails = line.split(" ", 3);
        int roomIndex = Integer.parseInt(itemDetails[0]);
        int damageAmount = Integer.parseInt(itemDetails[1]);
        String itemName = itemDetails[2];
        System.out.println("Item " + i + ":");
        System.out.println("Room Index: " + roomIndex);
        System.out.println("Damage Amount: " + damageAmount);
        System.out.println("Name of the item: " + itemName);
        item[i] = new Items(itemName, roomIndex, damageAmount);
        System.out.println("Item read.");
        room[roomIndex].addItem(item[i]);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  public String getTargetCharacterName() {
    return targetName;
    
  }

  public List<Rooms> getRoomsList() {
    return roomsList;
  }

  public String getWorldName() {
    return worldName;
    
  }
  
  public int getWorldY() {
    return 0;
  }
  
  public Rooms[] getRooms() {
    return room;
  }
  
  public int getTotalRooms() {
    return totalRooms;
  }
  
  public Players getTarget() {
    return player;
  }

  
}

    
