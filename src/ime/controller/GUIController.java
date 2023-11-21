package ime.controller;

import ime.model.ImageRepository;
import ime.view.GUIView;
import ime.view.View;

public class GUIController implements ImageProcessingController, Features {

  private final ImageRepository imgRepo;
  private final GUIView view;
  private final FileHandlerProvider fileHandlerProvider;
  public GUIController(ImageRepository imageRepository, GUIView view, FileHandlerProvider fileHandlerProvider){
    this.imgRepo = imageRepository;
    this.view = view;
    this.fileHandlerProvider = fileHandlerProvider;
  }
  @Override
  public void execute() {
    view.setFeatures(new FeaturesImpl(fileHandlerProvider));
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
