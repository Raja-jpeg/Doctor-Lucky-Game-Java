package mansion.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.filechooser.FileSystemView;
import mansion.WorldViewInterface;
import mansion.controller.PlayerCommandInterface;

/**
 * This is the MansionBoardGameViewImpl class which implements the MansoinBoardGameViewInterface.
 * It implements the view component for the game.
 */
public class MansionBoardGameViewImpl extends JFrame implements MansionBoardGameViewInterface {
  private static final long serialVersionUID = 1L;
  private final AddPlayersToMansion addPlayersPanel;
  private final MansionBoardGameIntro welcomePanel;
  private final MansionBoardGameSetup gamePanel;
  private final WorldViewInterface worldView;
  private final JPanel cardPanel;
  private final JDialog pickItemsDialog;
  private final JDialog attackTargetDialog;
  private final JDialog movePetDialog;
  private final JDialog newGameDialog;
  private final String welcomePage = "welcome page";
  private final String addPage = "add players page";
  private final String gamePage = "game page";
  private JComboBox<String> roomItemsComboBox;
  private JComboBox<String> playerItemsComboBox;
  private JComboBox<String> mansionRoomsComboBox;

  private final int mansionBoardHeight = 800;
  private final int mansionBoardWidth = 800;
  private JMenuBar menuBar;
  private JMenu menu;
  private JMenuItem menuItem1;
  private JMenuItem menuItem2;
  private JMenuItem menuItem3;

  private JScrollBar horizontalScrollBar;
  private JScrollBar verticalScrollBar;

  /**
   * This is the MansionBoardGameViewImpl class constructor.
   * It is helpful in initializing the MansionBoardGameViewImpl class fields.
   * 
   * @param worldView WorldViewInterface instance.
   */
  public MansionBoardGameViewImpl(WorldViewInterface worldView) {
    if (worldView == null) {
      throw new IllegalArgumentException("worldView object cannot be null");
    }
    this.worldView = worldView;
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    addPlayersPanel = new AddPlayersToMansion(worldView);
    welcomePanel = new MansionBoardGameIntro(worldView);
    gamePanel = new MansionBoardGameSetup(worldView);
    this.cardPanel = new JPanel(new CardLayout());
    roomItemsComboBox = new JComboBox<>();
    playerItemsComboBox = new JComboBox<>();
    this.pickItemsDialog = new JDialog();
    this.attackTargetDialog = new JDialog();
    this.movePetDialog = new JDialog();
    this.newGameDialog = new JDialog();
    this.horizontalScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 20, 0, 500);
    this.verticalScrollBar = new JScrollBar(JScrollBar.VERTICAL, 30, 40, 0, 500);
    cardPanel.add(welcomePanel, welcomePage);
    cardPanel.add(addPlayersPanel, addPage);
    cardPanel.add(gamePanel, gamePage);
    this.add(cardPanel);
    this.getContentPane().add(horizontalScrollBar, BorderLayout.SOUTH);
    this.getContentPane().add(verticalScrollBar, BorderLayout.EAST);
    menuBar = new JMenuBar();
    menu = new JMenu("Menu");
    menuItem1 = new JMenuItem("Play with current world");
    menuItem2 = new JMenuItem("Play with new world");
    menuItem3 = new JMenuItem("Quit Game");

    menu.add(menuItem1);
    menu.add(menuItem2);
    menu.add(menuItem3);

    menuBar.add(menu);

    this.setJMenuBar(menuBar);

