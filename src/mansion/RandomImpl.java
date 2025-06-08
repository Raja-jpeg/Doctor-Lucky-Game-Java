package mansion;

import java.util.Random;

/**
 * This is the random implementation for the RandomInterface.
 * It deals with generating random number to perform actions.
 *
 */
public class RandomImpl implements RandomInterface {

  private Random randomInteger;

  /**
   * This is the RandomImpl class constructor.
   * It is helpful in initializing fields for this class.
   */
  public RandomImpl() {
    this.randomInteger = new Random();
  }

  @Override
  public int generateRandomInteger() {
    return Math.abs(randomInteger.nextInt());
  }

}
