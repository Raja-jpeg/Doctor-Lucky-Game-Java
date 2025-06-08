package mansion;

import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.function.Function;
import mansion.GameControls;

/**
 * This is the Controller class.
 * It invokes all the actions performed by the players.
 */
public class Controller implements ControllerInterface {
    
  private final Appendable out;
  private final Scanner scanner;  
  private String numberOfPlayers;
  
  /**
   * This is the controller class constructor.
   * 
   * @param in the input.
   * @param out the output.
   */
  public Controller(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable cannot be null");
    }
    this.out = out;
    this.scanner = new Scanner(in);
    this.numberOfPlayers = null;
  }
  
  @Override
  public void playGame(GameControls obj) throws IllegalArgumentException {
    Stack<ActionInterface> commandStack = new Stack<>();
    Map<String, Function<Scanner, ActionInterface>> action = new HashMap<>();
    try {
      this.out.append(
          "===================Milestone 3======================\n");
      this.out.append(
          "====================Play it to win it====================\n");
      this.out.append("Enter the number of players\n");
      while (true) {
        try {
          this.numberOfPlayers = this.scanner.nextLine();
          Integer.parseInt(this.numberOfPlayers);
          break;
        } catch (IllegalArgumentException e) {
          this.out.append("Enter valid input\n");
        } 
      } 
      for (int i = 0; i < Integer.parseInt(this.numberOfPlayers); ) {
        action.put("1", s -> new AddHumanPlayer(s.next().trim(),
                s.nextInt(), s.nextLine().trim(), this.out));
        action.put("2", s -> new AddComputerPlayer(this.out));
        this.out.append("Choose a command option to add players\n");
        this.out.append("1---->AddHumanPlayer\n2---->AddComputerPlayer\n");
        String in = this.scanner.nextLine();
        if ("1".equals(in)) {
          this.out.append("Enter player name,player item limit and player room\n");
        } 
        Function<Scanner, ActionInterface> cmd = action.getOrDefault(in, null);
        if (cmd == null) {
          this.out.append("Enter a valid command\n");
          continue;
        } 
        ActionInterface c = cmd.apply(this.scanner);
        commandStack.add(c);
        try {
          c.execute(obj);
          if ("1".equals(in) || "2".equals(in)) {
            i++;
          }
        } catch (InputMismatchException e) {
          this.out.append("Enter valid parameters\n");
        } catch (IllegalArgumentException e) {
          this.out.append(String.valueOf(e.getMessage()) + "\n");
        } 
      } 
      this.out.append("=================All Players added to the game========================\n");
      while (obj.isGameNotOver()) {
        action.put("1", s -> new TheWorldGraphics(this.out));
        action.put("2", s -> new LookAround(this.out));
        action.put("3", s -> new MovePlayer(this.scanner, this.out));
        action.put("4", s -> new PickItem(this.scanner, this.out));
        action.put("5", s -> new PlayerDescription(s.nextLine(), this.out));
        action.put("6", s -> new RoomDescription(s.nextLine(), this.out));
        action.put("7", s -> new MovePet(this.scanner, this.out));
        action.put("8", s -> new AttackTarget(this.scanner, this.out));
        if (obj.checkComputerPlayer()) {
          try {
            this.out.append(String.valueOf(obj.playComputerPlayer()) + "\n");
          } catch (IllegalStateException e) {
            this.out.append(String.valueOf(e.getMessage()) + "\n");
          } 
          continue;
        } 
        this.out.append("=========Perform an action for Player " + obj.getPlayerNameTurn() 
            + "==============" + "\n");
        this.out.append("1.) WorldGraphicalRep\n");
        this.out.append("2.) LookAround\n");
        this.out.append("3.) MovePlayer\n");
        this.out.append("4.) PickItem\n");
        this.out.append("5.) PlayerInfo\n");
        this.out.append("6.) SpaceInfo\n");
        this.out.append("7.) MovePet\n");
        this.out.append("8.) AttackTarget\n");
        this.out.append("==========Hints===========\n");
        this.out.append(String.valueOf(obj.getHintsAboutWorld()) + "\n");
        this.out.append("=================================================================\n");
        String in = this.scanner.nextLine();
        if ("3".equals(in)) {
          this.out.append("Enter room name\n");
        } else if ("4".equals(in)) {
          this.out.append("Enter item name\n");
        } else if ("5".equals(in)) {
          this.out.append("Enter player name\n");
        } else if ("6".equals(in)) {
          this.out.append("Enter Space name\n");
        } else if ("7".equals(in)) {
          this.out.append("Enter room name\n");
        } 
        if ("q".equalsIgnoreCase(in) || "quit".equalsIgnoreCase(in)) {
          break;
        }
        Function<Scanner, ActionInterface> cmd = action.getOrDefault(in, null);
        if (cmd == null) {
          this.out.append("Enter a valid command\n");
          continue;
        } 
        ActionInterface action1 = cmd.apply(this.scanner);
        commandStack.add(action1);
        try {
          action1.execute(obj);
        } catch (IllegalArgumentException e) {
          this.out.append(String.valueOf(e.getMessage()) + "\n");
        } 
      } 
      this.out.append(String.valueOf(obj.getWinner()) + "\n");
      this.out.append("======================Game Over=============================\n");
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    } 
  }
}
