package mansion.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import mansion.WorldViewInterface;
import mansion.controller.PlayerCommandInterface;

/**
 * This is the AddPlayersToMansion class which extends JPanel.
 * This class displays the panel to add players to the mansion board game.
 */
public class AddPlayersToMansion extends JPanel {
  private static final long serialVersionUID = 1L;
  private JPanel labelPanel;
  private JPanel middlePanel;
  private JPanel humanPanel;
  private JPanel humanPanel1;
  private JPanel humanPanel2;
  private JPanel humanLabelPanel;
  private JPanel humanInputPanel;
  private JPanel buttonPanel;
  private JTextField playerNameField;
  private JComboBox<String> playerRoomNameField;
  private JComboBox<String> playerItemsLimitField;
  private JLabel titleLabel;
  private JLabel playerNameLabel;
  private JLabel playerRoomLabel;
  private JLabel playerItemsLabel;
  private JButton addHumanButton;
  private JButton addComputerButton;
  private JButton playButton;

  /**
   * This is the AddPlayersToMansion class constructor.
   * It is helpful in initializing the AddPlayerToMansion class fields.
   * 
   * @param model WorldViewInterface instance.
   * 
   */
  public AddPlayersToMansion(WorldViewInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("model object cannot be null");
    }
    labelPanel = new JPanel();

    middlePanel = new JPanel();
    humanPanel = new JPanel();
    humanPanel1 = new JPanel();
    humanPanel2 = new JPanel();
    humanLabelPanel = new JPanel();
    humanInputPanel = new JPanel();

    buttonPanel = new JPanel();

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.X_AXIS));
    humanInputPanel.setLayout(new BoxLayout(humanInputPanel, BoxLayout.Y_AXIS));
    humanLabelPanel.setLayout(new BoxLayout(humanLabelPanel, BoxLayout.Y_AXIS));
    humanPanel.setLayout(new BoxLayout(humanPanel, BoxLayout.Y_AXIS));
    this.addHumanButton = new JButton("Add Human Player");
    this.addComputerButton = new JButton("Add Computer Player");
    this.playButton = new JButton("Play");
    this.titleLabel = new JLabel("Add Players to the game");
    this.playerNameLabel = new JLabel("Player Name:");
    this.playerRoomLabel = new JLabel("Player Room Name:");
    this.playerItemsLabel = new JLabel("Player Item's Limit:");
    this.playerNameField = new JTextField(20);
    this.playerRoomNameField = new JComboBox<String>(model.getRooms());
    String[] itemLimits = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
    this.playerItemsLimitField = new JComboBox<String>(itemLimits);

    titleLabel.setFont(new Font("Montserrat", Font.BOLD, 75));
    addHumanButton.setFont(new Font("Montserrat", Font.BOLD, 18));
    addComputerButton.setFont(new Font("Montserrat", Font.BOLD, 18));
    playButton.setFont(new Font("Montserrat", Font.BOLD, 20));
    
    humanPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    humanPanel.setBackground(new Color(240, 240, 240));
    humanPanel1.setBackground(new Color(240, 240, 240));
    humanPanel2.setBackground(new Color(240, 240, 240));
    
    // Add spacing between components
    middlePanel.setBorder(new EmptyBorder(20, 0, 20, 0));
    buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

    // Add rounded border to buttons
    addHumanButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    addComputerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    addHumanButton.setMargin(new Insets(10, 20, 10, 20)); // Top, Left, Bottom, Right
    addComputerButton.setMargin(new Insets(10, 20, 10, 20)); // Top, Left, Bottom, Right
    playButton.setMargin(new Insets(10, 20, 10, 20));
    addHumanButton.setBackground(new Color(255, 215, 0));
    addHumanButton.setPreferredSize(new Dimension(200, 30));
    addComputerButton.setPreferredSize(new Dimension(250, 30));
    playButton.setPreferredSize(new Dimension(180, 50));
    addComputerButton.setBackground(new Color(255, 215, 0));
    playButton.setBackground(new Color(46, 139, 87));
    addHumanButton.setForeground(Color.BLACK);
    addComputerButton.setForeground(Color.BLACK);
    playButton.setForeground(Color.WHITE);

    // Set up background color and border
    setBackground(new Color(200, 220, 240));
    Border border = BorderFactory.createLineBorder(Color.BLACK, 4, true);
    setBorder(border);
    
    humanLabelPanel.add(playerNameLabel);
    humanLabelPanel.add(playerRoomLabel);
    humanLabelPanel.add(playerItemsLabel);
    humanInputPanel.add(playerNameField);
    humanInputPanel.add(playerRoomNameField);
    humanInputPanel.add(playerItemsLimitField);
    humanPanel1.add(humanLabelPanel);
    humanPanel1.add(humanInputPanel);
    humanPanel1.add(addHumanButton);
    humanPanel2.add(addComputerButton);
    humanPanel.add(humanPanel1, BorderLayout.WEST);
    humanPanel.add(humanPanel2);

    labelPanel.add(titleLabel);
    middlePanel.add(humanPanel);
    buttonPanel.add(playButton);
    this.add(labelPanel);
    this.add(middlePanel);
    this.add(buttonPanel);
    titleLabel.setFont(new Font("Montserrat", Font.PLAIN, 30));
  }

  @Override
  public void paintComponent(Graphics g) {
    if (g == null) {
      throw new IllegalArgumentException("graphic object cannot be null");
    }
    super.paintComponent(g);
    Graphics2D graphicsObj = (Graphics2D) g;
    Border border = new LineBorder(Color.BLACK, 3, true);
    this.setBorder(border);
    graphicsObj.setColor(Color.WHITE);
    graphicsObj.fillRect(0, 0, getWidth(), getHeight());
    graphicsObj.setColor(Color.BLACK);
  }

  /**
   * The button component event listener will be implemented.
   *
   * @param component PlayerCommandInterface object.
   */
  public void configureGamePlay(PlayerCommandInterface component) {
    if (component == null) {
      throw new IllegalArgumentException("Command object cannot be null");
    }
    addHumanButton.addActionListener(l -> component.addHumanPlayer(playerNameField.getText(),
        playerRoomNameField.getSelectedItem().toString(),
        playerItemsLimitField.getSelectedItem().toString()));
    addComputerButton.addActionListener(l -> component.addComputerPlayer());
    playButton.addActionListener(l -> component.startGame());
  }

}
