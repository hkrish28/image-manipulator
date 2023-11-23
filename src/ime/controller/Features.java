package ime.controller;

import java.util.List;

public interface Features {

  void loadImage(String fileName);

  void saveImage(String fileName);

  void applyBlur();

  void applySharpen();

  void applySepia();

  void applyLumaGreyScale();

  void applyLevelsAdjust(int b, int m, int w);

  void applyCompression(int compressionFactor);

  void visualizeRed();

  void visualizeGreen();

  void visualizeBlue();

  void applyHorizontalFlip();

  void applyVerticalFlip();

  void applyColorCorrection();

  void previewOperation(CommandEnum commandEnum, List<String> additionalInputs, int previewPercent);

  void toggle();
}
