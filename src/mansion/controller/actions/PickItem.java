package mansion.controller.actions;

import mansion.GameControls;

/**
 * This is the PickItem class which implements ActionInterface.
 * It acts as picking up a valid item in the specified location in the game.
 *
 */
public class PickItem implements ActionInterface {

  private final String itemName;

  /**
   * This is the PickItem class constructor.
   * It is helpful in initializing PickItem class fields.
   * 
   * @param itemName name of the item.
   */
  public PickItem(String itemName) {
    if (itemName == null) {
      throw new IllegalArgumentException("The provided item is invalid.");
    }
    this.itemName = itemName;
  }

  @Override
  public void execute(GameControls worldInstance) 
          throws IllegalStateException, IllegalArgumentException {
    if (worldInstance == null) {
      throw new IllegalArgumentException("Invalid object: Cannot be null.");
    }
    worldInstance.playerPickItems(itemName);
  }
}
