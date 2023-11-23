package ime.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeaturesImpl implements Features {

  private static final String activeImage = "guiImage";
  private static final String histogram = "hist";
  private static final String preview = "previewImage";
  private GUIController controller;

  private boolean isPreview;

  public FeaturesImpl(GUIController controller) {
    this.controller = controller;
    isPreview = false;
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
    isPreview = false;
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
            new String[]{String.valueOf(b), String.valueOf(m), String.valueOf(w), activeImage, activeImage});
  }

  @Override
  public void applyCompression(int compressionFactor) {
    invokeCommand(CommandEnum.compress,
            new String[]{String.valueOf(compressionFactor), activeImage, activeImage});
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
  public void previewOperation(CommandEnum commandEnum, List<String> additionalInputs, int previewPercent) {

    List<String> commandTokens = additionalInputs != null ? new ArrayList<>(additionalInputs) : new ArrayList<>();
    commandTokens.addAll(Arrays.asList(activeImage, preview));
    String command = controller.knownCommands.get(commandEnum)
            .constructPreviewCommand(commandTokens.toArray(new String[0]), previewPercent);
    controller.executeCommand(command);
    controller.updateImage(preview);
    isPreview = true;
  }

  @Override
  public void toggle() {
    if (isPreview) {
      controller.updateImage(activeImage);
    } else {
      controller.updateImage(preview);
    }
    isPreview = !isPreview;
  }


}

