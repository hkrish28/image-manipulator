package ime.controller;

public class FeaturesImpl implements Features {

  private GUIController controller;

  public FeaturesImpl(GUIController controller) {
    this.controller = controller;
  }

  private void invokeCommand(CommandEnum commandEnum) {
    invokeCommand(commandEnum, new String[]{"guiImage", "guiImage"});
  }

  private void invokeCommand(CommandEnum commandEnum, String[] tokens) {
    String command = controller.knownCommands.get(commandEnum).constructCommand(tokens);
    controller.executeCommand(command);
  }

  @Override
  public void loadImage(String fileName) {
    invokeCommand(CommandEnum.load, new String[]{fileName, "guiImage"});
    controller.imageLoaded("guiImage");
  }

  @Override
  public void saveImage(String fileName) {
    invokeCommand(CommandEnum.save, new String[]{fileName, "guiImage"});
  }

  @Override
  public void applyBlur() {
    invokeCommand(CommandEnum.blur);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applySharpen() {
    invokeCommand(CommandEnum.sharpen);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applySepia() {
    invokeCommand(CommandEnum.sepia);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applyLumaGreyScale() {
    invokeCommand(CommandEnum.luma_component);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applyLevelsAdjust(int b, int m, int w) {
    invokeCommand(CommandEnum.levels_adjust,
            new String[]{String.valueOf(b), String.valueOf(m), String.valueOf(w), "guiImage"});
  }

  @Override
  public void applyCompression(int compressionFactor) {
    invokeCommand(CommandEnum.compress,
            new String[]{String.valueOf(compressionFactor), "guiImage"});
  }

  @Override
  public void visualizeRed() {
    invokeCommand(CommandEnum.red_component);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void visualizeGreen() {
    invokeCommand(CommandEnum.green_component);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void visualizeBlue() {
    invokeCommand(CommandEnum.blue_component);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applyHorizontalFlip() {
    invokeCommand(CommandEnum.horizontalFlip);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applyVerticalFlip() {
    invokeCommand(CommandEnum.verticalFlip);
    controller.imageLoaded("guiImage");
  }

  @Override
  public void applyColorCorrection() {
    invokeCommand(CommandEnum.color_correct);
    controller.imageLoaded("guiImage");
  }
}

