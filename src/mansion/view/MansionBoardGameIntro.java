package mansion.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import mansion.WorldViewInterface;
import mansion.controller.PlayerCommandInterface;

/**
 * This is the MansionBoardGameIntro class which extends JPanel.
 * This class displays the introduction panel of the mansion board
 * which consists of the information on the respective buttons, labels, title and credits.
 */
public class MansionBoardGameIntro extends JPanel {
  private static final long serialVersionUID = 1L;
  private JPanel titlePanel;
  private JPanel picturePanel;
  private JPanel playButtonPanel;
  private JPanel creditsPanel;
  private JLabel titleLabel;
  private JLabel pictureLabel;
  private JLabel creditsLabel;
  private JButton playButton;

  /**
   * This is the MansionBoardGameIntro class constructor.
   * It is helpful in initializing the MansionBoardGameIntro class fields.
   * 
   * @param model WorldViewInterface instance.
   */
  public MansionBoardGameIntro(WorldViewInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("model object cannot be null");
    }
    this.titlePanel = new JPanel();
    this.picturePanel = new JPanel();
    this.playButtonPanel = new JPanel();
    this.creditsPanel = new JPanel();
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.titleLabel = new JLabel("Kill Doctor Lucky Board Game");
    this.playButton = new JButton("Enter the Mansion");
    this.creditsLabel = new JLabel("Submission: P. Raja Shiva Ram");
    playButton = new JButton("Enter the Mansion");
    playButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
    playButton.setMargin(new Insets(10, 20, 10, 20));
    playButton.setPreferredSize(new Dimension(180, 50));
    playButton.setForeground(Color.WHITE); // Text color
    playButton.setFont(new Font("Montserrat", Font.BOLD, 20)); // Custom font
    playButton.setBackground(new Color(46, 139, 87));
    BufferedImage myPicture;
    try {
      myPicture = ImageIO.read(new File("res/doctor3300.jpg"));
    } catch (IOException e) {
      throw new IllegalArgumentException("image not found");
    }
    pictureLabel = new JLabel(new ImageIcon(myPicture));
    titleLabel.setFont(new Font("Montserrat", Font.BOLD, 50));
    creditsLabel.setFont(new Font("Montserrat", Font.BOLD, 25));
    titlePanel.add(titleLabel);
    picturePanel.add(pictureLabel);
    creditsPanel.add(creditsLabel);
    playButtonPanel.add(playButton);
    this.add(titlePanel);
    this.add(picturePanel);
    this.add(playButtonPanel);
    this.add(creditsPanel);
  }

  @Override
  public void paintComponent(Graphics g) {
    if (g == null) {
      throw new IllegalArgumentException("graphic object cannot be null");
    }
    super.paintComponent(g);
    Graphics2D graphicsObj = (Graphics2D) g;
    Border border = new LineBorder(Color.BLACK, 3, true);
    graphicsObj.setBackground(new Color(50, 50, 50));
    this.setBorder(border);
    graphicsObj.setColor(Color.BLACK);
    graphicsObj.fillRect(0, 0, getWidth(), getHeight());
    graphicsObj.setColor(Color.BLACK);
  }

  /**
  /**
   * The button component event listener will be implemented.
   *
   * @param component PlayerCommandInterface object.
   */
  public void configureGamePlay(PlayerCommandInterface component) {
    if (component == null) {
      throw new IllegalArgumentException("Command object cannot be null");
    }
    playButton.addActionListener(l -> component.addPlayers());
    playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }
}
