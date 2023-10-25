package ime;

/**
 * interface imageProcessingController is the controller interface. contains executeScript method
 */
public interface ImageProcessingController {

  /**
   * Reads the script and execute the commands by calling te respective methods from the image
   * interface.
   *
   * @param script is the script provided by the view.
   */
  void executeScript(String script);

}
