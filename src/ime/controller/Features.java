package ime.controller;


public interface Features {

  void loadImage();

  void saveImage();

  void toggle();

  void chooseHorizontalFlip();
  void chooseVerticalFlip();
  void chooseColorCorrect();
  void chooseVisualizeRed();
  void chooseVisualizeGreen();
  void chooseVisualizeBlue();
  void chooseCompression();
  void chooseSepia();
  void chooseLumaGreyscale();
  void chooseLevelsAdjust();
  void chooseBlur();
  void applyChosenOperation();
  void previewChosenOperation();
}
