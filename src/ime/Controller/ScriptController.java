package ime.Controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ime.Model.ImageRepository;
import ime.Model.ImageRepositoryImpl;
import ime.View.View;
import ime.View.ViewImpl;

/**
 * The `controller` class implements the ImageProcessingController interface and provides
 * functionality to execute image processing commands specified in a script file.
 */

public class ScriptController implements ImageProcessingController {

  ImageRepository imgRepo;

  View view;
  Scanner in;

  /**
   * Constructs a new controller instance with an empty image files map.
   */
  public ScriptController(Scanner in) {

    this.imgRepo = new ImageRepositoryImpl();
    this.in = in;
    this.view = new ViewImpl(System.out);

  }

  @Override
  public void execute() {
    view.displayMessage("Please enter the command script file: ");
    String scriptFileName = in.next();
    processScriptFile(scriptFileName);
    view.displayMessage("Script file execution complete.");
  }


  private void executeCommand(String command) {

    String[] tokens = command.split(" ");
    if (tokens.length < 2) {
      view.displayMessage("Invalid command statement provided");
      return;
    }

    CommandEnum commandKeyword = getCommandEnum(tokens[0]);
    if (commandKeyword != null) {
      switch (commandKeyword) {
        case load:
          processLoad(tokens);
          break;
        case save:
          processSave(tokens);
          break;
        case brighten:
          processBrighten(tokens);
          break;
        case horizontalFlip:
          processHorizontalFlip(tokens);
          break;
        case verticalFlip:
          processVerticalFlip(tokens);
          break;
        case rgb_split:
          processSplit(tokens);
          break;
        case rgb_combine:
          processCombine(tokens);
          break;
        case value_component:
          processValueGreyScale(tokens);
          break;
        case red_component:
          processRedChannel(tokens);
          break;
        case green_component:
          processGreenChannel(tokens);
          break;
        case blue_component:
          processBlueChannel(tokens);
          break;
        case luma_component:
          processLumaGreyScale(tokens);
          break;
        case intensity_component:
          processIntensityGreyScale(tokens);
          break;
        case blur:
          processBlur(tokens);
          break;
        case sharpen:
          processSharpen(tokens);
          break;
        case sepia:
          processSepia(tokens);
          break;
      }
    }

  }


  private void processLoad(String[] tokens) {
    String path = tokens[1];
    String imageName = tokens[2];
    try {
      imgRepo.loadImage(path, imageName);
    } catch (IOException e) {
      view.displayMessage("File not found to load");
    }
  }

  private void processSave(String[] tokens) {
    String imageName = tokens[2];
    String fileName = tokens[1];
    try {
      this.imgRepo.saveImage(fileName, imageName);
    } catch (Exception e) {
      view.displayMessage("Exception occurred during save" + e.getMessage());
    }
  }

  private void processBrighten(String[] tokens) {
    float brightnessConstant = Float.parseFloat(tokens[1]);
    String imageName = tokens[2];
    String newImage = tokens[3];
    imgRepo.brightenImage(imageName, newImage, brightnessConstant);

  }

  private void processHorizontalFlip(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.flipImageHorizontally(imageName, newImage);
  }

  private void processVerticalFlip(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.flipImageHorizontally(imageName, newImage);
  }

  private void processSplit(String[] tokens) {
    String srcImage = tokens[1];
    List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
    imgRepo.splitImageIntoColorChannels(srcImage, colorChannelsImages);
  }

  private void processCombine(String[] tokens) {
    String destImage = tokens[1];
    List<String> colorChannelsImages = Arrays.asList(tokens).subList(2, tokens.length);
    imgRepo.combineImages(colorChannelsImages, destImage);
  }

  private void processRedChannel(String[] tokens) {
    String srcImage = tokens[1];
    String destImage = tokens[2];
    imgRepo.toRedChannelImage(srcImage, destImage);
  }

  private void processGreenChannel(String[] tokens) {
    String srcImage = tokens[1];
    String destImage = tokens[2];
    imgRepo.toGreenChannelImage(srcImage, destImage);
  }

  private void processBlueChannel(String[] tokens) {
    String srcImage = tokens[1];
    String destImage = tokens[2];
    imgRepo.toBlueChannelImage(srcImage, destImage);
  }

  private void processValueGreyScale(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.toValueGreyScale(imageName, newImage);
  }

  private void processLumaGreyScale(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.toLumaGreyScale(imageName, newImage);
  }

  private void processIntensityGreyScale(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.toIntensityGreyScale(imageName, newImage);
  }

  private void processBlur(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.blurImage(imageName, newImage);
  }

  private void processSharpen(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.sharpenImage(imageName, newImage);
  }

  private void processSepia(String[] tokens) {
    String imageName = tokens[1];
    String newImage = tokens[2];
    imgRepo.toSepiaImage(imageName, newImage);
  }

  private CommandEnum getCommandEnum(String commandStr) {
    for (CommandEnum cmd : CommandEnum.values()) {
      if (cmd.getRepresentation().equals(commandStr)) {
        return cmd;
      }
    }
    return null; // Command not found
  }

  public void processScriptFile(String scriptFileName) {
    try (BufferedReader reader = new BufferedReader(new FileReader(scriptFileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        // Trim leading and trailing whitespaces
        line = line.trim();
        // Ignore lines that start with #
        if (!(line.startsWith("#") || line.isEmpty())) {
          // Call the controller's executeScript method with the current line
          executeCommand(line);
        }
      }
    } catch (IOException e) {
      view.displayMessage("Invalid script location/file." + e.getMessage());
    }
  }
}


