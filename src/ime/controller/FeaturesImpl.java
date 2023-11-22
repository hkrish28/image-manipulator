package ime.controller;

public class FeaturesImpl implements Features {

  private static final String activeImage = "guiImage";
  private static final String histogram = "hist";
  private static final String preview = "previewImage";
  private GUIController controller;

  public FeaturesImpl(GUIController controller) {
    this.controller = controller;
  }

  private void invokeCommand(CommandEnum commandEnum) {
    invokeCommand(commandEnum, new String[]{activeImage, activeImage});
  }

  private void invokeCommand(CommandEnum commandEnum, String[] tokens) {
    String command = controller.knownCommands.get(commandEnum).constructCommand(tokens);
    String histogramCommand = controller.knownCommands.get(CommandEnum.histogram)
            .constructCommand(new String[]{activeImage, histogram});
    controller.executeCommand(command);
    controller.executeCommand(histogramCommand);
    controller.updateImage(activeImage);
    controller.updateHistogram(histogram);
  }

  @Override
  public void loadImage(String fileName) {
    invokeCommand(CommandEnum.load, new String[]{fileName, activeImage});
  }

  @Override
  public void saveImage(String fileName) {
    invokeCommand(CommandEnum.save, new String[]{fileName, activeImage});
  }

  @Override
  public void applyBlur() {
    invokeCommand(CommandEnum.blur);
  }

  @Override
  public void applySharpen() {
    invokeCommand(CommandEnum.sharpen);
  }

  @Override
  public void applySepia() {
    invokeCommand(CommandEnum.sepia);
  }

  @Override
  public void applyLumaGreyScale() {
    invokeCommand(CommandEnum.luma_component);
  }

  @Override
  public void applyLevelsAdjust(int b, int m, int w) {
    invokeCommand(CommandEnum.levels_adjust,
            new String[]{String.valueOf(b), String.valueOf(m), String.valueOf(w), activeImage});
  }

  @Override
  public void applyCompression(int compressionFactor) {
    invokeCommand(CommandEnum.compress,
            new String[]{String.valueOf(compressionFactor), activeImage});
  }

  @Override
  public void visualizeRed() {
    invokeCommand(CommandEnum.red_component);
  }

  @Override
  public void visualizeGreen() {
    invokeCommand(CommandEnum.green_component);
  }

  @Override
  public void visualizeBlue() {
    invokeCommand(CommandEnum.blue_component);
  }

  @Override
  public void applyHorizontalFlip() {
    invokeCommand(CommandEnum.horizontalFlip);
  }

  @Override
  public void applyVerticalFlip() {
    invokeCommand(CommandEnum.verticalFlip);
  }

  @Override
  public void applyColorCorrection() {
    invokeCommand(CommandEnum.color_correct);
  }

  @Override
  public void previewOperation(CommandEnum commandEnum, int previewPercent) {
    String command = controller.knownCommands.get(commandEnum)
            .constructPreviewCommand(new String[]{activeImage, preview}, previewPercent);
    controller.executeCommand(command);
    controller.updateImage(preview);
  }

  @Override
  public void toggle() {

  }


}

