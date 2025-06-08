package simulation;

import mansion.RandomInterface;

/**
 * This is the RandomImplSimulation class.
 * This class simulates the controller's random functionality.
 */
public class RandomImplSimulation implements RandomInterface {
 
  private StringBuilder message;
  private final int uniqueCode;
  private final int[] args;
  private int index;
  
  /**
   * This is the RandomImplSimulation class constructor.
   * 
   * @param message the method
   * @param uniqueCode unique code
   * @param varargs various arguments.
   */
  public RandomImplSimulation(StringBuilder message, int uniqueCode, int... varargs) {
    this.message = message;
    this.uniqueCode = uniqueCode;
    this.args = new int[varargs.length];
    for (int i = 0; i < varargs.length; i++) {
      this.args[i] = varargs[i];
    }
    this.index = 0;
  }
  
  @Override
  public int getRandomInt() {
    this.message.append("getRandomInt method invoked with input\n");
    int num = this.args[this.index % this.args.length];
    this.index++;
    return num;
  }
}
