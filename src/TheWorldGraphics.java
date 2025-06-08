package milestone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



/**
 * Displays the graphics of Doctor Lucky's Mansion blueprint.
 */
public class TheWorldGraphics extends JPanel {

  /**
   * The main method to run the graphics display.
   * @param roomsList has the list of all the rooms
   * @param playersList 
   *
   * @param args command-line arguments
   */
  private Color getRandomColor() {
    Random random = new Random();
    int r = random.nextInt(256);
    int g = random.nextInt(256);
    int b = random.nextInt(256);
    return new Color(r, g, b);
  }
  
  /**
   * getWorldGraphics() method will generate the image.
   * 
   * @param roomsList has the list of all the rooms.
   * @param playersList has the list of all the players.
   */
  public void getWorldGraphics(List<Rooms> roomsList, List<Players> playersList) {
    int width = 800;
    int height = 600;
    int scalingFactor = 20; // Scaling factor to enlarge the rectangles
    int borderThickness = 2; // Thickness of the rectangle borders

    // Create a BufferedImage
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Get the Graphics object from the image
    Graphics2D g2d = image.createGraphics();

    // Set the background color
    g2d.setColor(Color.WHITE);
    g2d.fillRect(0, 0, width, height);

    // Read the coordinates and names from the file
    int roomIndex = 0; // Counter for room index
    try {
      for (Rooms room : roomsList) {
        int x1 = room.getTopLeftX();
        int y1 = room.getTopLeftY();
        int x2 = room.getBottomRightX();
        int y2 = room.getBottomRightY();

        // Calculate the scaled coordinates and dimensions
        int scaledX1 = x1 * scalingFactor;
        int scaledY1 = y1 * scalingFactor;
        int scaledWidth = (x2 - x1 + 1) * scalingFactor;
        int scaledHeight = (y2 - y1 + 1) * scalingFactor;

        // Set the fill color for the rectangle
        g2d.setColor(Color.CYAN);

        // Draw a filled rectangle
        g2d.fillRect(scaledX1, scaledY1, scaledWidth, scaledHeight);

        // Set the color for the rectangle border
        g2d.setColor(Color.BLACK);

        // Draw the rectangle border
        for (int i = 0; i < borderThickness; i++) {
          g2d.drawRect(scaledX1 + i, scaledY1 + i, scaledWidth - 2 * i, scaledHeight - 2 * i);
        }

        // Set the font and color for the room name
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.BLACK);
        
        
        String roomName = room.getRoomName();
        // Calculate the position to draw the room name

        int nameX = scaledX1 + scaledWidth / 2 - g2d.getFontMetrics().stringWidth(roomName) / 2;
        int nameY = scaledY1 + scaledHeight / 2;

        // Draw the room name
        g2d.drawString(roomName, nameX, nameY);

        // Check if the target character is in the current room
        if (roomIndex == 0) {
          int targetX = x1 + (x2 - x1) / 2;
          int targetY = y1 + (y2 - y1) / 2;

          // Print the position of the target character
          System.out.println("Target character is in room " + roomName 
              + " at position (" + targetX + ", " + targetY + ")");

          // Display the target character position as text overlay
          String targetText = "Target character: Room " + roomName 
              + " (" + targetX + ", " + targetY + ")";
          g2d.setFont(new Font("Arial", Font.BOLD, 16));
          g2d.setColor(Color.RED);
          g2d.drawString(targetText, 10, 30);
        }

        roomIndex++;
      }

      String targetRoomName; // Room name provided by the user
      for (Players player : playersList) {

        targetRoomName = player.getRoomName();
        for (Rooms room : roomsList) {

          if (room.getRoomName().equalsIgnoreCase(targetRoomName)) {
            int x1 = room.getTopLeftX();
            int y1 = room.getTopLeftY();
            int x2 = room.getBottomRightX();
            int y2 = room.getBottomRightY();
            int scaledX1 = x1 * scalingFactor;
            int scaledY1 = y1 * scalingFactor;
            int scaledX2 = x2 * scalingFactor;
            int scaledY2 = y2 * scalingFactor;
            int roomCenterX = (scaledX1 + scaledX2) / 2;
            int roomCenterY = (scaledY1 + scaledY2) / 2;

            int playerWidth = 15; // Width of the player circle
            int playerHeight = 15; // Height of the player circle

            int playerX = roomCenterX - playerWidth;
            int playerY = roomCenterY - playerHeight;

            // Get a unique color for each player
            Color playerColor = getRandomColor();
            // Draw the player circle
            g2d.setColor(playerColor);
            g2d.fillRect(playerX, playerY, playerWidth, playerHeight);

            // Set the font and color for the player name
            g2d.setFont(new Font("Arial", Font.BOLD, 10));
            g2d.setColor(Color.BLACK);

            // Calculate the position to draw the player name
            int nameX = (playerX + playerWidth / 2) 
                - (g2d.getFontMetrics().stringWidth(player.getName()) / 2);
            int nameY = playerY + playerHeight / 2;

            // Draw the player name
            g2d.drawString(player.getName(), roomCenterX, roomCenterY + 15);

            // Break the loop since we found the target room
            break;
          }
        }
      }
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } finally {
      // Dispose the Graphics object
      g2d.dispose();
    }

    // Display the image in a JFrame
    JFrame frame = new JFrame("Doctor Lucky's Mansion Blueprint");
    frame.getContentPane().add(new JLabel(new ImageIcon(image)));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

    // Save the image as a PNG file
    String filePath = "mansion.png"; // Path to the output PNG file
    try {
      ImageIO.write(image, "png", new File(filePath));
      System.out.println("Image saved successfully: " + filePath);
    } catch (IOException e) {
      System.out.println("Failed to save image: " + e.getMessage());
    }
  }
}