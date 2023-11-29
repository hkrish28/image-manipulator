package ime.controller;

import ime.view.GUIView;
import java.awt.Image;
import java.util.List;

/**
 * Mock implementation of the GUIView interface for testing purposes. Captures method calls and
 * provides a log for verification in unit tests.
 */
public class MockGUIView implements GUIView {

  private StringBuilder methodCallLogger;
  private int inputValue;
  private Boolean fail;

  /**
   * Initializes the MockGUIView with an empty method call log and default values.
   */
  public MockGUIView() {
    this.methodCallLogger = new StringBuilder();
    this.fail = false;
    this.inputValue = 0;
  }

  /**
   * Records the setting of features in the method call log.
   *
   * @param features The Features object to be set.
   */
  @Override
  public void setFeatures(Features features) {
    //this.features = features;
    methodCallLogger.append("features has been set");
  }

  /**
   * Records the setting of an image in the method call log.
   *
   * @param image The Image object to be set.
   */

  @Override
  public void setImage(Image image) {

    methodCallLogger.append("image has been set\n");
  }

  /**
   * Records the setting of a histogram in the method call log.
   *
   * @param image The Image object representing the histogram.
   */
  @Override
  public void setHistogram(Image image) {

    methodCallLogger.append("histogram has been set\n");

  }

  /**
   * Records the enabling or disabling of the preview and logs it accordingly.
   *
   * @param show True to enable, false to disable the preview.
   */
  @Override
  public void enablePreview(boolean show) {
    if (show) {

      methodCallLogger.append("preview has been enabled\n");
    } else {
      methodCallLogger.append("preview has been disabled\n");
    }
  }

  /**
   * Records the enabling or disabling of the Apply feature and logs it accordingly.
   *
   * @param show True to enable, false to disable Apply.
   */
  @Override
  public void enableApply(boolean show) {
    if (show) {
      methodCallLogger.append("Apply has been enabled\n");
    } else {
      methodCallLogger.append("Apply has been disabled\n");
    }
  }

  /**
   * Records the enabling or disabling of the toggle feature and logs it accordingly.
   *
   * @param show True to enable, false to disable the toggle.
   */
  @Override
  public void enableToggle(boolean show) {
    if (show) {
      methodCallLogger.append("toggle has been enabled\n");
    } else {
      methodCallLogger.append("toggle has been disabled\n");
    }

  }

  /**
   * Records the receipt of an input along with a message in the method call log.
   *
   * @param message The message accompanying the input.
   * @return The simulated input value.
   * @throws IllegalStateException If the operation fails due to a specific condition.
   */
  @Override
  public int getInput(String message) {
    methodCallLogger.append("input is received" + " " + message + "\n");
    if (fail) {
      throw new IllegalStateException("histogram drawer failed");
    }
    return inputValue++;
  }

  /**
   * Records the receipt of a confirmation along with a message in the method call log.
   *
   * @param message The message accompanying the confirmation.
   * @return Always returns false in this mock implementation.
   */
  @Override
  public boolean getConfirmation(String message) {
    methodCallLogger.append("confirmation received" + " " + message);
    return false;
  }

  /**
   * Records the success of loading a file in the method call log.
   *
   * @param supportedFormats A list of supported file formats.
   * @return Always returns null in this mock implementation.
   */
  @Override
  public String getFilePathToLoad(List<String> supportedFormats) {
    methodCallLogger.append("load success\n");
    return null;
  }

  /**
   * Records the success of saving a file in the method call log.
   *
   * @return Always returns null in this mock implementation.
   */
  @Override
  public String getFilePathToSave() {
    methodCallLogger.append("save success\n");
    return null;
  }

  /**
   * Records the display of a message in the method call log.
   *
   * @param message The message to be displayed.
   */
  @Override
  public void displayMessage(String message) {
    methodCallLogger.append("message displayed" + " " + message);

  }

  /**
   * gets logger.
   *
   * @return string.
   */
  public String getLogger() {
    return methodCallLogger.toString();
  }

  /**
   * Clear the log of method calls made to this MockFileHandlerProvider instance.
   */
  public void clearLogger() {
    methodCallLogger = new StringBuilder();
  }
}
