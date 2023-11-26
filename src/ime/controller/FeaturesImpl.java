package ime.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FeaturesImpl implements Features {

  private static final String activeImage = "guiImage";
  private static final String histogram = "hist";
  private static final String preview = "previewImage";

  private boolean unsavedImagePrompt;
  private List<String> tokens;

  private CommandEnum chosenCommand;
  private GUIController controller;

  private boolean isPreview;

  public FeaturesImpl(GUIController controller) {
    this.controller = controller;
    isPreview = false;
    unsavedImagePrompt = false;
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
  public void loadImage() {
    if (unsavedImagePrompt) {
      if (!controller.getConfirmation(
              "Current image is unsaved. Do you want to overwrite the image?")) {
        return;
      }
    }
    String fileName = openFileAction();
    invokeCommand(CommandEnum.load, new String[]{fileName, activeImage});
  }

  private String openFileAction() {
    final JFileChooser fchooser = new JFileChooser(".");
    List<String> supportedFormats = Arrays.stream(FileFormatEnum.values())
            .map(FileFormatEnum::name).collect(Collectors.toList());
    FileNameExtensionFilter filter
            = new FileNameExtensionFilter("Supported : " + supportedFormats,
            supportedFormats.toArray(new String[0]));
    fchooser.setFileFilter(filter);
    int retvalue = fchooser.showOpenDialog(null);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
  }

  @Override
  public void saveImage() {
    String fileName = saveFileAction();
    invokeCommand(CommandEnum.save, new String[]{fileName, activeImage});
    unsavedImagePrompt = false;
  }

  private String saveFileAction() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(null);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      return f.getAbsolutePath();
    }
    return null;
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

  @Override
  public void chooseHorizontalFlip() {
    setCommandTokens(CommandEnum.horizontalFlip);
    controller.setupOperation(true, false);
  }

  @Override
  public void chooseVerticalFlip() {
    setCommandTokens(CommandEnum.verticalFlip);
    controller.setupOperation(true, false);
  }

  @Override
  public void chooseColorCorrect() {
    setCommandTokens(CommandEnum.color_correct);
    controller.setupOperation(true, true);
  }

  @Override
  public void chooseVisualizeRed() {
    setCommandTokens(CommandEnum.red_component);
  }

  @Override
  public void chooseVisualizeGreen() {
    setCommandTokens(CommandEnum.green_component);
    controller.setupOperation(true, false);
  }

  @Override
  public void chooseVisualizeBlue() {
    setCommandTokens(CommandEnum.blue_component);
    controller.setupOperation(true, false);
  }

  @Override
  public void chooseCompression() {
    int compressPercent = getValueWithConstraint("compression factor", 0, 100);
    setCommandTokens(CommandEnum.compress, Arrays.asList(String.valueOf(compressPercent), activeImage));
    controller.setupOperation(true, false);
  }

  @Override
  public void chooseSepia() {
    setCommandTokens(CommandEnum.sepia);
    controller.setupOperation(true, true);
  }

  @Override
  public void chooseLumaGreyscale() {
    setCommandTokens(CommandEnum.luma_component);
    controller.setupOperation(true, true);
  }

  @Override
  public void chooseLevelsAdjust() {
    int b;
    int m;
    int w;
    b = getValueWithConstraint("black point", 0, 253);
    m = getValueWithConstraint("mid point", b + 1, 254);
    w = getValueWithConstraint("white point", m + 1, 255);

    setCommandTokens(CommandEnum.levels_adjust, Arrays.asList(String.valueOf(b), String.valueOf(m), String.valueOf(w), activeImage));
    controller.setupOperation(true, true);
  }

  private int getValueWithConstraint(String message, int min, int max) {
    int val = controller.getInput("Enter value between " + min + " - " + max + " for " + message);
    while (val < min || val > max) {
      controller.sendDisplayMessage("Invalid value. Please try again");
      val = controller.getInput("Enter value between " + min + " - " + max + " for " + message);
    }
    return val;
  }

  @Override
  public void chooseBlur() {
    setCommandTokens(CommandEnum.blur);
    controller.setupOperation(true, true);
  }

  private void setCommandTokens(CommandEnum commandEnum, List<String> tokens) {
    chosenCommand = commandEnum;
    this.tokens = tokens;
  }

  private void setCommandTokens(CommandEnum commandEnum) {
    chosenCommand = commandEnum;
    tokens = Arrays.asList(activeImage);
  }

  @Override
  public void applyChosenOperation() {
    List<String> commandTokens = new ArrayList<>(tokens);
    commandTokens.add(activeImage);
    invokeCommand(chosenCommand, commandTokens.toArray(new String[0]));
    controller.setToggle(false);
    unsavedImagePrompt = true;
  }

  @Override
  public void previewChosenOperation() {
    List<String> commandTokens = new ArrayList<>(tokens);
    commandTokens.add(preview);
    int previewPercent = getValueWithConstraint("preview percentage", 0, 100);
    String command = controller.knownCommands.get(chosenCommand)
            .constructPreviewCommand(commandTokens.toArray(new String[0]), previewPercent);
    controller.executeCommand(command);
    controller.updateImage(preview);
    isPreview = true;
    controller.setToggle(true);
    controller.setupOperation(true, false);
  }

}

