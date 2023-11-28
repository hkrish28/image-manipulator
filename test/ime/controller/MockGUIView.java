package ime.controller;

import ime.view.GUIView;
import java.awt.Image;

public class MockGUIView implements GUIView {
  private StringBuilder methodCallLogger;
  private Features features;
  private Boolean fail;
  public MockGUIView(){
    this.methodCallLogger = new StringBuilder();
    this.fail=false;
  }
  @Override
  public void setFeatures(Features features) {
    this.features = features;
    methodCallLogger.append("features has been set");
  }

  @Override
  public void setImage(Image image) {

    methodCallLogger.append("image has been set");
  }

  @Override
  public void setHistogram(Image image) {
methodCallLogger.append("histogram has been set");
  }

  @Override
  public void enablePreview(boolean show) {
methodCallLogger.append("preview has been enabled");
  }

  @Override
  public void enableApply(boolean show) {
methodCallLogger.append("Apply has been enabled");
  }

  @Override
  public void enableToggle(boolean show) {
methodCallLogger.append("toggle has been enabled");

  }

  @Override
  public int getInput(String message) {
    methodCallLogger.append("input is received"+" "+message);
    if (fail) {
      throw new IllegalArgumentException("histogram drawer failed");
    }
    return 0;
  }

  @Override
  public boolean getConfirmation(String message) {
    methodCallLogger.append("confirmation received"+" "+message);
    return false;
  }

  @Override
  public void displayMessage(String message) {
    methodCallLogger.append("message displayed"+" "+message);
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
