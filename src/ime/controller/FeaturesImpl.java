package ime.controller;

import ime.controller.commands.Load;

public class FeaturesImpl implements Features {

  private final FileHandlerProvider fileHandlerProvider;
  public FeaturesImpl(FileHandlerProvider fileHandlerProvider){
      this.fileHandlerProvider = fileHandlerProvider;
  }
  @Override
  public void loadImage(String fileName) {
  }

  @Override
  public void saveImage(String fileName) {

  }

  @Override
  public void applyBlur() {

  }

  @Override
  public void applySharpen() {

  }

  @Override
  public void applySepia() {

  }

  @Override
  public void applyLumaGreyScale() {

  }

  @Override
  public void applyLevelsAdjust(int b, int m, int w) {

  }

  @Override
  public void applyCompression(int compressionFactor) {

  }

  @Override
  public void visualizeRed() {

  }

  @Override
  public void visualizeGreen() {

  }

  @Override
  public void visualizeBlue() {

  }

  @Override
  public void applyHorizontalFlip() {

  }

  @Override
  public void applyVerticalFlip() {

  }

  @Override
  public void applyColorCorrection() {

  }
}
