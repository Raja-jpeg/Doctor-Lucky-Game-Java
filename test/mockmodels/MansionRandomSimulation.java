package mockmodels;

import mansion.RandomInterface;

/**
 * This is the MansionRandomSimulation class which implements RandomInterface.
 * It acts a simulation of Random interface.
 */
public class MansionRandomSimulation implements RandomInterface {
  private StringBuilder message;
  private final int[] args;
  private int index;

  /**
   * This is the MansionRandomSimulation class constructor.
   * It is helpful in initializing the MansionRandomSimulation class fields.
   * 
   * @param message the message to be displayed.
   * @param uniqueIdentifier checks whether the correct method is invoked.
   * @param varargs variable arguments.
   */
  public MansionRandomSimulation(StringBuilder message, int uniqueIdentifier, int... varargs) {
    this.message = message;
    this.args = new int[varargs.length];
    for (int i = 0; i < varargs.length; i++) {
      this.args[i] = varargs[i];
    }
    this.index = 0;
  }

  @Override
  public int generateRandomInteger() {
    message.append("generateRandomInteger method invoked with input" + "\n");
    int num = args[this.index % args.length];
    this.index++;
    return num;
  }
}
