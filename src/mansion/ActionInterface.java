package mansion;

/**
 * The ActionInterface interface allows controller to interact with model commands.
 */
public interface ActionInterface {
  /**
   * Executes a command.
   * 
   * @param worldObject World object
   */
  void execute(GameControls worldObject);
}
