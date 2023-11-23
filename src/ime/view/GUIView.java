package ime.view;

import java.awt.*;
import java.util.List;

import ime.controller.Features;

public interface GUIView {
  void setFeatures(Features features);

  void setOptions(List<Option> optionList);

  void setImage(Image image);

  void setHistogram(Image image);

}
