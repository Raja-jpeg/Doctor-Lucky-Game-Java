package mansion;

import java.io.IOException;

/**
 * This is FailingAppendable class.
 * This class handles exceptions.
 */
public class FailingAppendable implements Appendable {
  /**
   * append() method with charsequence single argument.
   */
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Fail!");
  }
  
  /**
   * append() method with multiple arguments.
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Fail!");
  }
  
  /**
   * append() method with single character argument.
   */
  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Fail!");
  }
}
