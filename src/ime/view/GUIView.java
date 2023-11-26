package ime.view;

import java.awt.Image;

import ime.controller.Features;

public interface GUIView extends View {
  void setFeatures(Features features);

  void setImage(Image image);

  void setHistogram(Image image);

  void enablePreview(boolean show);
  void enableApply(boolean show);
  void enableToggle(boolean show);

  int getInput(String message);

  boolean getConfirmation(String message);

}