    setTitle("Kill Doctor Lucky");
    setSize(this.getWidth(), this.getHeight());
    setBackground(Color.BLACK);
    setPreferredSize(new Dimension(mansionBoardHeight, mansionBoardWidth));
    pack();
    setVisible(true);
  }

  @Override
  public void displayWelcomePanel() {
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, this.welcomePage);
  }

  @Override
  public void configureGamePlay(PlayerCommandInterface component) {
    if (component == null) {
      throw new IllegalArgumentException("command object cannot be null");
    }
    setFocusable(true);
    requestFocus();
    this.addMouseListener(new MouseListener() {
      public void mouseClicked(MouseEvent e) {
        component.movePlayer(e.getX() / 20, (e.getY() / 20 - 3));
      }

      @Override
      public void mouseReleased(MouseEvent e) {

      }

      @Override
      public void mouseEntered(MouseEvent e) {

      }

      @Override
      public void mouseExited(MouseEvent e) {

      }

      @Override
      public void mousePressed(MouseEvent e) {

      }

    });
    this.addKeyListener(new KeyAdapter() {

      public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == '1') {
          component.lookAround();
        }
        if (e.getKeyChar() == '2') {
          if (worldView.getRoomItems().length == 0) {
            showMessage("No Items present in the room");
          } else {
            showPickItemBox();
            component.pickItem(roomItemsComboBox.getSelectedItem().toString());
          }
        }
        if (e.getKeyChar() == '3') {
          if (worldView.getPlayerItems().length == 0) {
            component.playerItems();
          } else {
            showAttackTargetBox();
            component.attackTarget(playerItemsComboBox.getSelectedItem().toString());
          }
        }
        if (e.getKeyChar() == '4') {
          showMovePetBox();
          component.movePet(mansionRoomsComboBox.getSelectedItem().toString());
        }
      }
    });

    menuItem1.addActionListener(l -> component.startNewGame());
    menuItem2.addActionListener(l -> this.startNewMapGame(component));
    menuItem3.addActionListener(l -> component.quitGame());
    newGameDialog.setVisible(false);
    addPlayersPanel.configureGamePlay(component);
    welcomePanel.configureGamePlay(component);
    gamePanel.configureGamePlay(component);
    this.setBackground(new Color(50, 50, 50));
  }

  @Override
  public void showMessage(String value) {
    if (value == null) {
      throw new IllegalArgumentException("string cannot be null");
    }
    JOptionPane.showMessageDialog(null, value);
    pickItemsDialog.setVisible(false);
  }

  /**
   * Message box which displays the result of every turn.
   * 
   * @param value string message.
   */
  public void showTurnMessage(String value) {
    if (value == null) {
      throw new IllegalArgumentException("string cannot be null");
    }
    JOptionPane.showMessageDialog(null, value);
    gamePanel.setHintUpdates();
    pickItemsDialog.setVisible(false);
    attackTargetDialog.setVisible(false);
    movePetDialog.setVisible(false);
  }

  @Override
  public void showErrorMessage(String value) {
    if (value == null) {
      throw new IllegalArgumentException("string cannot be null");
    }
    JOptionPane.showMessageDialog(null, value, "Error", JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void startGame() {
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, this.gamePage);
    gamePanel.setHintUpdates();
  }

  @Override
  public void addPlayers() {
    CardLayout cl = (CardLayout) (cardPanel.getLayout());
    cl.show(cardPanel, this.addPage);
  }

  @Override
  public void showPickItemBox() {
    roomItemsComboBox = new JComboBox<>(worldView.getRoomItems());
    roomItemsComboBox.setEditable(false);
    JOptionPane.showMessageDialog(new JFrame(), roomItemsComboBox, "Choose an item to pick",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showAttackTargetBox() {
    playerItemsComboBox = new JComboBox<>(worldView.getPlayerItems());
    playerItemsComboBox.setEditable(false);
    JOptionPane.showMessageDialog(new JFrame(), playerItemsComboBox, "Pick an item to Attack",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void showMovePetBox() {
    mansionRoomsComboBox = new JComboBox<>(worldView.getRooms());
    mansionRoomsComboBox.setEditable(false);
    JOptionPane.showMessageDialog(new JFrame(), mansionRoomsComboBox, "Choose a room to move pet",
        JOptionPane.INFORMATION_MESSAGE);
  }

  @Override
  public void quitGame() {
    this.setVisible(false);
    this.dispose();
    System.exit(1);
  }

  @Override
  public void startNewGame() {
    this.setVisible(false);
    this.dispose();
  }

  @Override
  public void startNewMapGame(PlayerCommandInterface component) {
    if (component == null) {
      throw new IllegalArgumentException("component cannot be null");
    }
    JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
    int returnValue = jfc.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      File selectedFile = jfc.getSelectedFile();
      component.startNewMapGame(selectedFile.getAbsolutePath());
    }
  }
}
