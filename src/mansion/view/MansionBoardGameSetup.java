package mansion.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import mansion.WorldViewInterface;
import mansion.controller.PlayerCommandInterface;

/**
 * This is the MansionBoardGameSetup class which extends JPanel.
 * It displays the mansion board game screen to play the game to the user.
 *
 */
public class MansionBoardGameSetup extends JPanel {
  private static final long serialVersionUID = 1L;
  private final WorldViewInterface model;
  private JPanel mapPanel;
  private JPanel gamePlayPanel;
  private JPanel playerTurnPanel;
  private JPanel playerPanel;
  private JPanel hintPanel;
  private JPanel gameRulesPanel;
  private JLabel pictureLabel;
  private JLabel hintLabel;
  private JLabel playerTurnLabel;
  private JLabel gameRulesLabel;
  private JLabel playersLabel;

  private BufferedImage myPictureTest;

  /**
   * This is the MansionBoardGameSetup class constructor.
   * It is helpful in initializing the MansionBoardGameSetup class fields.
   * 
   * @param model WorldViewInterface instance.
   */
  public MansionBoardGameSetup(WorldViewInterface model) {
    if (model == null) {
      throw new IllegalArgumentException("model object cannot be null");
    }
    this.model = model;
    mapPanel = new JPanel();
    gamePlayPanel = new JPanel();
    playerTurnPanel = new JPanel();
    playerPanel = new JPanel();
    hintPanel = new JPanel();
    gameRulesPanel = new JPanel();
    this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    gamePlayPanel.setPreferredSize(new Dimension(800, 200));
    gamePlayPanel.setLayout(new BoxLayout(gamePlayPanel, BoxLayout.Y_AXIS));
    myPictureTest = model.getMansionBoardStatusImage();
    pictureLabel = new JLabel(new ImageIcon(myPictureTest));
    hintLabel = new JLabel();
    playerTurnLabel = new JLabel();
    playersLabel = new JLabel();
    gameRulesLabel = new JLabel("<html>Press 1 to Look Around<br/>Press 2 to Pick Item<br/>Press"
        + " 3 to Attack Target<br/>Press 4 to Move Pet<br/>"
        + "Click on a room in world to Move Player</html>");
    mapPanel.add(pictureLabel);
    playerTurnPanel.add(playerTurnLabel);
    playerPanel.add(playersLabel);
    hintPanel.add(hintLabel);
    gameRulesPanel.add(gameRulesLabel);
    gamePlayPanel.add(playerTurnPanel);
    gamePlayPanel.add(playerPanel);
    gamePlayPanel.add(hintPanel);
    gamePlayPanel.add(gameRulesPanel);
    this.add(mapPanel);
    this.add(gamePlayPanel);
    hintLabel.setFont(new Font("Montserrat", Font.BOLD, 75));
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
   * clues and the graph updated the game.
   */
  public void setHintUpdates() {
    hintLabel.setFont(new Font("Montserrat", Font.PLAIN, 12));
    hintLabel.setText(model.getHintsAboutWorld());
    hintLabel.setText("<html>" + model.getHintsAboutWorld()
        .replaceAll("\n", "<br/>") + "</html>");
    playerTurnLabel.setText(model.getPlayerNameTurn());
    playersLabel.setText(model.getPlayers());
    myPictureTest = model.getMansionBoardStatusImage();
    pictureLabel.setIcon(new ImageIcon(myPictureTest));
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
  }
}
