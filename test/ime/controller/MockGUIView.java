package ime.controller;

import ime.view.GUIView;
import java.awt.Image;
import java.util.List;

public class MockGUIView implements GUIView {
  private StringBuilder methodCallLogger;
  //private Features features;
  private int inputValue;
  private Boolean fail;
  public MockGUIView(){
    this.methodCallLogger = new StringBuilder();
    this.fail=false;
    this.inputValue=0;
  }
  @Override
  public void setFeatures(Features features) {
    //this.features = features;
    methodCallLogger.append("features has been set");
  }

  @Override
  public void setImage(Image image) {

    methodCallLogger.append("image has been set\n");
  }

  @Override
  public void setHistogram(Image image) {

    methodCallLogger.append("histogram has been set\n");

  }

  @Override
  public void enablePreview(boolean show) {
    if (show){

    methodCallLogger.append("preview has been enabled\n");}
    else{
      methodCallLogger.append("preview has been disabled\n");
    }
  }

  @Override
  public void enableApply(boolean show) {
if(show){
    methodCallLogger.append("Apply has been enabled\n");}
else {
  methodCallLogger.append("Apply has been disabled\n");
}
  }

  @Override
  public void enableToggle(boolean show) {
    if (show){
methodCallLogger.append("toggle has been enabled\n");}
    else{
      methodCallLogger.append("toggle has been disabled\n");
    }

  }

  @Override
  public int getInput(String message) {
    methodCallLogger.append("input is received"+" "+message+ "\n");
    if (fail) {
      throw new IllegalStateException("histogram drawer failed");
    }
    return inputValue++;
  }

  @Override
  public boolean getConfirmation(String message) {
    methodCallLogger.append("confirmation received"+" "+message);
    return false;
  }

  @Override
  public String getFilePathToLoad(List<String> supportedFormats) {
    methodCallLogger.append("load success\n");
    return null;
  }

  @Override
  public String getFilePathToSave() {
    methodCallLogger.append("save success\n");
    return null;
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
